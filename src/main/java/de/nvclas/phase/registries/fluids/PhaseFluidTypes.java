package de.nvclas.phase.registries.fluids;

import de.nvclas.phase.Phase;
import lombok.experimental.UtilityClass;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@UtilityClass
public class PhaseFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(
            NeoForgeRegistries.FLUID_TYPES, Phase.MODID);

    public static final DeferredHolder<FluidType, FluidType> LIQUID_TIME = register("liquid_time",
            FluidType.Properties.create()
                    .fallDistanceModifier(0F)
                    .canExtinguish(true)
                    .canConvertToSource(false)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .lightLevel(1)
                    .canHydrate(true));

    private static DeferredHolder<FluidType, FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new FluidType(properties));
    }

    public static void register(IEventBus modEventBus) {
        FLUID_TYPES.register(modEventBus);
    }
}
