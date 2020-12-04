package fr.sganayon.training.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static fr.sganayon.training.blocks.ModBlocks.FIRSTGENERATOR_TILE;

public class FirstGeneratorTile extends TileEntity implements ITickableTileEntity {

    private ItemStackHandler itemStackHandler;

    public FirstGeneratorTile() {
        super(FIRSTGENERATOR_TILE);
    }

    @Override
    public void tick() {
        if(world.isRemote){
            System.out.println("tick");
        }
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT compound = tag.getCompound("inv");
        getItemStackHandler().deserializeNBT(compound);
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        CompoundNBT compound = getItemStackHandler().serializeNBT();
        tag.put("inv", compound);
        return super.write(tag);
    }

    private ItemStackHandler getItemStackHandler(){
        if(itemStackHandler == null){
            itemStackHandler = new ItemStackHandler(1){
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
        return itemStackHandler;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return LazyOptional.of(() -> (T) getItemStackHandler());
        }
        return super.getCapability(cap, side);
    }
}
