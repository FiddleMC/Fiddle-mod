package spout.client.fabric.moredatadriven.minecraft.type;

import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import org.jspecify.annotations.Nullable;

public interface PropertiesExtensions {

    @Nullable ChunkSectionLayer spout$getChunkSectionLayer();

    void spout$setChunkSectionLayer(@Nullable ChunkSectionLayer chunkSectionLayer);

}
