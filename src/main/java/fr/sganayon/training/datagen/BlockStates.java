package fr.sganayon.training.datagen;

import fr.sganayon.training.McTrainingMod;
import fr.sganayon.training.blocks.ComplexMultipartBlock;
import fr.sganayon.training.blocks.ComplexMultipartTile;
import fr.sganayon.training.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, McTrainingMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Make the frame of the cell + transparent glass
        BlockModelBuilder dimCellFrame = models().getBuilder("block/complex/main");

        floatingCube(dimCellFrame, 0f, 0f, 0f, 1f, 16f, 1f);
        floatingCube(dimCellFrame, 15f, 0f, 0f, 16f, 16f, 1f);
        floatingCube(dimCellFrame, 0f, 0f, 15f, 1f, 16f, 16f);
        floatingCube(dimCellFrame, 15f, 0f, 15f, 16f, 16f, 16f);

        floatingCube(dimCellFrame, 1f, 0f, 0f, 15f, 1f, 1f);
        floatingCube(dimCellFrame, 1f, 15f, 0f, 15f, 16f, 1f);
        floatingCube(dimCellFrame, 1f, 0f, 15f, 15f, 1f, 16f);
        floatingCube(dimCellFrame, 1f, 15f, 15f, 15f, 16f, 16f);

        floatingCube(dimCellFrame, 0f, 0f, 1f, 1f, 1f, 15f);
        floatingCube(dimCellFrame, 15f, 0f, 1f, 16f, 1f, 15f);
        floatingCube(dimCellFrame, 0f, 15f, 1f, 1f, 16f, 15f);
        floatingCube(dimCellFrame, 15f, 15f, 1f, 16f, 16f, 15f);

        floatingCube(dimCellFrame, 1f, 1f, 1f, 15f, 15f, 15f);

        dimCellFrame.texture("window", modLoc("block/complex_window"));

        createDimensionalCellModel(Registration.COMPLEX_MULTIPART.get(), dimCellFrame);
    }

    private void floatingCube(BlockModelBuilder builder, float fx, float fy, float fz, float tx, float ty, float tz) {
        builder.element().from(fx, fy, fz).to(tx, ty, tz).allFaces((direction, faceBuilder) -> faceBuilder.texture("#window")).end();
    }

    private void createDimensionalCellModel(Block block, BlockModelBuilder dimCellFrame) {
        BlockModelBuilder singleNone = models().getBuilder("block/complex/singlenone")
                .element().from(3, 3, 3).to(13, 13, 13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/complex"));
        BlockModelBuilder singleIn = models().getBuilder("block/complex/singlein")
                .element().from(3, 3, 3).to(13, 13, 13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/complex_in"));
        BlockModelBuilder singleOut = models().getBuilder("block/complex/singleout")
                .element().from(3, 3, 3).to(13, 13, 13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/complex_out"));

        MultiPartBlockStateBuilder bld = getMultipartBuilder(block);

        bld.part().modelFile(dimCellFrame).addModel();

        BlockModelBuilder[] models = new BlockModelBuilder[] { singleNone, singleIn, singleOut };
        for (ComplexMultipartTile.Mode mode : ComplexMultipartTile.Mode.values()) {
            bld.part().modelFile(models[mode.ordinal()]).addModel().condition(ComplexMultipartBlock.DOWN, mode);
            bld.part().modelFile(models[mode.ordinal()]).rotationX(180).addModel().condition(ComplexMultipartBlock.UP, mode);
            bld.part().modelFile(models[mode.ordinal()]).rotationX(90).addModel().condition(ComplexMultipartBlock.SOUTH, mode);
            bld.part().modelFile(models[mode.ordinal()]).rotationX(270).addModel().condition(ComplexMultipartBlock.NORTH, mode);
            bld.part().modelFile(models[mode.ordinal()]).rotationY(90).rotationX(90).addModel().condition(ComplexMultipartBlock.WEST, mode);
            bld.part().modelFile(models[mode.ordinal()]).rotationY(270).rotationX(90).addModel().condition(ComplexMultipartBlock.EAST, mode);
        }
    }
}
