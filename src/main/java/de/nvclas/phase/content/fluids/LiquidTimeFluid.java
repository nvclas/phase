package de.nvclas.phase.content.fluids;

import de.nvclas.phase.registries.fluids.PhaseFluidTypes;
import de.nvclas.phase.registries.fluids.PhaseFluids;
import de.nvclas.phase.registries.PhaseItems;
import de.nvclas.phase.registries.fluids.PhaseLiquidBlocks;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public abstract class LiquidTimeFluid extends BaseFlowingFluid {

    public static final Properties PROPERTIES = new Properties(PhaseFluidTypes.LIQUID_TIME,
            PhaseFluids.LIQUID_TIME,
            PhaseFluids.FLOWING_LIQUID_TIME)
            .explosionResistance(1000.0f)
            .tickRate(3)
            .bucket(PhaseItems.LIQUID_TIME_BUCKET)
            .slopeFindDistance(3)
            .levelDecreasePerBlock(1)
            .block(PhaseLiquidBlocks.LIQUID_TIME);

    protected LiquidTimeFluid() {
        super(PROPERTIES);
    }

}
