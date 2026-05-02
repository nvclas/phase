package de.nvclas.phase.datagen.assets;

import de.nvclas.phase.Phase;
import de.nvclas.phase.registries.PhaseBlocks;
import de.nvclas.phase.registries.PhaseItems;
import de.nvclas.phase.registries.fluids.PhaseLiquidBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class PhaseEnLanguageProvider extends LanguageProvider {

    public PhaseEnLanguageProvider(PackOutput output) {
        super(output, Phase.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Items
        addItem(PhaseItems.UNSTABLE_PHASE, "Unstable Phase");
        addItem(PhaseItems.LIQUID_TIME_BUCKET, "Liquid Time Bucket");

        // Blocks
        addBlock(PhaseBlocks.FRACTURED_STONE, "Fractured Stone");
        addBlock(PhaseBlocks.FRACTURED_COBBLESTONE, "Fractured Cobblestone");
        addBlock(PhaseBlocks.PULSING_GRASS, "Pulsing Grass");
        addBlock(PhaseBlocks.PULSING_DIRT, "Pulsing Dirt");

        // Liquids
        addBlock(PhaseLiquidBlocks.LIQUID_TIME, "Liquid Time");

        // Creative Tab
        add("itemGroup.phase", "Phase");
    }
}
