package de.nvclas.phase.datagen.assets;

import de.nvclas.phase.Phase;
import de.nvclas.phase.registries.PhaseBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PhaseBlockStateProvider extends BlockStateProvider {

    private static final String BLOCK_PATH = "block/";
    private static final int[] NO_ROTATION = {0};
    private static final int[] HALF_ROTATION = {0, 180};
    private static final int[] FULL_ROTATION = {0, 90, 180, 270};

    public PhaseBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Phase.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        cubeAllBlock(PhaseBlocks.FRACTURED_STONE.get(),
                BlockModelOptions.create().mirror().rotations(NO_ROTATION, HALF_ROTATION));

        cubeAllBlock(PhaseBlocks.FRACTURED_COBBLESTONE.get());

        cubeBottomTopBlock(PhaseBlocks.PULSING_GRASS.get(), sideTexture(PhaseBlocks.PULSING_GRASS.get()),
                blockTexture(PhaseBlocks.PULSING_DIRT.get()), topTexture(PhaseBlocks.PULSING_GRASS.get()),
                BlockModelOptions.create().rotations(NO_ROTATION, FULL_ROTATION));

        cubeAllBlock(PhaseBlocks.PULSING_DIRT.get(),
                BlockModelOptions.create().rotations(NO_ROTATION, FULL_ROTATION));

        simpleBlock(PhaseBlocks.LIQUID_TIME_BLOCK.get(), models().getExistingFile(mcLoc("water")));
    }

    private ConfiguredModel @NotNull [] getConfiguredModels(List<ModelFile> models, int[] xRotations, int[] yRotations,
            boolean uvLock, int weight) {
        ConfiguredModel[] variants = new ConfiguredModel[models.size() * xRotations.length * yRotations.length];
        int index = 0;

        for (ModelFile model : models) {
            for (int x : xRotations) {
                for (int y : yRotations) {
                    variants[index++] = ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationX(x)
                            .rotationY(y)
                            .uvLock(uvLock)
                            .weight(weight)
                            .buildLast();
                }
            }
        }

        return variants;
    }

    private void cubeAllBlock(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    private void cubeAllBlock(Block block, BlockModelOptions options) {
        ModelFile normalModel = cubeAll(block);
        List<ModelFile> models = new ArrayList<>();
        models.add(normalModel);

        if (options.mirrored) {
            models.add(cubeMirroredAll(block));
        }

        blockWithItem(block, models, normalModel, options);
    }

    private void cubeBottomTopBlock(Block block, ResourceLocation sideTexture, ResourceLocation bottomTexture,
            ResourceLocation topTexture) {
        cubeBottomTopBlock(block, sideTexture, bottomTexture, topTexture, BlockModelOptions.create());
    }

    private void cubeBottomTopBlock(Block block, ResourceLocation sideTexture, ResourceLocation bottomTexture,
            ResourceLocation topTexture, BlockModelOptions options) {
        String name = blockName(block);

        ModelFile normalModel = models().cubeBottomTop(name, sideTexture, bottomTexture, topTexture);

        List<ModelFile> models = new ArrayList<>();
        models.add(normalModel);

        if (options.mirrored) {
            models.add(cubeMirroredBottomTop(block, sideTexture, bottomTexture, topTexture));
        }

        blockWithItem(block, models, normalModel, options);
    }

    private void blockWithItem(Block block, List<ModelFile> models, ModelFile itemModel, BlockModelOptions options) {
        ConfiguredModel[] configuredModels = getConfiguredModels(models, options.xRotations, options.yRotations,
                options.uvLock, options.weight);

        if (options.forAllStates) {
            getVariantBuilder(block).forAllStates(state -> configuredModels);
        } else {
            getVariantBuilder(block).partialState().setModels(configuredModels);
        }

        simpleBlockItem(block, itemModel);
    }

    private ModelFile cubeMirroredAll(Block block) {
        String name = blockName(block);

        return models().withExistingParent(name + "_mirrored", mcLoc("block/cube_mirrored_all"))
                .texture("all", blockTexture(block));
    }

    private ModelFile cubeMirroredBottomTop(Block block, ResourceLocation sideTexture, ResourceLocation bottomTexture,
            ResourceLocation topTexture) {
        String name = blockName(block);

        return models().withExistingParent(name + "_mirrored", mcLoc("block/cube_mirrored"))
                .texture("side", sideTexture)
                .texture("bottom", bottomTexture)
                .texture("top", topTexture);
    }

    private ResourceLocation sideTexture(Block block) {
        return modLoc(BLOCK_PATH + blockName(block) + "_side");
    }

    private ResourceLocation topTexture(Block block) {
        return modLoc(BLOCK_PATH + blockName(block) + "_top");
    }

    private ResourceLocation bottomTexture(Block block) {
        return modLoc(BLOCK_PATH + blockName(block) + "_bottom");
    }

    private String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private static final class BlockModelOptions {
        private int[] xRotations = NO_ROTATION;
        private int[] yRotations = NO_ROTATION;
        private boolean mirrored = false;
        private boolean uvLock = false;
        private boolean forAllStates = false;
        private int weight = 1;

        private static BlockModelOptions create() {
            return new BlockModelOptions();
        }

        private BlockModelOptions rotations(int[] xRotations, int[] yRotations) {
            this.xRotations = xRotations;
            this.yRotations = yRotations;
            return this;
        }

        private BlockModelOptions mirror() {
            this.mirrored = true;
            return this;
        }

        private BlockModelOptions uvLock() {
            this.uvLock = true;
            return this;
        }

        private BlockModelOptions forAllStates() {
            this.forAllStates = true;
            return this;
        }

        private BlockModelOptions weight(int weight) {
            this.weight = weight;
            return this;
        }
    }
}
