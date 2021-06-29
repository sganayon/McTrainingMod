package fr.sganayon.training.items;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Obliterator extends CubePickaxe{
    public Obliterator(int length, int height, int depth) {
        super(ItemTierMod.OBSI, length, height, depth, false, true);
        blocksWhiteList.add(Blocks.STONE);
        blocksWhiteList.add(Blocks.DIORITE);
        blocksWhiteList.add(Blocks.ANDESITE);
        blocksWhiteList.add(Blocks.GRANITE);
        blocksWhiteList.add(Blocks.GRAVEL);
        blocksWhiteList.add(Blocks.SAND);
        blocksWhiteList.add(Blocks.SANDSTONE);
        blocksWhiteList.add(Blocks.DIRT);
    }

    @Override
    public boolean canBeBroken(World worldIn, BlockPos pos) {
        return blocksWhiteList.contains(worldIn.getBlockState(pos).getBlock());
    }
}
