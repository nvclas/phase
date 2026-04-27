package de.nvclas.phase.generators.assets;

import de.nvclas.phase.Phase;
import de.nvclas.phase.registries.PhaseItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PhaseItemModelProvider extends ItemModelProvider {

    public PhaseItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, Phase.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(PhaseItems.UNSTABLE_PHASE.get());
    }
}
