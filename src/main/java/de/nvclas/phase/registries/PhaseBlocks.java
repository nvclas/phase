package de.nvclas.phase.registries;

import de.nvclas.phase.Phase;
import lombok.experimental.UtilityClass;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@UtilityClass
public class PhaseBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Phase.MODID);

    public static final DeferredBlock<Block> FRACTURED_STONE = register("fractured_stone", () -> new Block(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .sound(PhaseSounds.FRACTURED_STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(25.0F, 500.0F)));

    public static final DeferredBlock<Block> COBBLED_FRACTURED_STONE = register("cobbled_fractured_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .sound(PhaseSounds.FRACTURED_STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(25.0F, 500.0F)));

    public static final DeferredBlock<Block> PULSING_GRASS = register("pulsing_grass", () -> new GrassBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .sound(PhaseSounds.PULSING_GRASS)
                    .randomTicks()
                    .requiresCorrectToolForDrops()
                    .strength(1.2F)));

    public static final DeferredBlock<Block> PULSING_DIRT = register("pulsing_dirt", () -> new Block(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .sound(PhaseSounds.PULSING_DIRT)
                    .randomTicks()
                    .requiresCorrectToolForDrops()
                    .strength(1.2F)));

    private static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> properties) {
        DeferredBlock<T> block = BLOCKS.register(name, properties);
        PhaseItems.ITEMS.registerSimpleBlockItem(block);
        return block;
    }
}
