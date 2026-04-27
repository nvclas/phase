package de.nvclas.phase.generators.assets;

import de.nvclas.phase.Phase;
import de.nvclas.phase.registries.PhaseBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PhaseBlockStateProvider extends BlockStateProvider {
    public PhaseBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Phase.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(PhaseBlocks.FRACTURED_STONE.get(), cubeAll(PhaseBlocks.FRACTURED_STONE.get()));
    }
}
