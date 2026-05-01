package de.nvclas.phase.registries;

import de.nvclas.phase.Phase;
import de.nvclas.phase.content.fluids.LiquidTimeFluid;
import lombok.experimental.UtilityClass;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@UtilityClass
public class PhaseFluidRegistries {

    public static void register(IEventBus modEventBus) {
        PhaseFluids.FLUIDS.register(modEventBus);
        PhaseFluidTypes.FLUID_TYPES.register(modEventBus);
    }

    @UtilityClass
    public static class PhaseFluids {
        public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID,
                Phase.MODID);

        public static final DeferredHolder<Fluid, LiquidTimeFluid.Flowing> FLOWING_LIQUID_TIME = FLUIDS.register(
                "flowing_liquid_time", () -> new LiquidTimeFluid.Flowing(LiquidTimeFluid.PROPERTIES));

        public static final DeferredHolder<Fluid, LiquidTimeFluid.Source> LIQUID_TIME = FLUIDS.register("liquid_time",
                () -> new LiquidTimeFluid.Source(LiquidTimeFluid.PROPERTIES));
    }


     @UtilityClass
    public static class PhaseFluidTypes {
        public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(
                NeoForgeRegistries.FLUID_TYPES, Phase.MODID);

        public static final DeferredHolder<FluidType, FluidType> LIQUID_TIME = FLUID_TYPES.register("liquid_time",
                () -> new FluidType(FluidType.Properties.create()
                        .fallDistanceModifier(0F)
                        .canExtinguish(false)
                        .canConvertToSource(true)
                        .supportsBoating(true)
                        .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                        .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                        .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                        .canHydrate(false)));
    }

}
