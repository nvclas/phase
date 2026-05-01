package de.nvclas.phase.datagen.assets;

import de.nvclas.phase.Phase;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PhaseBlockModelProvider extends BlockModelProvider {


    public PhaseBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Phase.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("liquid_time_block", mcLoc("block/water"));
    }
}
