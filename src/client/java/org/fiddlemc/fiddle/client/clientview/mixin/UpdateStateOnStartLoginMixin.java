package org.fiddlemc.fiddle.client.clientview.mixin;

import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import org.fiddlemc.fiddle.client.clientview.FiddleProtocol;
import org.fiddlemc.fiddle.client.clientview.ClientModState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Updates the {@link FiddleProtocol} state
 * when the login phase is about to start.
 */
@Mixin(ClientHandshakePacketListenerImpl.class)
public class UpdateStateOnStartLoginMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstructed(CallbackInfo ci) {
        FiddleProtocol.changeState(ClientModState.IDLE, ClientModState.HANDSHAKE_STARTED);
    }

}
