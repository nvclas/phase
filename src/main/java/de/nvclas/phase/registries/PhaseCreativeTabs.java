package de.nvclas.phase.registries;

import de.nvclas.phase.Phase;
import lombok.experimental.UtilityClass;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@UtilityClass
public class PhaseCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB, Phase.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PHASE = CREATIVE_MODE_TABS.register("phase",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + Phase.MODID))
                    .icon(() -> new ItemStack(PhaseItems.UNSTABLE_PHASE.get()))
                    .build());


    public static void addToCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == PHASE.get()) {
            event.accept(PhaseItems.UNSTABLE_PHASE.get());
            event.accept(PhaseBlocks.FRACTURED_STONE.get());
            event.accept(PhaseBlocks.FRACTURED_COBBLESTONE.get());
            event.accept(PhaseBlocks.PULSING_GRASS.get());
            event.accept(PhaseBlocks.PULSING_DIRT.get());
            event.accept(PhaseItems.LIQUID_TIME_BUCKET.get());
        }
    }

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
