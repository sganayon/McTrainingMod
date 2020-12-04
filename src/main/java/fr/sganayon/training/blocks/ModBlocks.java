package fr.sganayon.training.blocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    //Trick to get sure that firstBlock is created (usefull for items ou if mod depend of others mods blocks)
    @ObjectHolder("mc-training-mod:firstblock")
    public static FirstBlock FIRSTBLOCK;

    @ObjectHolder("mc-training-mod:firstgenerator")
    public static FirstGenerator FIRSTGENERATOR;

    @ObjectHolder("mc-training-mod:firstgenerator")
    public static TileEntityType<FirstGeneratorTile> FIRSTGENERATOR_TILE;
}
