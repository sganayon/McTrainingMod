package fr.sganayon.training.items;

import fr.sganayon.training.setup.ModSetup;
import fr.sganayon.training.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.stats.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class FirstItem extends PickaxeItem {

    private static final Item.Properties properties = new Item.Properties()
            .maxStackSize(1)
            .group(ModSetup.ITEM_GROUP);

    private static final IItemTier itemTier = ItemTier.IRON;

    public FirstItem() {
        super(itemTier,5,5, properties);
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        PlayerEntity player = (PlayerEntity) entityLiving;
        BlockPos blockPosAboveTargeted = new BlockPos(pos.getX(), pos.getY() + 1 , pos.getZ());
        BlockState blockStateAboveTargeted = worldIn.getBlockState(blockPosAboveTargeted);
        Block blockAboveTargeted = blockStateAboveTargeted.getBlock();

        player.addStat(Stats.BLOCK_MINED.get(blockAboveTargeted));
        player.addExhaustion(0.005F);

//        //block broken by the player (/!\ the block broken by the player will be dropped anyway !)
//        List<ItemStack> drops = Block.getDrops(state, (ServerWorld)worldIn, pos, null, player, stack);
//        // -1 seems to mean wherever possible and as much a possible
//        drops.forEach(drop -> player.inventory.add(-1, drop));

        //block above
        List<ItemStack> dropsAbove = Block.getDrops(blockStateAboveTargeted, (ServerWorld)worldIn, blockPosAboveTargeted, null, player, stack);
        // -1 seems to mean wherever possible and as much a possible
        dropsAbove.forEach(drop -> player.inventory.add(-1, drop));

        blockStateAboveTargeted.getBlock().removedByPlayer(blockStateAboveTargeted, worldIn, blockPosAboveTargeted, (PlayerEntity) entityLiving , true, null);
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }
}
