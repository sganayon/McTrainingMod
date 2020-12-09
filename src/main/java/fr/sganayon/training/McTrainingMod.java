package fr.sganayon.training;

import fr.sganayon.training.setup.*;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("mc-training-mod")
public class McTrainingMod {
    public static final String MODID = "mc-training-mod";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static ModSetup setup = new ModSetup();

    // Double lambda for class contruction things, optional appearently
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static RegistryEvents registryEvents;

    public McTrainingMod() {
        // Register config file
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Load config file
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("mc-training-mod-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("mc-training-mod-common.toml"));
    }

    // After all things have been registered
    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        setup.init();
        proxy.init();
    }


}
