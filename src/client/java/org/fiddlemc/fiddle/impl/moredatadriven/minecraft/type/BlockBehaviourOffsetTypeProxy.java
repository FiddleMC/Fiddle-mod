package org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.fiddlemc.fiddle.impl.util.mojang.codec.ProxyCodec;

/**
 * A proxy enum for {@link BlockBehaviour.OffsetType}.
 */
public enum BlockBehaviourOffsetTypeProxy implements ProxyCodec.Proxy<BlockBehaviour.OffsetType> {

    NONE(BlockBehaviour.OffsetType.NONE),
    XZ(BlockBehaviour.OffsetType.XZ),
    XYZ(BlockBehaviour.OffsetType.XYZ);

    private final BlockBehaviour.OffsetType original;

    BlockBehaviourOffsetTypeProxy(BlockBehaviour.OffsetType original) {
        this.original = original;
    }

    @Override
    public BlockBehaviour.OffsetType getOriginal() {
        return this.original;
    }

}
