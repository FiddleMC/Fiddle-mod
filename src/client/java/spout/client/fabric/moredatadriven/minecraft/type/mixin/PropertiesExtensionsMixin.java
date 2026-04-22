package spout.client.fabric.moredatadriven.minecraft.type.mixin;

import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import spout.client.fabric.moredatadriven.minecraft.type.PropertiesExtensions;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockBehaviour.Properties.class)
public abstract class PropertiesExtensionsMixin implements PropertiesExtensions {

    @Unique
    private @Nullable ChunkSectionLayer spout$chunkSectionLayer;

    @Override
    public @Nullable ChunkSectionLayer spout$getChunkSectionLayer() {
        return this.spout$chunkSectionLayer;
    }

    @Override
    public void spout$setChunkSectionLayer(@Nullable ChunkSectionLayer chunkSectionLayer) {
        this.spout$chunkSectionLayer = chunkSectionLayer;
    }

}
