package org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type.mixin;

import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type.PropertiesExtensions;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockBehaviour.Properties.class)
public abstract class PropertiesExtensionsMixin implements PropertiesExtensions {

    @Unique
    private @Nullable ChunkSectionLayer fiddle$chunkSectionLayer;

    @Override
    public @Nullable ChunkSectionLayer fiddle$getChunkSectionLayer() {
        return this.fiddle$chunkSectionLayer;
    }

    @Override
    public void fiddle$setChunkSectionLayer(@Nullable ChunkSectionLayer chunkSectionLayer) {
        this.fiddle$chunkSectionLayer = chunkSectionLayer;
    }

}
