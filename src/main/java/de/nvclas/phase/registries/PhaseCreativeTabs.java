package de.nvclas.phase.registries;

import lombok.experimental.UtilityClass;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

@UtilityClass
public class PhaseCreativeTabs {

    public static void addToCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            insertAfter(net.minecraft.world.item.Items.RAW_COPPER, PhaseItems.UNSTABLE_PHASE, event);
        }
    }

    private static void insertAfter(Item after, DeferredItem<Item> item, BuildCreativeModeTabContentsEvent event) {
        event.insertAfter(new ItemStack(after), item.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

}
