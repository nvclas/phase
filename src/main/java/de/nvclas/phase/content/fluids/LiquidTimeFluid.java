package de.nvclas.phase.content.fluids;

import de.nvclas.phase.registries.PhaseBlocks;
import de.nvclas.phase.registries.PhaseFluidRegistries;
import de.nvclas.phase.registries.PhaseItems;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public abstract class LiquidTimeFluid extends BaseFlowingFluid {

    public static final Properties PROPERTIES = new Properties(PhaseFluidRegistries.PhaseFluidTypes.LIQUID_TIME,
            PhaseFluidRegistries.PhaseFluids.LIQUID_TIME, PhaseFluidRegistries.PhaseFluids.FLOWING_LIQUID_TIME).block(
                    PhaseBlocks.LIQUID_TIME_BLOCK)
            .explosionResistance(100.0F)
            .bucket(PhaseItems.LIQUID_TIME_BUCKET)
            .tickRate(3)
            .slopeFindDistance(3)
            .levelDecreasePerBlock(1);

    protected LiquidTimeFluid() {
        super(PROPERTIES);
    }
}
