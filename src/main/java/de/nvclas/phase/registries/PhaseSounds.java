package de.nvclas.phase.registries;

import de.nvclas.phase.Phase;
import lombok.experimental.UtilityClass;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

@UtilityClass
public class PhaseSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(
            BuiltInRegistries.SOUND_EVENT, Phase.MODID);

    public static final DeferredSoundType FRACTURED_STONE = new DeferredSoundType(1.0f, 0.8f,
            () -> SoundEvents.STONE_BREAK, () -> SoundEvents.STONE_STEP, () -> SoundEvents.STONE_PLACE,
            () -> SoundEvents.STONE_HIT, () -> SoundEvents.STONE_FALL);

    public static final DeferredSoundType PULSING_GRASS = new DeferredSoundType(1.0f, 0.8f,
            () -> SoundEvents.GRASS_BREAK, () -> SoundEvents.GRASS_STEP, () -> SoundEvents.GRASS_PLACE,
            () -> SoundEvents.GRASS_HIT, () -> SoundEvents.GRASS_FALL);

    public static final DeferredSoundType PULSING_DIRT = new DeferredSoundType(1.0f, 0.8f,
            () -> SoundEvents.GRAVEL_BREAK, () -> SoundEvents.GRAVEL_STEP, () -> SoundEvents.GRAVEL_PLACE,
            () -> SoundEvents.GRAVEL_HIT, () -> SoundEvents.GRAVEL_FALL);


    public static void register(IEventBus modEventBus) {
        SOUND_EVENTS.register(modEventBus);
    }
}
