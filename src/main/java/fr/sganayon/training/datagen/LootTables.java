package fr.sganayon.training.datagen;

import fr.sganayon.training.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.FIRSTBLOCK, createStandardTable("firstblock", ModBlocks.FIRSTBLOCK));
        lootTables.put(ModBlocks.FIRSTGENERATOR, createStandardTable("firstgenerator", ModBlocks.FIRSTGENERATOR));
    }
}
