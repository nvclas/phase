package de.nvclas.phase.content.sounds;

import de.nvclas.phase.registries.PhaseSounds;
import de.nvclas.phase.registries.fluids.PhaseFluidTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class LiquidTimeAmbientSound extends AbstractTickableSoundInstance {
    private static final float MAX_VOLUME = 0.1f;
    private static final int MAX_FADE = 40;
    private static LiquidTimeAmbientSound liquidTimeAmbientSound;
    private final LocalPlayer player;
    private int fade;

    public LiquidTimeAmbientSound(LocalPlayer player) {
        super(PhaseSounds.LIQUID_TIME_AMBIENT.get(), SoundSource.AMBIENT, RandomSource.create());
        this.player = player;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.00000000001f;
        this.pitch = 1.0f;
    }

    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            if (liquidTimeAmbientSound != null) {
                minecraft.getSoundManager().stop(liquidTimeAmbientSound);
                liquidTimeAmbientSound = null;
            }
            return;
        }

        if (liquidTimeAmbientSound != null && liquidTimeAmbientSound.isStopped()) {
            liquidTimeAmbientSound = null;
        }

        if (minecraft.player.isEyeInFluidType(PhaseFluidTypes.LIQUID_TIME.get()) && liquidTimeAmbientSound == null) {
            liquidTimeAmbientSound = new LiquidTimeAmbientSound(minecraft.player);
            minecraft.getSoundManager().play(liquidTimeAmbientSound);
        }
    }

    @Override
    public void tick() {
        if (this.player.isRemoved()) {
            this.stop();
            return;
        }

        if (this.player.isEyeInFluidType(PhaseFluidTypes.LIQUID_TIME.get())) {
            this.fade++;
        } else {
            this.fade -= 2;
        }

        this.fade = Mth.clamp(this.fade, 0, MAX_FADE);
        this.volume = MAX_VOLUME * (this.fade / (float) MAX_FADE);

        this.x = this.player.getX();
        this.y = this.player.getY();
        this.z = this.player.getZ();

        if (this.fade <= 0 && !this.player.isEyeInFluidType(PhaseFluidTypes.LIQUID_TIME.get())) {
            this.stop();
        }
    }
}
