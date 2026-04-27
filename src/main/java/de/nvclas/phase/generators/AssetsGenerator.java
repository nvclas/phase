package de.nvclas.phase.generators;

import de.nvclas.phase.generators.assets.PhaseBlockStateProvider;
import de.nvclas.phase.generators.assets.PhaseEnLanguageProvider;
import de.nvclas.phase.generators.assets.PhaseItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class AssetsGenerator {

    public void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new PhaseEnLanguageProvider(packOutput));
        generator.addProvider(true, new PhaseItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(true, new PhaseBlockStateProvider(packOutput, existingFileHelper));
    }
}
