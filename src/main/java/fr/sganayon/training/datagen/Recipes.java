package fr.sganayon.training.datagen;

import fr.sganayon.training.McTrainingMod;
import fr.sganayon.training.blocks.ModBlocks;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        //.setGroup() add to the right panel (standard, combat, etc...)
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.FIRSTBLOCK)
                .patternLine("xxx")
                .patternLine("x#x")
                .patternLine("xxx")
                .key('x', Blocks.COBBLESTONE)
                .key('#', Tags.Items.DYES_RED)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.FIRSTGENERATOR)
                .patternLine("xxx")
                .patternLine("x x")
                .patternLine("xxx")
                .key('x', ModBlocks.FIRSTBLOCK)
                .addCriterion("FIRSTBLOCK", InventoryChangeTrigger.Instance.forItems(ModBlocks.FIRSTBLOCK))
                .build(consumer);
    }
}
