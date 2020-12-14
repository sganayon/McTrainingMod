package fr.sganayon.training.items;

import fr.sganayon.training.McTrainingMod;
import fr.sganayon.training.blocks.FirstGenerator;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {
    @ObjectHolder("mc-training-mod:firstitem")
    public static FirstItem FIRSTITEM;

    @ObjectHolder(McTrainingMod.MODID+":first_animal_entity_egg")
    public static FirstAnimalEntityEggItem FIRST_ANIMAL_ENTITY_EGG;
}
