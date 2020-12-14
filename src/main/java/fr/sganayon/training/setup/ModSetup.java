package fr.sganayon.training.setup;

import fr.sganayon.training.McTrainingMod;
import fr.sganayon.training.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {

    // creative tab
    public static final ItemGroup itemGroup = new ItemGroup(McTrainingMod.MODID) {
        // item of the tab shown in creative tab
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.FIRSTBLOCK);
        }
    };

    public void init() {

    }
}
