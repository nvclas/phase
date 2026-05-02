package de.nvclas.phase.registries.fluids;

import de.nvclas.phase.Phase;
import de.nvclas.phase.content.fluids.LiquidTimeFluid;
import lombok.experimental.UtilityClass;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@UtilityClass
public class PhaseFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Phase.MODID);

    public static final DeferredHolder<Fluid, BaseFlowingFluid> FLOWING_LIQUID_TIME = FLUIDS.register(
            "flowing_liquid_time",
            () -> new LiquidTimeFluid.Flowing(LiquidTimeFluid.PROPERTIES));

    public static final DeferredHolder<Fluid, BaseFlowingFluid> LIQUID_TIME = FLUIDS.register("liquid_time",
            () -> new LiquidTimeFluid.Source(LiquidTimeFluid.PROPERTIES));


    public static void register(IEventBus modEventBus) {
        FLUIDS.register(modEventBus);
    }
}
