package fr.sganayon.training.blocks;

import fr.sganayon.training.Config;
import fr.sganayon.training.tools.CustomEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

import static fr.sganayon.training.blocks.ModBlocks.FIRSTGENERATOR_TILE;

public class FirstGeneratorTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final ItemStackHandler itemStackHandler;
    private final LazyOptional<IItemHandler> itemHandler;
    private final CustomEnergyStorage energyStorageHandler;
    private final LazyOptional<IEnergyStorage> energyHandler;
    private int counter;

    public FirstGeneratorTile() {
        super(FIRSTGENERATOR_TILE);
        itemStackHandler = this.createItemStackHandler();
        itemHandler = LazyOptional.of(() -> itemStackHandler);
        energyStorageHandler = this.createEnergyHandler();
        energyHandler = LazyOptional.of(() -> energyStorageHandler);
    }

    @Override
    public void tick() {
        if(world.isRemote){
            return;
        }
        if(counter > 0){
            counter--;
            if(counter <= 0){
                energyStorageHandler.addEnergy(Config.FIRSTGENERATOR_GENERATE.get());
            }
            markDirty();
        }
        if (counter <= 0) {
            Item item = itemStackHandler.getStackInSlot(0).getItem();
            if(item == Items.DIAMOND){
                itemStackHandler.extractItem(0,1,false);
                counter = Config.FIRSTGENERATOR_GENERATE_SPEED.get();
                markDirty();
            }
        }

        BlockState blockState = world.getBlockState(pos);
        if(blockState.get(BlockStateProperties.POWERED) != counter>0){
            world.setBlockState(pos,blockState.with(BlockStateProperties.POWERED, counter>0),3);
        }

        sendOutPower();
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT itemCompound = tag.getCompound("inv");
        itemStackHandler.deserializeNBT(itemCompound);

        CompoundNBT energyCompound = tag.getCompound("energy");
        energyStorageHandler.deserializeNBT(energyCompound);
        counter = tag.getInt("counter");
        super.read(tag);
    }

    @NotNull
    @Override
    public CompoundNBT write(CompoundNBT tag) {
        CompoundNBT itemCompound = itemStackHandler.serializeNBT();
        tag.put("inv", itemCompound);

        CompoundNBT energyCompound = energyStorageHandler.serializeNBT();
        tag.put("energy", energyCompound);

        tag.putInt("counter", counter);

        return super.write(tag);
    }

    @NotNull
    private ItemStackHandler createItemStackHandler(){

        return new ItemStackHandler(1){
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return stack.getItem() == Items.DIAMOND;
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                if (stack.getItem() != Items.DIAMOND){
                    return stack;
                }
                return super.insertItem(slot,stack,simulate);
            }

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
    }

    @NotNull
    private CustomEnergyStorage createEnergyHandler() {
        return new CustomEnergyStorage(Config.FIRSTGENERATOR_MAXPOWER.get(),Config.FIRSTGENERATOR_MAXTRANSFER.get());
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return itemHandler.cast();
        }
        if(cap == CapabilityEnergy.ENERGY){
            return energyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    //server side
    @org.jetbrains.annotations.Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new FirstGeneratorContainer(id, world, pos, playerInventory, playerEntity);
    }

    private void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorageHandler.getEnergyStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                TileEntity te = world.getTileEntity(pos.offset(direction));
                if (te != null) {
                    boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveEnergy(Math.min(capacity.get(), Config.FIRSTGENERATOR_SEND.get()), false);
                                    capacity.addAndGet(-received);
                                    energyStorageHandler.consumeEnergy(received);
                                    markDirty();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }
}
