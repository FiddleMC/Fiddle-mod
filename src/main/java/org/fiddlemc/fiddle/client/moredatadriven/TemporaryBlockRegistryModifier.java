package org.fiddlemc.fiddle.client.moredatadriven;

import it.unimi.dsi.fastutil.Pair;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type.BlockStateStringConversion;
import org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type.BaseStateStringBlock;
import org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type.mixin.StairBlockAccessor;
import java.util.List;

/**
 * The {@link TemporaryRegistryModifier} for {@link BuiltInRegistries#BLOCK}.
 */
public final class TemporaryBlockRegistryModifier extends TemporaryRegistryModifier<Block, DefaultedMappedRegistry<Block>> {

    TemporaryBlockRegistryModifier() {
        super((DefaultedMappedRegistry<Block>) BuiltInRegistries.BLOCK);
    }

    @Override
    public void add(List<Pair<ResourceKey<Block>, Block>> resources) {
        super.add(resources);
        if (!resources.isEmpty()) {
            // Set base for stairs
            for (Pair<ResourceKey<Block>, Block> resource : resources) {
                if (resource.right() instanceof StairBlock stairBlock) {
                    System.out.println("Fixing stair block " + resource.right());
                    String baseStateString = ((BaseStateStringBlock) stairBlock).fiddle$getBaseStateString();
                    BlockState baseState = BlockStateStringConversion.blockStateFromString(baseStateString);
                    StairBlockAccessor accessor = (StairBlockAccessor) stairBlock;
                    accessor.setBaseState(baseState);
                    accessor.setBase(baseState.getBlock());
                    System.out.println("Base state string was " + baseStateString + ", result is " + baseState);
                }
            }
        }
    }

}
