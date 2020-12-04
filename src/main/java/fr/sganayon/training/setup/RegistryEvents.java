package fr.sganayon.training.setup;

import fr.sganayon.training.blocks.FirstBlock;
import fr.sganayon.training.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
// Event bus for receiving Registry Events)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        // register a new block here
        // block fist then item using the registered block
        LOGGER.info("HELLO from Register Block");
        blockRegistryEvent.getRegistry().register(new FirstBlock());
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        // register a new item here (same registry Name as block)
        LOGGER.info("HELLO from Register item");
        Item.Properties itemProperties = new Item.Properties().group(ModSetup.itemGroup);
        itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.FIRSTBLOCK, itemProperties).setRegistryName("firstblock"));
    }
}
