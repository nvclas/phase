package de.nvclas.phase.client.fluid;

import net.minecraft.client.Camera;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.FluidType;
import org.joml.Vector3f;

import java.util.function.Supplier;

public record FluidVisionEffect(Supplier<? extends FluidType> fluidType, Supplier<? extends Fluid> sourceFluid,
                                Supplier<? extends Fluid> flowingFluid, double fovMultiplier, int overlayColor,
                                float fogStart, float denseFogEnd, float clearFogEnd, int fogDelayTicks,
                                int fogFadeTicks, Vector3f fogColor, ResourceLocation stillTexture,
                                ResourceLocation flowingTexture, ResourceLocation overlayTexture, int tintColor) {

    public boolean isCameraIn(Camera camera, Level level) {
        BlockPos cameraPos = BlockPos.containing(camera.getPosition());
        FluidState fluidState = level.getFluidState(cameraPos);

        if (fluidState.getFluidType() != fluidType.get()) {
            return false;
        }

        double cameraYInBlock = camera.getPosition().y - cameraPos.getY();
        return cameraYInBlock < fluidState.getHeight(level, cameraPos);
    }

    public int totalFogTicks() {
        return fogDelayTicks + fogFadeTicks;
    }
}
