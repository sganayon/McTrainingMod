package fr.sganayon.training.setup;

import fr.sganayon.training.blocks.FancyModelLoader;
import fr.sganayon.training.blocks.FirstGeneratorScreen;
import fr.sganayon.training.entities.FirstAnimalEntityRenderer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static fr.sganayon.training.McTrainingMod.MODID;

public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        // register gui on the client
        ScreenManager.registerFactory(Registration.FIRSTGENERATOR_CONTAINER.get(), FirstGeneratorScreen::new);

        // register entity on the client
        RenderingRegistry.registerEntityRenderingHandler(Registration.FIRST_ANIMAL_ENTITY.get(), FirstAnimalEntityRenderer::new);

        // register model loader on the client
        ModelLoaderRegistry.registerLoader(new ResourceLocation(MODID, "fancyloader"), new FancyModelLoader());
    }
}
