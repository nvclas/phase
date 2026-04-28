package de.nvclas.phase.generators.assets;

import de.nvclas.phase.Phase;
import de.nvclas.phase.registries.PhaseBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PhaseBlockStateProvider extends BlockStateProvider {

    private static final String BLOCK_PATH = "block/";

    public PhaseBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Phase.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(PhaseBlocks.FRACTURED_STONE.get(), cubeAll(PhaseBlocks.FRACTURED_STONE.get()));
        simpleBlockWithItem(PhaseBlocks.COBBLED_FRACTURED_STONE.get(),
                cubeAll(PhaseBlocks.COBBLED_FRACTURED_STONE.get()));
        simpleBlockWithItem(PhaseBlocks.PULSING_GRASS.get(),
                models().cubeBottomTop(
                        PhaseBlocks.PULSING_GRASS.getId().getPath(),
                        modLoc(BLOCK_PATH + PhaseBlocks.PULSING_GRASS.getId().getPath() + "_side"),
                        modLoc(BLOCK_PATH + PhaseBlocks.PULSING_DIRT.getId().getPath()),
                        modLoc(BLOCK_PATH + PhaseBlocks.PULSING_GRASS.getId().getPath() + "_top")
                )
        );
        simpleBlockWithItem(PhaseBlocks.PULSING_DIRT.get(), cubeAll(PhaseBlocks.PULSING_DIRT.get()));

    }
}
