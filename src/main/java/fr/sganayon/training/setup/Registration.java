package fr.sganayon.training.setup;

import fr.sganayon.training.blocks.*;
import fr.sganayon.training.dimension.TestModDimension;
import fr.sganayon.training.entities.FirstAnimalEntity;
import fr.sganayon.training.items.*;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static fr.sganayon.training.McTrainingMod.MODID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MODID);
    private static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<FirstBlock> FIRSTBLOCK = BLOCKS.register("firstblock", FirstBlock::new);
    public static final RegistryObject<Item> FIRSTBLOCK_ITEM = ITEMS.register("firstblock", () -> new BlockItem(FIRSTBLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<FirstGenerator> FIRSTGENERATOR = BLOCKS.register("firstgenerator", FirstGenerator::new);
    public static final RegistryObject<Item> FIRSTGENERATOR_ITEM = ITEMS.register("firstgenerator", () -> new BlockItem(FIRSTGENERATOR.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<FirstGeneratorTile>> FIRSTGENERATOR_TILE = TILES.register("firstgenerator", () -> TileEntityType.Builder.create(FirstGeneratorTile::new, FIRSTGENERATOR.get()).build(null));
    public static final RegistryObject<ContainerType<FirstGeneratorContainer>> FIRSTGENERATOR_CONTAINER = CONTAINERS.register("firstgenerator", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new FirstGeneratorContainer(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<FancyBlock> FANCYBLOCK = BLOCKS.register("fancyblock", FancyBlock::new);
    public static final RegistryObject<Item> FANCYBLOCK_ITEM = ITEMS.register("fancyblock", () -> new BlockItem(FANCYBLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<FancyBlockTile>> FANCYBLOCK_TILE = TILES.register("fancyblock", () -> TileEntityType.Builder.create(FancyBlockTile::new, FANCYBLOCK.get()).build(null));

    public static final RegistryObject<MagicBlock> MAGICBLOCK = BLOCKS.register("magicblock", MagicBlock::new);
    public static final RegistryObject<Item> MAGICBLOCK_ITEM = ITEMS.register("magicblock", () -> new BlockItem(MAGICBLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<MagicTile>> MAGICBLOCK_TILE = TILES.register("magicblock", () -> TileEntityType.Builder.create(MagicTile::new, MAGICBLOCK.get()).build(null));

    public static final RegistryObject<ComplexMultipartBlock> COMPLEX_MULTIPART = BLOCKS.register("complex_multipart", ComplexMultipartBlock::new);
    public static final RegistryObject<Item> COMPLEX_MULTIPART_ITEM = ITEMS.register("complex_multipart", () -> new BlockItem(COMPLEX_MULTIPART.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<ComplexMultipartTile>> COMPLEX_MULTIPART_TILE = TILES.register("complex_multipart", () -> TileEntityType.Builder.create(ComplexMultipartTile::new, COMPLEX_MULTIPART.get()).build(null));

    public static final RegistryObject<FirstItem> FIRSTITEM = ITEMS.register("firstitem", FirstItem::new);
    public static final RegistryObject<DoublePickaxe> DOUBLE_IRON_PICKAXE = ITEMS.register("double_iron_pickaxe", () -> new DoublePickaxe(ItemTier.IRON));
    //public static final RegistryObject<CubePickaxe> CUBE_PICKAXE = ITEMS.register("cube_pickaxe", () -> new CubePickaxe(ItemTierMod.OBSI, 5, 5, 5, true, false));
    public static final RegistryObject<CubePickaxe> CUBE_PICKAXE = ITEMS.register("cube_pickaxe", () -> new Obliterator(5,5,64));

    public static final RegistryObject<FirstAnimalEntityEggItem> FIRST_ANIMAL_ENTITY_EGG_ITEM = ITEMS.register("first_animal_entity_egg", FirstAnimalEntityEggItem::new);

    public static final RegistryObject<EntityType<FirstAnimalEntity>> FIRST_ANIMAL_ENTITY = ENTITIES.register("first_animal_entity", () -> EntityType.Builder.create(FirstAnimalEntity::new, EntityClassification.CREATURE)
            .size(1, 1)
            .setShouldReceiveVelocityUpdates(false)
            .build("first_animal_entity"));

    public static final RegistryObject<TestModDimension> DIMENSION = DIMENSIONS.register("first_dimension", TestModDimension::new);
}
