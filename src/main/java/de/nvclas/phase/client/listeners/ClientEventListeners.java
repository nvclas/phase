package de.nvclas.phase.client.listeners;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import de.nvclas.phase.content.sounds.LiquidTimeAmbientSound;
import de.nvclas.phase.registries.fluids.PhaseFluidTypes;
import de.nvclas.phase.registries.fluids.PhaseFluids;
import lombok.experimental.UtilityClass;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

@UtilityClass
public class ClientEventListeners {
    private static LiquidTimeAmbientSound liquidTimeAmbientSound;

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(PhaseFluids.LIQUID_TIME.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(PhaseFluids.FLOWING_LIQUID_TIME.get(), RenderType.translucent());
        });
    }

    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        final ResourceLocation liquidTimeStill = ResourceLocation.withDefaultNamespace("block/water_still");
        final ResourceLocation liquidTimeFlowing = ResourceLocation.withDefaultNamespace("block/water_flow");
        final ResourceLocation liquidTimeOverlay = ResourceLocation.withDefaultNamespace("block/water_overlay");
        final int tintColor = FastColor.ARGB32.color(200, 255, 0, 0);
        final float fogStart = 1f;
        final float fogEnd = 25f;
        final Vector3f fogColor = new Vector3f(200f / 255f, 0f / 255f, 0f / 255f);

        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return liquidTimeStill;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return liquidTimeFlowing;
            }

            @Override
            public @NotNull ResourceLocation getOverlayTexture() {
                return liquidTimeOverlay;
            }

            @Override
            public int getTintColor() {
                return tintColor;
            }

            @Override
            public @NotNull Vector3f modifyFogColor(@NotNull Camera camera, float partialTick,
                    @NotNull ClientLevel level, int renderDistance,
                    float darkenWorldAmount, @NotNull Vector3f fluidFogColor) {
                return fogColor;
            }


            @Override
            public void modifyFogRender(@NotNull Camera camera, FogRenderer.@NotNull FogMode mode, float renderDistance,
                    float partialTick, float nearDistance, float farDistance, @NotNull FogShape shape) {
                RenderSystem.setShaderFogStart(fogStart);
                RenderSystem.setShaderFogEnd(fogEnd);
                RenderSystem.setShaderFogShape(shape);
            }
        }, PhaseFluidTypes.LIQUID_TIME);
    }

    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            if (liquidTimeAmbientSound != null) {
                minecraft.getSoundManager().stop(liquidTimeAmbientSound);
            }
            return;
        }

        if (liquidTimeAmbientSound != null && liquidTimeAmbientSound.isStopped()) {
            liquidTimeAmbientSound = null;
        }

        if (minecraft.player.isEyeInFluidType(PhaseFluidTypes.LIQUID_TIME.get())
                && liquidTimeAmbientSound == null) {
            liquidTimeAmbientSound = new LiquidTimeAmbientSound(minecraft.player);
            minecraft.getSoundManager().play(liquidTimeAmbientSound);
        }
    }

}
