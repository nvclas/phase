package de.nvclas.phase.blocks;

import de.nvclas.phase.registries.PhaseBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import org.jetbrains.annotations.NotNull;

public class PulsingGrassBlock extends Block {

    public PulsingGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    private static boolean canBePulsingGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = levelReader.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        }
        if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(levelReader, state, pos, blockstate, blockpos, Direction.UP,
                    blockstate.getLightBlock(levelReader, blockpos));
            return i < levelReader.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBePulsingGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos,
            @NotNull RandomSource random) {
        if (!canBePulsingGrass(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 1)) {
                return;
            }
            level.setBlockAndUpdate(pos, PhaseBlocks.PULSING_DIRT.get().defaultBlockState());
            return;
        }
        if (!level.isAreaLoaded(pos, 3)) {
            return;
        }
        if (level.getMaxLocalRawBrightness(pos.above()) < 9) {
            BlockState blockstate = this.defaultBlockState();

            for (int i = 0; i < 4; i++) {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (level.getBlockState(blockpos).is(PhaseBlocks.PULSING_DIRT) && canPropagate(blockstate, level,
                        blockpos)) {
                    level.setBlockAndUpdate(blockpos, blockstate);
                }
            }
        }
    }
}
