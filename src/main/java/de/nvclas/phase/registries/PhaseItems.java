package de.nvclas.phase.registries;

import de.nvclas.phase.Phase;
import lombok.experimental.UtilityClass;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@UtilityClass
public class PhaseItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Phase.MODID);

    public static final DeferredItem<Item> UNSTABLE_PHASE = ITEMS.registerSimpleItem("unstable_phase");
    public static final DeferredItem<BucketItem> LIQUID_TIME_BUCKET = ITEMS.register("liquid_time_bucket",
            () -> new BucketItem(PhaseFluidRegistries.PhaseFluids.LIQUID_TIME.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
