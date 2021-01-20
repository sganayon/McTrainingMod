package fr.sganayon.training.dimension;

import fr.sganayon.training.McTrainingMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.ObjectHolder;

public class ModDimensions {

    public static final ResourceLocation DIMENSION_ID = new ResourceLocation(McTrainingMod.MODID, "dimension");

    @ObjectHolder(McTrainingMod.MODID+":dimension")
    public static ModDimension DIMENSION;

    public static DimensionType DIMENSION_TYPE;
}
