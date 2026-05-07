package de.nvclas.phase.client.fluid;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@UtilityClass
public class FluidVisionEffectManager {
    private static final Map<FluidVisionEffect, Integer> previousTicks = new HashMap<>();
    private static final Map<FluidVisionEffect, Integer> ticks = new HashMap<>();

    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        for (FluidVisionEffect effect : ClientFluidVisuals.effects()) {
            int currentTicks = ticks.getOrDefault(effect, 0);
            previousTicks.put(effect, currentTicks);

            if (minecraft.player == null || minecraft.level == null) {
                ticks.put(effect, 0);
                previousTicks.put(effect, 0);
                continue;
            }

            Camera camera = minecraft.gameRenderer.getMainCamera();
            if (effect.isCameraIn(camera, minecraft.level)) {
                ticks.put(effect, Math.min(currentTicks + 1, effect.totalFogTicks()));
            } else {
                ticks.put(effect, 0);
            }
        }
    }

    public static void onComputeFov(ViewportEvent.ComputeFov event) {
        findEffect(event.getCamera()).ifPresent(effect -> event.setFOV(event.getFOV() * effect.fovMultiplier()));
    }

    public static void onRenderOverlay(RenderGuiEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.cameraEntity == null) {
            return;
        }

        Camera camera = minecraft.gameRenderer.getMainCamera();
        Optional<FluidVisionEffect> effect = findEffect(camera);

        if (effect.isEmpty()) {
            return;
        }

        GuiGraphics guiGraphics = event.getGuiGraphics();
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();

        guiGraphics.fill(0, 0, width, height, effect.get().overlayColor());
    }

    public static float getFogEnd(FluidVisionEffect effect, float partialTick) {
        int previous = previousTicks.getOrDefault(effect, 0);
        int current = ticks.getOrDefault(effect, 0);

        if (effect.fogFadeTicks() <= 0) {
            return effect.clearFogEnd();
        }

        float interpolatedTicks = Mth.lerp(partialTick, previous, current);
        float progress = (interpolatedTicks - effect.fogDelayTicks()) / effect.fogFadeTicks();
        progress = Mth.clamp(progress, 0f, 1f);

        return Mth.lerp(progress, effect.denseFogEnd(), effect.clearFogEnd());
    }

    private static Optional<FluidVisionEffect> findEffect(Camera camera) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.level == null) {
            return Optional.empty();
        }

        return ClientFluidVisuals.effects()
                .stream()
                .filter(effect -> effect.isCameraIn(camera, minecraft.level))
                .findFirst();
    }
}
