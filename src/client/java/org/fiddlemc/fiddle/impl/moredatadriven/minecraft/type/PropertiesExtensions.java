package org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type;

import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import org.jspecify.annotations.Nullable;

public interface PropertiesExtensions {

    @Nullable ChunkSectionLayer fiddle$getChunkSectionLayer();

    void fiddle$setChunkSectionLayer(@Nullable ChunkSectionLayer chunkSectionLayer);

}
