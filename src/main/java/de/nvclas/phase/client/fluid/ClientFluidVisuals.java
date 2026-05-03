package de.nvclas.phase.client.fluid;

import de.nvclas.phase.registries.fluids.PhaseFluidTypes;
import de.nvclas.phase.registries.fluids.PhaseFluids;
import lombok.experimental.UtilityClass;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.joml.Vector3f;

import java.util.List;

@UtilityClass
public class ClientFluidVisuals {
    private static final FluidVisionEffect LIQUID_TIME = new FluidVisionEffect(PhaseFluidTypes.LIQUID_TIME,
            PhaseFluids.LIQUID_TIME, PhaseFluids.FLOWING_LIQUID_TIME, 0.9D, 0x35FFFFFF, 0f, 15f, 50f, 20, 80,
            new Vector3f(200f / 255f, 200f / 255f, 200f / 255f),
            ResourceLocation.withDefaultNamespace("block/water_still"),
            ResourceLocation.withDefaultNamespace("block/water_flow"),
            ResourceLocation.withDefaultNamespace("block/water_overlay"), FastColor.ARGB32.color(200, 255, 255, 255));

    private static final List<FluidVisionEffect> EFFECTS = List.of(LIQUID_TIME);

    public static List<FluidVisionEffect> effects() {
        return EFFECTS;
    }

    public static void setupRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(PhaseFluids.LIQUID_TIME.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(PhaseFluids.FLOWING_LIQUID_TIME.get(), RenderType.translucent());
    }

    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new FluidClientExtension(LIQUID_TIME), PhaseFluidTypes.LIQUID_TIME);
    }
}
