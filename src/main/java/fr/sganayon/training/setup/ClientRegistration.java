package fr.sganayon.training.setup;

import fr.sganayon.training.McTrainingMod;
import fr.sganayon.training.items.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = McTrainingMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {

    @SubscribeEvent
    public static void onItemColor(ColorHandlerEvent.Item event) {
        // Color egg items
        event.getItemColors().register((stack, i) -> 0xff0000, ModItems.FIRST_ANIMAL_ENTITY_EGG);
    }
}
