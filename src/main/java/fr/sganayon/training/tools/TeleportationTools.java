package fr.sganayon.training.tools;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class TeleportationTools {

    public static void teleport(ServerPlayerEntity entity, DimensionType destination) {
        entity.changeDimension(destination);
    }
}
