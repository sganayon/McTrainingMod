package fr.sganayon.training.entities;

import fr.sganayon.training.McTrainingMod;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModEntities {

    @ObjectHolder(McTrainingMod.MODID+":first_animal_entity")
    public static EntityType<FirstAnimalEntity> FIRSTANIMALENTITY;

}
