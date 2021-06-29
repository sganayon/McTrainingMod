package fr.sganayon.training.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Mine a cuboid shape and put the drops int the player inventory if possible, if not spawn them.
 * When mining in West, East, North, South direction : the cube start under the block broken with {height} and {depth} and {length}/2 on both side
 * When mining up and down : mine {depth} block in that direction, {length} in x and {height} in z centered on the block broken
 *
 */
public class CubePickaxe extends AbstractMultiBreakPickaxe{
    private final int length;
    private final int height;
    private final int depth;

    public CubePickaxe(IItemTier tier, int length, int height, int depth, boolean autoPickup, boolean obliterate) {
        super(tier, autoPickup, obliterate);
        this.length = length;
        this.height = height;
        this.depth = depth;
    }

    /**
     * Instead of spawning drops, put them in the player inventory if possible
     * If not they spawn them
     * @param blockState the state of the block being broken
     * @param worldIn the world
     * @param playerEntity the player that broke the blocks
     * @param pos the pos of the broken block
     * @param te the tile entity if any (/!\ Always null for the moment)
     * @param stack the tool that was used to break the block
     */
    @Override
    protected void harvestBrokenBlock(BlockState blockState, World worldIn, PlayerEntity playerEntity, BlockPos pos, @Nullable TileEntity te, ItemStack stack) {
        playerEntity.addStat(Stats.BLOCK_MINED.get(blockState.getBlock()));
        playerEntity.addExhaustion(0.005F);
        List<ItemStack> dropsAbove = Block.getDrops(blockState, (ServerWorld)worldIn, pos, null, playerEntity, stack);
        // -1 seems to mean wherever possible and as much a possible
        dropsAbove.forEach(drop -> playerEntity.inventory.add(-1, drop));
    }

    /**
     * Build the cube in witch non-air blocks will be broken
     * @param worldIn the world to perform check on what kind of block may be broken
     * @param pos the position of the block broken
     * @return
     */
    @Override
    protected List<BlockPos> getBlockPosToBreakRelativeTo(World worldIn, BlockPos pos) {
        int startX;
        int endX;
        int startY;
        int endY;
        int startZ;
        int endZ;

        switch (faceOfBrokenBlock.getOpposite()) {
            case DOWN:
                 startX = pos.getX() - length/2;
                 endX = pos.getX() + length/2;
                 startY = pos.getY() - depth +1; // Y negative && inclusive bound
                 endY = pos.getY();
                 startZ = pos.getZ() - height/2;
                 endZ = pos.getZ() + height/2;
                break;
            case UP:
                startX = pos.getX() - length/2;
                endX = pos.getX() + length/2;
                startY = pos.getY();
                endY = pos.getY() + depth -1;
                startZ = pos.getZ() - height/2;
                endZ = pos.getZ() + height/2;
                break;
            case EAST:
                startX = pos.getX();
                endX = pos.getX() + depth -1;
                startY = pos.getY() -1; // allow breaking at eye and making a room from your feet
                endY = pos.getY() + height -2; // allow breaking at eye and making a room from your feet (and inclusive bound)
                startZ = pos.getZ() - length/2;
                endZ = pos.getZ() + length/2;
                break;
            case WEST:
                startX = pos.getX() - depth + 1; // X negative so x - depth < x
                endX = pos.getX();
                startY = pos.getY() -1;
                endY = pos.getY() + height -2;
                startZ = pos.getZ() - length/2;
                endZ = pos.getZ() + length/2;
                break;
            case NORTH:
                startX = pos.getX() - length/2;
                endX = pos.getX() + length/2;
                startY = pos.getY() -1;
                endY = pos.getY() + height -2;
                startZ = pos.getZ() - depth + 1; // Z negative && inclusive bound
                endZ = pos.getZ();
                break;
            case SOUTH:
                startX = pos.getX() - length/2;
                endX = pos.getX() + length/2;
                startY = pos.getY() -1;
                endY = pos.getY() + height -2;
                startZ = pos.getZ();
                endZ = pos.getZ() + depth -1;
                break;
            default:
                return Collections.emptyList();
        }
        return generateBlockPos(startX, endX, startY, endY, startZ, endZ, worldIn);
    }

    /**
     * Generate all position of non air blocks to be broken
     * @param startX
     * @param endX
     * @param startY
     * @param endY
     * @param startZ
     * @param endZ
     * @param worldIn
     * @return
     */
    private List<BlockPos> generateBlockPos(final int startX, final int endX, final int startY, final int endY, final int startZ, final int endZ, World worldIn){
        List<BlockPos> blockToBeBroken = new ArrayList<>();
        for(int x = startX; x <= endX; x++){
            for(int y = startY; y <= endY; y++){
                for(int z = startZ; z <= endZ; z++){
                    BlockPos pos = new BlockPos(x,y,z);
                    if(canBeBroken(worldIn, pos)){
                        blockToBeBroken.add(pos);
                    }
                }
            }
        }
        return blockToBeBroken;
    }

    public boolean canBeBroken(World worldIn, BlockPos pos){
        return !worldIn.isAirBlock(pos);
    }
}
