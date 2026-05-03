package de.nvclas.phase.client.listeners;

import de.nvclas.phase.client.fluid.ClientFluidVisuals;
import lombok.experimental.UtilityClass;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@UtilityClass
public class ClientEventListeners {
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ClientFluidVisuals::setupRenderLayers);
    }

    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        ClientFluidVisuals.registerClientExtensions(event);
    }
}
