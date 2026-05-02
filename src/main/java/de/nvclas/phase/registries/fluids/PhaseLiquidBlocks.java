package de.nvclas.phase.registries.fluids;

import de.nvclas.phase.Phase;
import lombok.experimental.UtilityClass;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

@UtilityClass
public class PhaseLiquidBlocks {
    public static final DeferredRegister.Blocks LIQUID_BLOCKS = DeferredRegister.createBlocks(Phase.MODID);

    public static final DeferredBlock<LiquidBlock> LIQUID_TIME = LIQUID_BLOCKS.register("liquid_time",
            () -> new LiquidBlock(PhaseFluids.LIQUID_TIME.get(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));

    public static void register(IEventBus bus) {
        LIQUID_BLOCKS.register(bus);
    }
}
