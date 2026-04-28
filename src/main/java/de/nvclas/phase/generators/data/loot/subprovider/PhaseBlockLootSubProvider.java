package de.nvclas.phase.generators.data.loot.subprovider;

import de.nvclas.phase.registries.PhaseBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class PhaseBlockLootSubProvider extends BlockLootSubProvider {
    public PhaseBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected void generate() {
        add(PhaseBlocks.FRACTURED_STONE.get(),
                block -> createSingleItemTableWithSilkTouch(block, PhaseBlocks.COBBLED_FRACTURED_STONE.get()));
        dropSelf(PhaseBlocks.COBBLED_FRACTURED_STONE.get());
        add(PhaseBlocks.PULSING_GRASS.get(),
                block -> createSingleItemTableWithSilkTouch(block, PhaseBlocks.PULSING_DIRT.get()));
        dropSelf(PhaseBlocks.PULSING_DIRT.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return PhaseBlocks.BLOCKS.getEntries()
                .stream()
                .map(holder -> holder.getDelegate().value())
                .toList();
    }
}
