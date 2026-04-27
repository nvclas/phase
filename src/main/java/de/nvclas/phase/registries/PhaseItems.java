package de.nvclas.phase.registries;

import de.nvclas.phase.Phase;
import lombok.experimental.UtilityClass;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@UtilityClass
public class PhaseItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Phase.MODID);

    public static final DeferredItem<Item> UNSTABLE_PHASE = ITEMS.registerSimpleItem("unstable_phase");
}
