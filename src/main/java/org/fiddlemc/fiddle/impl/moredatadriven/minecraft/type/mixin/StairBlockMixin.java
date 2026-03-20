package org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type.mixin;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type.BaseStateStringBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StairBlock.class)
public class StairBlockMixin implements BaseStateStringBlock {

    @Shadow
    @Final
    @Mutable
    public static MapCodec<StairBlock> CODEC;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void fiddle$replaceCodec(CallbackInfo ci) {
        CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                com.mojang.serialization.Codec.STRING.fieldOf("base_state").forGetter(stairBlock -> null /* Only needs to happen on server */),
                BlockBehaviour.Properties.CODEC.fieldOf("properties").forGetter(BlockBehaviour::properties) // StairBlock#propertiesCodec()
            ).apply(instance, (baseStateString, properties) -> {
                StairBlock block = new StairBlock(Blocks.STONE.defaultBlockState() /* We replace it later */, properties);
                ((BaseStateStringBlock) block).fiddle$setBaseStateString(baseStateString);
                return block;
            })
        );
    }

    @Shadow
    @Final
    @Mutable
    private Block base;

    @Shadow
    @Final
    @Mutable
    protected BlockState baseState;

    @Unique
    public String baseStateString;

    @Override
    public String fiddle$getBaseStateString() {
        return this.baseStateString;
    }

    @Override
    public void fiddle$setBaseStateString(final String baseStateString) {
        this.baseStateString = baseStateString;
    }

}
