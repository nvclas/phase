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
        add("item.phase.unstable_phase", "Unstable Phase");
    }
}
