package fr.sganayon.training.items;

import fr.sganayon.training.setup.ModSetup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMultiBreakPickaxe extends PickaxeItem {

    protected static final float DEFAULT_EXHAUSTION = 0.005F;
    protected Direction faceOfBrokenBlock;
    protected boolean autoPickup = false;
    protected boolean obliterate = false;
    protected List<Block> blocksWhiteList = new ArrayList<>();

    protected static final Item.Properties properties = new Item.Properties()
            .maxStackSize(1)
            .group(ModSetup.ITEM_GROUP);

    public AbstractMultiBreakPickaxe(IItemTier tier, boolean autoPickup, boolean obliterate) {
        super(tier, 5, 5, properties);
        this.autoPickup = autoPickup;
        this.obliterate = obliterate;
    }

    /**
     * sub-classes should return all blocks position to be broken when a block is broken by the multiBreakPickaxe
     * @param worldIn the world to perform check on what kind of block may be broken
     * @param pos the position of the block broken
     * @return all position to be broken too
     */
    protected abstract List<BlockPos> getBlockPosToBreakRelativeTo(World worldIn, BlockPos pos);

    /**
     * sub-classes may override this to control the harvest process (get drops, increase the mining stats of that player, increase exhaustion, etc...)
     * @param blockState the state of the block being broken
     * @param worldIn the world
     * @param player the player that broke the blocks
     * @param pos the pos of the broken block
     * @param te the tile entity if any (/!\ Always null for the moment)
     * @param stack the tool that was used to break the block
     */
    protected void harvestBrokenBlock(BlockState blockState, World worldIn, PlayerEntity player, BlockPos pos, @Nullable TileEntity te, ItemStack stack) {
        if (autoPickup) {
            List<ItemStack> drops = Block.getDrops(blockState, (ServerWorld)worldIn, pos, null, player, stack);
            drops.forEach(drop -> {
               player.inventory.add(-1, drop);
                if(!drop.isEmpty()){
                    Block.spawnAsEntity(worldIn, player.getPosition(), drop);
                }
            });
        } else {
            Block.spawnDrops(blockState, worldIn, pos, te, player, stack);
        }
    }

    /**
     * sub-classes may override this to control the exhaustion of the use of the pickaxe
     * Called per block broken by the pickaxe
     * @return the exhaustion that will be added to the player for one block broken
     */
    protected float getExhaustion(){
        return DEFAULT_EXHAUSTION;
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
       return this.getTier().getHarvestLevel() >= blockIn.getHarvestLevel();
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        PlayerEntity player = (PlayerEntity) entityLiving;
        List<BlockPos> blocksToBeBroken = getBlockPosToBreakRelativeTo(worldIn, pos);
        // Add exhaustion relative to the number of block broken (there will be the first block's exhaustion and the pickaxe exhaustion for every block broken)
        player.addExhaustion(getExhaustion() * blocksToBeBroken.size());
        blocksToBeBroken.forEach(blockPos -> {
            BlockState blockState = worldIn.getBlockState(blockPos);
            // Add stats
            player.addStat(Stats.BLOCK_MINED.get(blockState.getBlock()));
            // Harvest the block if the pickaxe isn't in obliterate mode
            if(!obliterate) {
                harvestBrokenBlock(blockState,worldIn, player, blockPos, null, stack);
            }
            // Remove the block (RemoveByPlayer = replace by air)
            blockState.getBlock().removedByPlayer(blockState, worldIn, blockPos,  player, true, null);
        });

        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    public void setFaceOfBrokenBlock(Direction faceOfBrokenBlock) {
        this.faceOfBrokenBlock = faceOfBrokenBlock;
    }

    public void setAutoPickup(boolean autoPickup) {
        this.autoPickup = autoPickup;
    }
}
