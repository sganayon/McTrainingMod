package fr.sganayon.training.setup;

import fr.sganayon.training.blocks.FancyModelLoader;
import fr.sganayon.training.blocks.FirstGeneratorScreen;
import fr.sganayon.training.blocks.MagicRenderer;
import fr.sganayon.training.entities.FirstAnimalEntityRenderer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static fr.sganayon.training.McTrainingMod.MODID;

public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        // register gui on the client
        ScreenManager.registerFactory(Registration.FIRSTGENERATOR_CONTAINER.get(), FirstGeneratorScreen::new);

        // register entity on the client
        RenderingRegistry.registerEntityRenderingHandler(Registration.FIRST_ANIMAL_ENTITY.get(), FirstAnimalEntityRenderer::new);

        // register the tile entity renderer for magic block
        MagicRenderer.register();

        // register model loader on the client
        ModelLoaderRegistry.registerLoader(new ResourceLocation(MODID, "fancyloader"), new FancyModelLoader());

        RenderTypeLookup.setRenderLayer(Registration.COMPLEX_MULTIPART.get(), RenderType.getTranslucent());
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event){
        if(!event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)){
            return;
        }
        event.addSprite(MagicRenderer.MAGICBLOCK_TEXTURE);
    }
}
