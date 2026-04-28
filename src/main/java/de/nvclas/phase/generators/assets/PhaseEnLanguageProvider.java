package de.nvclas.phase.generators.assets;

import de.nvclas.phase.Phase;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class PhaseEnLanguageProvider extends LanguageProvider {

    public PhaseEnLanguageProvider(PackOutput output) {
        super(output, Phase.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Items
        add("item.phase.unstable_phase", "Unstable Phase");

        // Blocks
        add("block.phase.fractured_stone", "Fractured Stone");
        add("block.phase.cobbled_fractured_stone", "Cobbled Fractured Stone");
        add("block.phase.pulsing_grass", "Pulsing Grass");
        add("block.phase.pulsing_dirt", "Pulsing Dirt");

        // Creative Tab
        add("itemGroup.phase", "Phase");
    }
}
