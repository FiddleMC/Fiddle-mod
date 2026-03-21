package org.fiddlemc.fiddle.impl.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type.StairBlockCodec;

/**
 * A union of {@link StairBlock} and {@link HalfTransparentBlock}.
 */
public class HalfTransparentStairBlock extends StairBlock {

    public static final MapCodec<HalfTransparentStairBlock> CODEC = StairBlockCodec.simpleStairCodec(HalfTransparentStairBlock::new);

    protected HalfTransparentStairBlock(BlockState baseState, Properties properties) {
        super(baseState, properties);
    }

    @Override
    public MapCodec<? extends HalfTransparentStairBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.is(this) || super.skipRendering(state, adjacentBlockState, side);
    }

}
