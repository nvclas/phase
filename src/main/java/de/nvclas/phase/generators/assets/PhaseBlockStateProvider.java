package de.nvclas.phase.generators.assets;

import de.nvclas.phase.Phase;
import de.nvclas.phase.registries.PhaseBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PhaseBlockStateProvider extends BlockStateProvider {

    private static final String BLOCK_PATH = "block/";

    public PhaseBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Phase.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        randomRotatingBlockWithItem(PhaseBlocks.FRACTURED_STONE.get(), cubeAll(PhaseBlocks.FRACTURED_STONE.get()), 0, 180);
        randomRotatingBlockWithItem(PhaseBlocks.FRACTURED_COBBLESTONE.get(), cubeAll(PhaseBlocks.FRACTURED_COBBLESTONE.get()), 0, 180);
        randomRotatingBlockWithItem(PhaseBlocks.PULSING_GRASS.get(),
                models().cubeBottomTop(
                        PhaseBlocks.PULSING_GRASS.getId().getPath(),
                        modLoc(BLOCK_PATH + PhaseBlocks.PULSING_GRASS.getId().getPath() + "_side"),
                        modLoc(BLOCK_PATH + PhaseBlocks.PULSING_DIRT.getId().getPath()),
                        modLoc(BLOCK_PATH + PhaseBlocks.PULSING_GRASS.getId().getPath() + "_top")
                ), 0, 90, 180, 270);
        randomRotatingBlockWithItem(PhaseBlocks.PULSING_DIRT.get(), cubeAll(PhaseBlocks.PULSING_DIRT.get()), 0, 90, 180, 270);
    }

    private void randomRotatingBlockWithItem(Block block, ModelFile model, int... rotations) {
        ConfiguredModel[] variants = new ConfiguredModel[rotations.length];
        for (int i = 0; i < rotations.length; i++) {
            variants[i] = new ConfiguredModel(model, 0, rotations[i], false);
        }
        getVariantBuilder(block).partialState().setModels(variants);
        simpleBlockItem(block, model);
    }
}
