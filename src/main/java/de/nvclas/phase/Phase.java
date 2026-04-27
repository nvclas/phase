package de.nvclas.phase;

import com.mojang.logging.LogUtils;
import de.nvclas.phase.generators.AssetsGenerator;
import de.nvclas.phase.generators.DataGenerator;
import de.nvclas.phase.registries.PhaseBlocks;
import de.nvclas.phase.registries.PhaseCreativeTabs;
import de.nvclas.phase.registries.PhaseItems;
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
        PhaseItems.ITEMS.register(modEventBus);
        PhaseBlocks.BLOCKS.register(modEventBus);
        PhaseCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Liseners
        modEventBus.addListener(PhaseCreativeTabs::addToCreativeTabs);
        modEventBus.addListener(this::generateData);
    }

    private void generateData(GatherDataEvent event) {
        new AssetsGenerator().generate(event);
        new DataGenerator().generate(event);
    }

}
