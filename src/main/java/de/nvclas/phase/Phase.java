package de.nvclas.phase;

import com.mojang.logging.LogUtils;
import de.nvclas.phase.client.listeners.FMLClientSetupListener;
import de.nvclas.phase.client.listeners.RegisterClientExtensionsListener;
import de.nvclas.phase.datagen.PhaseAssetsGenerator;
import de.nvclas.phase.datagen.PhaseDataGenerator;
import de.nvclas.phase.registries.PhaseBlocks;
import de.nvclas.phase.registries.PhaseCreativeTabs;
import de.nvclas.phase.registries.PhaseFluidRegistries;
import de.nvclas.phase.registries.PhaseItems;
import de.nvclas.phase.registries.PhaseSounds;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

@Mod(Phase.MODID)
public class Phase {
    public static final String MODID = "phase";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Phase(IEventBus modEventBus, ModContainer modContainer) {
        // Content
        PhaseItems.register(modEventBus);
        PhaseBlocks.register(modEventBus);
        PhaseFluidRegistries.register(modEventBus);
        PhaseSounds.register(modEventBus);
        PhaseCreativeTabs.register(modEventBus);

        // Listeners
        modEventBus.addListener(this::generateData);
        modEventBus.addListener(PhaseCreativeTabs::addToCreativeTabs);
        modEventBus.addListener(RegisterClientExtensionsListener::onRegisterClientExtensions);
        modEventBus.addListener(FMLClientSetupListener::onFMLClientSetup);
    }

    private void generateData(GatherDataEvent event) {
        new PhaseAssetsGenerator().generate(event);
        new PhaseDataGenerator().generate(event);
    }

}
