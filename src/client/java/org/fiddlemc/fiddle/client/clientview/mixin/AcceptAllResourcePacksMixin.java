package org.fiddlemc.fiddle.client.clientview.mixin;

import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.client.multiplayer.ServerData;
import org.fiddlemc.fiddle.client.clientview.FiddleProtocol;
import org.fiddlemc.fiddle.client.clientview.ClientModState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Makes the client silently accept all resource packs from Fiddle servers.
 */
@Mixin(ClientCommonPacketListenerImpl.class)
public class AcceptAllResourcePacksMixin {

    // Shadow the protected final serverData field
    @Shadow
    protected @Nullable ServerData serverData;

    @Inject(method = "handleResourcePackPush", at = @At("HEAD"))
    private void onInit(CallbackInfo ci) {
        while (true) {
            ClientModState currentState = FiddleProtocol.getState();
            if (currentState == ClientModState.CLIENT_MOD_DETECTED || currentState == ClientModState.RECEIVED_CUSTOM_CONTENT || currentState == ClientModState.ADDED_CUSTOM_CONTENT) {
                // Force accepting of packs
                if (this.serverData != null) {
                    this.serverData.setResourcePackStatus(ServerData.ServerPackStatus.ENABLED);
                }
                return;
            }
            if (currentState == ClientModState.IDLE || currentState == ClientModState.HANDSHAKE_STARTED) {
                // Wait for a valid state
                Thread.onSpinWait();
                continue;
            }
            // No change to handling
            return;
        }
    }

}
