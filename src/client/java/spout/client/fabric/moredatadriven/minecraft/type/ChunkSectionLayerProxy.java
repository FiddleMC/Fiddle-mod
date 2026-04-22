package spout.client.fabric.moredatadriven.minecraft.type;

import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import spout.common.util.mojang.codec.ProxyCodec;

/**
 * A proxy enum for {@link ChunkSectionLayer}.
 */
public enum ChunkSectionLayerProxy implements ProxyCodec.Proxy<ChunkSectionLayer> {

    SOLID(ChunkSectionLayer.SOLID),
    CUTOUT(ChunkSectionLayer.CUTOUT),
    TRANSLUCENT(ChunkSectionLayer.TRANSLUCENT),
    TRIPWIRE(ChunkSectionLayer.TRIPWIRE);

    private final ChunkSectionLayer original;

    ChunkSectionLayerProxy(ChunkSectionLayer original) {
        this.original = original;
    }

    @Override
    public ChunkSectionLayer getOriginal() {
        return this.original;
    }

}
