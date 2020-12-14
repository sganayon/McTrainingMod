package fr.sganayon.training.entities;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class FirstAnimalEntityModel extends EntityModel<FirstAnimalEntity> {
    public RendererModel body;

    public FirstAnimalEntityModel() {
        body = new RendererModel(this, 0, 0);
        body.func_78789_a(-3, -3, -3, 6, 6, 6);
    }

    @Override
    public void func_212844_a_(FirstAnimalEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

    }

    @Override
    public void func_78088_a(FirstAnimalEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        func_212844_a_(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        body.func_78785_a(scale);
//        body.func_78791_b(scale);
//        body.func_78794_c(scale);
    }
}
