package spout.common.moredatadriven.clientmodprotocol;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LenientJsonParser;
import spout.common.branding.SpoutNamespace;
import org.jspecify.annotations.Nullable;

public final class ClientModCustomContentPacketPayload implements CustomPacketPayload {

    private static final Identifier PACKET_ID = Identifier.fromNamespaceAndPath(SpoutNamespace.SPOUT, "custom_content");
    public static final Type<ClientModCustomContentPacketPayload> TYPE = new Type<>(PACKET_ID);
    public static final StreamCodec<FriendlyByteBuf, ClientModCustomContentPacketPayload> STREAM_CODEC = CustomPacketPayload.codec(ClientModCustomContentPacketPayload::write, ClientModCustomContentPacketPayload::new);

    /**
     * {@link #content}, but as JSON.
     */
    public final JsonElement contentJSON;

    /**
     * The content of the packet,
     * or null if not initialized yet.
     */
    public @Nullable ClientModCustomContent content;

    private ClientModCustomContentPacketPayload(FriendlyByteBuf buffer) {
        this.contentJSON = LenientJsonParser.parse(buffer.readUtf());
    }

    @Override
    public Type<ClientModCustomContentPacketPayload> type() {
        return TYPE;
    }

    private void write(FriendlyByteBuf buffer) {
        // No need to write: only needs to happen on the server
    }

    public ClientModCustomContent getContent() {
        if (this.content == null) {
            this.content = ClientModCustomContent.CODEC.decode(JsonOps.INSTANCE, this.contentJSON).getOrThrow().getFirst();
        }
        return this.content;
    }

}
