package fr.sganayon.training.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static fr.sganayon.training.blocks.ModBlocks.FIRSTGENERATOR_TILE;

public class FirstGeneratorTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final ItemStackHandler itemStackHandler;
    private final LazyOptional<IItemHandler> handler;

    public FirstGeneratorTile() {
        super(FIRSTGENERATOR_TILE);
        itemStackHandler = this.createItemStackHandler();
        handler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void tick() {
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT compound = tag.getCompound("inv");
        itemStackHandler.deserializeNBT(compound);
        super.read(tag);
    }

    @NotNull
    @Override
    public CompoundNBT write(CompoundNBT tag) {
        CompoundNBT compound = itemStackHandler.serializeNBT();
        tag.put("inv", compound);
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
        };
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
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
}
