package fr.sganayon.training.blocks;

import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    //Trick to get sure that firstBlock is created (usefull for items ou if mod depend of others mods blocks)
    @ObjectHolder("mc-training-mod:firstblock")
    public static FirstBlock FIRSTBLOCK;
}
