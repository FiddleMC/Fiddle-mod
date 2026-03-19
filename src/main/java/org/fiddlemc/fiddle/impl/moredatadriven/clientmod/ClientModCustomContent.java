package org.fiddlemc.fiddle.impl.moredatadriven.clientmod;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jspecify.annotations.NonNull;
import java.util.List;

/**
 * An encodable object containing all custom content sent to the client.
 */
public record ClientModCustomContent(
    List<JsonElement> blocks,
    List<JsonElement> items
) {

    private static final Codec<JsonElement> JSON_CODEC = Codec.PASSTHROUGH.xmap(
        dynamic -> dynamic.convert(JsonOps.INSTANCE).getValue(),
        json -> new Dynamic<>(JsonOps.INSTANCE, json)
    );;

    public static final MapCodec<ClientModCustomContent> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        JSON_CODEC.listOf().fieldOf("blocks").forGetter(ClientModCustomContent::blocks),
        JSON_CODEC.listOf().fieldOf("items").forGetter(ClientModCustomContent::items)
    ).apply(instance, ClientModCustomContent::new));

    public static final Codec<ClientModCustomContent> CODEC = MAP_CODEC.codec();

    @Override
    public @NonNull String toString() {
        return CODEC.encodeStart(JsonOps.INSTANCE, this).toString();
    }

}
