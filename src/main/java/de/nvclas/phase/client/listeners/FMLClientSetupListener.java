package de.nvclas.phase.client.listeners;

import de.nvclas.phase.registries.PhaseFluidRegistries;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class FMLClientSetupListener {

    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(PhaseFluidRegistries.PhaseFluids.LIQUID_TIME.get(),
                RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(PhaseFluidRegistries.PhaseFluids.FLOWING_LIQUID_TIME.get(),
                RenderType.translucent());

    }

}
