package fr.sganayon.training.items;

import net.minecraft.item.IItemTier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Collections;
import java.util.List;

public class DoublePickaxe extends AbstractMultiBreakPickaxe {

    public DoublePickaxe(IItemTier itemTier) {
        super(itemTier, false, false);
    }

    @Override
    protected List<BlockPos> getBlockPosToBreakRelativeTo(World worldIn, BlockPos pos) {
        return Collections.singletonList(new BlockPos(pos.getX(), pos.getY() - 1 , pos.getZ()));
    }
}
