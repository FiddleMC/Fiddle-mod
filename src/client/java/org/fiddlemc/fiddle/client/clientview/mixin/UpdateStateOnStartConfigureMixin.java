package org.fiddlemc.fiddle.client.clientview.mixin;

import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;
import org.fiddlemc.fiddle.client.clientview.FiddleProtocol;
import org.fiddlemc.fiddle.client.clientview.ClientModState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Updates the {@link FiddleProtocol} state
 * when the configuration phase is about to start.
 */
@Mixin(ClientConfigurationPacketListenerImpl.class)
public class UpdateStateOnStartConfigureMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstructed(CallbackInfo ci) {
        while (true) {
            if (FiddleProtocol.getState() == ClientModState.CLIENT_MOD_DETECTED) {
                return;
            }
            if (FiddleProtocol.tryChangeState(ClientModState.HANDSHAKE_STARTED, ClientModState.CLIENT_MOD_NOT_DETECTED)) {
                return;
            }
            Thread.onSpinWait();
        }
    }

}
