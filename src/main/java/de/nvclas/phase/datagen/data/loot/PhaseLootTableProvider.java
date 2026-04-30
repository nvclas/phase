package de.nvclas.phase.datagen.data.loot;

import de.nvclas.phase.datagen.data.loot.subprovider.PhaseBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class PhaseLootTableProvider extends LootTableProvider {
    public PhaseLootTableProvider(PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(),
                List.of(new SubProviderEntry(PhaseBlockLootSubProvider::new, LootContextParamSets.BLOCK)), registries);
    }
}
