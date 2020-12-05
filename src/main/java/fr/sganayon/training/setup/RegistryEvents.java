package fr.sganayon.training.setup;

import fr.sganayon.training.McTrainingMod;
import fr.sganayon.training.blocks.*;
import fr.sganayon.training.items.FirstItem;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
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
        LOGGER.info("Block registration for mc-training-mod");

        blockRegistryEvent.getRegistry().register(new FirstBlock());
        blockRegistryEvent.getRegistry().register(new FirstGenerator());
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        // register a new item here (same registry Name as block)
        LOGGER.info("item registration for mc-training-mod");

        // add to creative tab
        Item.Properties itemProperties = new Item.Properties().group(ModSetup.itemGroup);

        // block item
        itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.FIRSTBLOCK, itemProperties).setRegistryName("firstblock"));
        itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.FIRSTGENERATOR, itemProperties).setRegistryName("firstgenerator"));

        // item
        itemRegistryEvent.getRegistry().register(new FirstItem());
    }

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> tileEntityRegistryEvent){
        // register a new item here (same registry Name as block)
        LOGGER.info("tileEntity registration for mc-training-mod");

        tileEntityRegistryEvent.getRegistry().register(TileEntityType.Builder.create(FirstGeneratorTile::new, ModBlocks.FIRSTGENERATOR).build(null).setRegistryName("firstgenerator"));
    }

    // Only for client
    @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> containerRegistryEvent){
        // register a new container here (same registry Name as block)
        LOGGER.info("container registration for mc-training-mod");

        containerRegistryEvent.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
            BlockPos blockPos = data.readBlockPos();
            return new FirstGeneratorContainer(windowId, McTrainingMod.proxy.getClientWorld(), blockPos, inv, McTrainingMod.proxy.getClientPlayer());
        }).setRegistryName("firstgenerator"));
    }
}
