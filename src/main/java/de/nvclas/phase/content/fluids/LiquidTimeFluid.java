package de.nvclas.phase.content.fluids;

import de.nvclas.phase.registries.PhaseItems;
import de.nvclas.phase.registries.fluids.PhaseFluidTypes;
import de.nvclas.phase.registries.fluids.PhaseFluids;
import de.nvclas.phase.registries.fluids.PhaseLiquidBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.block.CropGrowEvent;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public abstract class LiquidTimeFluid extends BaseFlowingFluid {

    public static final Properties PROPERTIES = new Properties(PhaseFluidTypes.LIQUID_TIME, PhaseFluids.LIQUID_TIME,
            PhaseFluids.FLOWING_LIQUID_TIME).explosionResistance(1000.0f)
            .tickRate(3)
            .bucket(PhaseItems.LIQUID_TIME_BUCKET)
            .slopeFindDistance(3)
            .levelDecreasePerBlock(1)
            .block(PhaseLiquidBlocks.LIQUID_TIME);

    private static final float MINING_SPEED_MULTIPLIER = 2.0f;
    private static final float MOVEMENT_SPEED_MULTIPLIER = 2.0f;

    protected LiquidTimeFluid() {
        super(PROPERTIES);
    }

    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntity().isEyeInFluidType(PhaseFluidTypes.LIQUID_TIME.get())) {
            event.setNewSpeed(event.getOriginalSpeed() * MINING_SPEED_MULTIPLIER);
        }
    }

    public static void onEntityTick(/*TODO: Find out fitting event*/) {
        /*TODO: Increase movement speed of entities in liquid time*/
    }

    public static void onCropGrow(CropGrowEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }

        BlockPos cropPos = event.getPos();
        BlockState cropState = event.getState();

        if (!(cropState.getBlock() instanceof CropBlock cropBlock) || cropBlock.isMaxAge(cropState)) {
            return;
        }

        if (isNextToLiquidTime(level, cropPos.below())) {
            level.setBlock(cropPos, cropBlock.getStateForAge(cropBlock.getAge(cropState) + 1), 2);
        }
    }

    private static boolean isNextToLiquidTime(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (isLiquidTime(level, pos.relative(direction))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLiquidTime(Level level, BlockPos pos) {
        return level.getFluidState(pos).getFluidType() == PhaseFluidTypes.LIQUID_TIME.get();
    }
}
