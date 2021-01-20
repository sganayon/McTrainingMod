package fr.sganayon.training.dimension;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.*;

public class TestBiomeProvider extends BiomeProvider {
    private final Biome biome;
    private static final List<Biome> SPAWN = Collections.singletonList(Biomes.PLAINS);

    public TestBiomeProvider() {
        super(new HashSet<>(SPAWN));
        biome = Biomes.PLAINS;
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return SPAWN;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return biome;
    }

    private Biome[] getBiomes(int x, int y, int width, int height, boolean b) {
        Biome[] biomes = new Biome[width * height];
        Arrays.fill(biomes, biome);
        return biomes;
    }

    @Override
    public boolean hasStructure(Structure<?> structure) {
        return false;
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (topBlocksCache.isEmpty()) {
            topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
        }
        return topBlocksCache;
    }
}
