package fr.sganayon.training.blocks;

import fr.sganayon.training.setup.Registration;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class MagicTile extends TileEntity {

    public MagicTile() {super(Registration.MAGICBLOCK_TILE.get());}

    /**
     * Allow to render the tile even if the block isn't rendered (as the tile is taller than 1 block : 2 high)
     * @return
     */
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getPos(), getPos().add(1,2,1));
    }
}
