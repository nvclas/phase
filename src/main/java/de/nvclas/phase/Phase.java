package de.nvclas.phase;

import com.mojang.logging.LogUtils;
import de.nvclas.phase.client.fluid.FluidVisionEffectManager;
import de.nvclas.phase.client.listeners.ClientEventListeners;
import de.nvclas.phase.content.fluids.LiquidTimeFluid;
import de.nvclas.phase.content.sounds.LiquidTimeAmbientSound;
import de.nvclas.phase.datagen.PhaseAssetsGenerator;
import de.nvclas.phase.datagen.PhaseDataGenerator;
import de.nvclas.phase.registries.PhaseBlocks;
import de.nvclas.phase.registries.PhaseCreativeTabs;
import de.nvclas.phase.registries.PhaseItems;
import de.nvclas.phase.registries.PhaseSounds;
import de.nvclas.phase.registries.fluids.PhaseFluidTypes;
import de.nvclas.phase.registries.fluids.PhaseFluids;
import de.nvclas.phase.registries.fluids.PhaseLiquidBlocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
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
        PhaseFluids.register(modEventBus);
        PhaseFluidTypes.register(modEventBus);
        PhaseLiquidBlocks.register(modEventBus);
        PhaseSounds.register(modEventBus);
        PhaseCreativeTabs.register(modEventBus);

        // Listeners
        modEventBus.addListener(this::generateData);
        modEventBus.addListener(PhaseCreativeTabs::addToCreativeTabs);
        NeoForge.EVENT_BUS.addListener(LiquidTimeFluid::onCropGrow);
        NeoForge.EVENT_BUS.addListener(LiquidTimeFluid::onBreakSpeed);
        NeoForge.EVENT_BUS.addListener(LiquidTimeFluid::onEntityTick);

        if (FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(ClientEventListeners::onRegisterClientExtensions);
            modEventBus.addListener(ClientEventListeners::onClientSetup);
            NeoForge.EVENT_BUS.addListener(FluidVisionEffectManager::onComputeFov);
            NeoForge.EVENT_BUS.addListener(FluidVisionEffectManager::onRenderOverlay);
            NeoForge.EVENT_BUS.addListener(FluidVisionEffectManager::onClientTick);
            NeoForge.EVENT_BUS.addListener(LiquidTimeAmbientSound::onClientTick);
        }
    }

    private void generateData(GatherDataEvent event) {
        new PhaseAssetsGenerator().generate(event);
        new PhaseDataGenerator().generate(event);
    }

}
