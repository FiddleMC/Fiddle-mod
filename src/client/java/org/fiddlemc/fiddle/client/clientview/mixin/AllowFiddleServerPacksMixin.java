package org.fiddlemc.fiddle.client.clientview.mixin;

import com.google.common.hash.HashCode;
import net.minecraft.client.resources.server.ServerPackManager;
import org.fiddlemc.fiddle.client.clientview.FiddleProtocol;
import org.fiddlemc.fiddle.client.clientview.ClientModState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.net.URL;
import java.util.UUID;

/**
 * Makes the client silently accept all resource packs from Fiddle servers.
 */
@Mixin(ServerPackManager.class)
public abstract class AllowFiddleServerPacksMixin {

    @Shadow
    public abstract void allowServerPacks();

    @Inject(method = "pushPack", at = @At("HEAD"))
    private void autoAccept(UUID uUID, URL uRL, @Nullable HashCode hashCode, CallbackInfo ci) {
        while (true) {
            ClientModState currentState = FiddleProtocol.getState();
            if (currentState == ClientModState.CLIENT_MOD_DETECTED || currentState == ClientModState.RECEIVED_CUSTOM_CONTENT || currentState == ClientModState.ADDED_CUSTOM_CONTENT) {
                this.allowServerPacks();
                return;
            }
            if (FiddleProtocol.getState() == ClientModState.IDLE || FiddleProtocol.getState() == ClientModState.HANDSHAKE_STARTED) {
                Thread.onSpinWait();
                continue;
            }
            break;
        }
    }

}
