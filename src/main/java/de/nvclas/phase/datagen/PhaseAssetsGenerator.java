package de.nvclas.phase.datagen;

import de.nvclas.phase.datagen.assets.PhaseBlockModelProvider;
import de.nvclas.phase.datagen.assets.PhaseBlockStateProvider;
import de.nvclas.phase.datagen.assets.PhaseEnLanguageProvider;
import de.nvclas.phase.datagen.assets.PhaseItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class PhaseAssetsGenerator {

    public void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        boolean includeClient = event.includeClient();

        generator.addProvider(includeClient, new PhaseEnLanguageProvider(packOutput));
        generator.addProvider(includeClient, new PhaseItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new PhaseBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new PhaseBlockModelProvider(packOutput, existingFileHelper));
    }
}
