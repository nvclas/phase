package de.nvclas.phase.generators.data.tags;

import de.nvclas.phase.Phase;
import de.nvclas.phase.registries.PhaseBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PhaseBlockTagsProvider extends BlockTagsProvider {

    public PhaseBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Phase.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // Best tool
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(PhaseBlocks.FRACTURED_STONE.get(),
                PhaseBlocks.COBBLED_FRACTURED_STONE.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(PhaseBlocks.PULSING_GRASS.get(), PhaseBlocks.PULSING_DIRT.get());

        // Needed tool level
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(PhaseBlocks.FRACTURED_STONE.get(), PhaseBlocks.PULSING_GRASS.get(),
                PhaseBlocks.PULSING_DIRT.get(), PhaseBlocks.COBBLED_FRACTURED_STONE.get());
    }


}
