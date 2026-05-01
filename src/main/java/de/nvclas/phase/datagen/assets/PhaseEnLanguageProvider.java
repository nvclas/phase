package de.nvclas.phase.datagen.assets;

import de.nvclas.phase.Phase;
import de.nvclas.phase.registries.PhaseBlocks;
import de.nvclas.phase.registries.PhaseItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Locale;
import java.util.function.Supplier;

public class PhaseEnLanguageProvider extends LanguageProvider {

    public PhaseEnLanguageProvider(PackOutput output) {
        super(output, Phase.MODID, Locale.US.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        // Items
        addItem(PhaseItems.UNSTABLE_PHASE, "Unstable Phase");

        // Blocks
        addBlock(PhaseBlocks.FRACTURED_STONE, "Fractured Stone");
        addBlock(PhaseBlocks.FRACTURED_COBBLESTONE, "Fractured Cobblestone");
        addBlock(PhaseBlocks.PULSING_GRASS, "Pulsing Grass");
        addBlock(PhaseBlocks.PULSING_DIRT, "Pulsing Dirt");

        // Fluids
        addFluid(PhaseBlocks.LIQUID_TIME_BLOCK, PhaseItems.LIQUID_TIME_BUCKET, "Liquid Time");

        // Creative Tab
        add("itemGroup.phase", "Phase");
    }

    private void addFluid(Supplier<? extends LiquidBlock> block, Supplier<? extends BucketItem> bucket, String name) {
        addBlock(block, name + " Block");
        add(block.get().fluid.getFluidType().getDescriptionId(), name);
        addItem(bucket, name + " Bucket");

    }
}
