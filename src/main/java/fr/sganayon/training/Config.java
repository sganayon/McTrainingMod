package fr.sganayon.training;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_POWER = "power";
    public static final String SUBCATEGORY_FIRSTGENERATOR = "firstgenerator";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    //ALSO SERVER and PLAYER ONLY

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;


    public static ForgeConfigSpec.IntValue FIRSTGENERATOR_MAXPOWER;
    public static ForgeConfigSpec.IntValue FIRSTGENERATOR_MAXTRANSFER;
    public static ForgeConfigSpec.IntValue FIRSTGENERATOR_GENERATE;
    public static ForgeConfigSpec.IntValue FIRSTGENERATOR_GENERATE_SPEED;
    public static ForgeConfigSpec.IntValue FIRSTGENERATOR_SEND;


    static {

        COMMON_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        // General settings
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Power settings").push(CATEGORY_POWER);
        // Power settings
        setupFirstBlockConfig();
        COMMON_BUILDER.pop();


        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupFirstBlockConfig() {
        COMMON_BUILDER.comment("FirstGenerator settings").push(SUBCATEGORY_FIRSTGENERATOR);

        FIRSTGENERATOR_MAXPOWER = COMMON_BUILDER.comment("Maximum power for the first generator ")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
        FIRSTGENERATOR_MAXTRANSFER = COMMON_BUILDER.comment("Maximum power transfer into first generator")
                .defineInRange("maxPowerTransferInto", 0, 0, Integer.MAX_VALUE);
        FIRSTGENERATOR_GENERATE = COMMON_BUILDER.comment("Power generation per diamond")
                .defineInRange("generate", 1000, 0, Integer.MAX_VALUE);
        FIRSTGENERATOR_GENERATE_SPEED = COMMON_BUILDER.comment("Power generation speed in ticks")
                .defineInRange("generation_speed", 20, 0, Integer.MAX_VALUE);
        FIRSTGENERATOR_SEND = COMMON_BUILDER.comment("Power generation to send per tick")
                .defineInRange("send", 100, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    //Finish loading config
    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    // Config changes
    @SubscribeEvent
    public static void onReload(final ModConfig.ConfigReloading configEvent) {
    }

}
