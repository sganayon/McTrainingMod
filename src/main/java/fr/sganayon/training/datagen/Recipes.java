package fr.sganayon.training.datagen;

import fr.sganayon.training.setup.Registration;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        //.setGroup() add to the right panel (standard, combat, etc...)
        ShapedRecipeBuilder.shapedRecipe(Registration.FIRSTBLOCK.get())
                .patternLine("xxx")
                .patternLine("x#x")
                .patternLine("xxx")
                .key('x', Blocks.COBBLESTONE)
                .key('#', Tags.Items.DYES_RED)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Registration.FIRSTGENERATOR.get())
                .patternLine("xxx")
                .patternLine("x x")
                .patternLine("xxx")
                .key('x', Registration.FIRSTBLOCK.get())
                .addCriterion("FIRSTBLOCK", InventoryChangeTrigger.Instance.forItems(Registration.FIRSTBLOCK.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Registration.DOUBLE_IRON_PICKAXE.get())
                .patternLine(" x ")
                .patternLine(" x ")
                .patternLine(" i ")
                .key('x', Items.IRON_PICKAXE)
                .key('i', Items.IRON_INGOT)
                .addCriterion("iron_pickaxe", InventoryChangeTrigger.Instance.forItems(Items.IRON_PICKAXE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Registration.CUBE_PICKAXE.get())
                .patternLine("xxx")
                .patternLine("ixi")
                .patternLine(" i ")
                .key('x', Items.OBSIDIAN)
                .key('i', Items.IRON_INGOT)
                .addCriterion("iron_pickaxe", InventoryChangeTrigger.Instance.forItems(Items.IRON_PICKAXE))
                .build(consumer);
    }
}
