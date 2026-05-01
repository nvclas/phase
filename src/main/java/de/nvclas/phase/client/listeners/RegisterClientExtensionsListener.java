package de.nvclas.phase.client.listeners;

import de.nvclas.phase.registries.PhaseFluidRegistries;
import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class RegisterClientExtensionsListener {
    private static final ResourceLocation LIQUID_TIME_STILL = ResourceLocation.withDefaultNamespace(
            "block/water_still");
    private static final ResourceLocation LIQUID_TIME_FLOW = ResourceLocation.withDefaultNamespace("block/water_flow");
    private static final ResourceLocation LIQUID_TIME_OVERLAY = ResourceLocation.withDefaultNamespace(
            "block/water_overlay");
    private static final ResourceLocation FLUID_TIME_LOCATION = ResourceLocation.withDefaultNamespace(
            "textures/misc/underwater.png");

    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return LIQUID_TIME_STILL;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return LIQUID_TIME_FLOW;
            }

            @Override
            public ResourceLocation getOverlayTexture() {
                return LIQUID_TIME_OVERLAY;
            }

            @Override
            public ResourceLocation getRenderOverlayTexture(@NotNull Minecraft mc) {
                return FLUID_TIME_LOCATION;
            }

            @Override
            public int getTintColor() {
                return FastColor.ARGB32.color(200, 255, 255, 255);
            }
        }, PhaseFluidRegistries.PhaseFluidTypes.LIQUID_TIME);
    }
}
