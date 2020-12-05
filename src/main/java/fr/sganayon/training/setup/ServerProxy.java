package fr.sganayon.training.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {
    @Override
    public void init() {

    }

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Run getClientWorld only on the client !");
    }

    @Override
    public PlayerEntity getClientPlayer() {
        throw new IllegalStateException("Run getClientPlayer only on the client !");
    }
}
