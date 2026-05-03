package de.nvclas.phase.client.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.joml.Vector3f;

import java.util.function.Supplier;

public record FluidVisionEffect(Supplier<? extends FluidType> fluidType, Supplier<? extends Fluid> sourceFluid,
                                Supplier<? extends Fluid> flowingFluid, double fovMultiplier, int overlayColor,
                                float fogStart, float denseFogEnd, float clearFogEnd, int fogDelayTicks,
                                int fogFadeTicks, Vector3f fogColor, ResourceLocation stillTexture,
                                ResourceLocation flowingTexture, ResourceLocation overlayTexture, int tintColor) {

    public boolean isEyeIn(Entity entity) {
        return entity.isEyeInFluidType(fluidType.get());
    }

    public int totalFogTicks() {
        return fogDelayTicks + fogFadeTicks;
    }
}
