package org.fiddlemc.fiddle.client.clientview.mixin;

import net.minecraft.network.Connection;
import net.minecraft.network.DisconnectionDetails;
import org.fiddlemc.fiddle.client.clientview.FiddleProtocol;
import org.fiddlemc.fiddle.client.clientview.ClientModState;
import org.fiddlemc.fiddle.client.moredatadriven.TemporaryRegistryModifiers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Set;

/**
 * Resets the multiplayer connection setup flags
 * and clears any custom content
 * when the client disconnects from a server.
 */
@Mixin(Connection.class)
public class ResetOnDisconnectMixin {

    @Inject(method = "disconnect(Lnet/minecraft/network/DisconnectionDetails;)V", at = @At("TAIL"))
    private void onDisconnect(DisconnectionDetails details, CallbackInfo ci) {
        // Clear custom content, if present
        while (true) {
            if (FiddleProtocol.getState() == ClientModState.ADDED_CUSTOM_CONTENT) {
                TemporaryRegistryModifiers.removeCustomContent();
                System.out.println("Post-remove: " + FiddleProtocol.getState());
                FiddleProtocol.changeState(ClientModState.ADDED_CUSTOM_CONTENT, ClientModState.REMOVED_CUSTOM_CONTENT);
                break;
            }
            if (FiddleProtocol.tryChangeState(Set.of(ClientModState.IDLE, ClientModState.HANDSHAKE_STARTED, ClientModState.CLIENT_MOD_DETECTED, ClientModState.CLIENT_MOD_NOT_DETECTED), ClientModState.REMOVED_CUSTOM_CONTENT)) {
                break;
            }
            Thread.onSpinWait();
        }
        System.out.println("Resetting to idle: " + FiddleProtocol.getState());
        // Reset to idle
        FiddleProtocol.changeState(ClientModState.REMOVED_CUSTOM_CONTENT, ClientModState.IDLE);
        System.out.println("Reset to idle: " + FiddleProtocol.getState());
    }

}
