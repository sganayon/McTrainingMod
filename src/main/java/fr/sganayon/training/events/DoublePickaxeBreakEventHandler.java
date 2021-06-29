package fr.sganayon.training.events;

import fr.sganayon.training.items.AbstractMultiBreakPickaxe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DoublePickaxeBreakEventHandler {

    @SubscribeEvent
    public static void LeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if(event.getItemStack().getItem() instanceof AbstractMultiBreakPickaxe) {
            ((AbstractMultiBreakPickaxe) event.getItemStack().getItem()).setFaceOfBrokenBlock(event.getFace());
        }
    }
}
