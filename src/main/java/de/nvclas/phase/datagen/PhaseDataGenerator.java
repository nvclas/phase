package de.nvclas.phase.datagen;

import de.nvclas.phase.datagen.data.loot.PhaseLootTableProvider;
import de.nvclas.phase.datagen.data.tags.PhaseBlockTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class PhaseDataGenerator {

    public void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean includeServer = event.includeServer();

        generator.addProvider(includeServer,
                new PhaseBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(includeServer, new PhaseLootTableProvider(packOutput, lookupProvider));
    }
}
