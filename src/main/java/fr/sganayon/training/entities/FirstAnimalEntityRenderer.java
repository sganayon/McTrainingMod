package fr.sganayon.training.entities;

import fr.sganayon.training.McTrainingMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class FirstAnimalEntityRenderer extends MobRenderer<FirstAnimalEntity, FirstAnimalEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(McTrainingMod.MODID, "textures/entity/first_animal_entity.png");

    public FirstAnimalEntityRenderer(EntityRendererManager manager) {
        super(manager, new FirstAnimalEntityModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(FirstAnimalEntity entity) {
        return TEXTURE;
    }
}
