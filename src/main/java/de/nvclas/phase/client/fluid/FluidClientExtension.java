package de.nvclas.phase.client.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class FluidClientExtension implements IClientFluidTypeExtensions {
    private final FluidVisionEffect effect;

    public FluidClientExtension(FluidVisionEffect effect) {
        this.effect = effect;
    }

    @Override
    public @NotNull ResourceLocation getStillTexture() {
        return effect.stillTexture();
    }

    @Override
    public @NotNull ResourceLocation getFlowingTexture() {
        return effect.flowingTexture();
    }

    @Override
    public @NotNull ResourceLocation getOverlayTexture() {
        return effect.overlayTexture();
    }

    @Override
    public int getTintColor() {
        return effect.tintColor();
    }

    @Override
    public @NotNull Vector3f modifyFogColor(@NotNull Camera camera, float partialTick, @NotNull ClientLevel level,
            int renderDistance, float darkenWorldAmount, @NotNull Vector3f fluidFogColor) {
        return effect.fogColor();
    }

    @Override
    public void modifyFogRender(@NotNull Camera camera, FogRenderer.@NotNull FogMode mode, float renderDistance,
            float partialTick, float nearDistance, float farDistance, @NotNull FogShape shape) {
        RenderSystem.setShaderFogStart(effect.fogStart());
        RenderSystem.setShaderFogEnd(FluidVisionEffectManager.getFogEnd(effect, partialTick));
        RenderSystem.setShaderFogShape(shape);
    }
}
