package org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import java.util.function.BiFunction;

public final class StairBlockCodec {

    private StairBlockCodec() {
        throw new UnsupportedOperationException();
    }

    public static <B extends StairBlock> MapCodec<B> simpleStairCodec(BiFunction<BlockState, BlockBehaviour.Properties, B> constructor) {
        return RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                com.mojang.serialization.Codec.STRING.fieldOf("base_state").forGetter(stairBlock -> null /* Only needs to happen on server */),
                BlockBehaviour.Properties.CODEC.fieldOf("properties").forGetter(BlockBehaviour::properties) // StairBlock#propertiesCodec()
            ).apply(instance, (baseStateString, properties) -> {
                B block = constructor.apply(Blocks.STONE.defaultBlockState() /* We replace it later */, properties);
                ((BaseStateStringBlock) block).fiddle$setBaseStateString(baseStateString);
                return block;
            })
        );
    }

}
