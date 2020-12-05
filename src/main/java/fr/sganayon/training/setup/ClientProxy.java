package fr.sganayon.training.setup;

import fr.sganayon.training.blocks.FirstGeneratorScreen;
import fr.sganayon.training.blocks.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy{
    @Override
    public void init() {
        // register gui on the client
        ScreenManager.registerFactory(ModBlocks.FIRSTGENERATOR_CONTAINER, FirstGeneratorScreen::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
