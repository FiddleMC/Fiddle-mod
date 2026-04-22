package spout.client.fabric.moredatadriven;

import it.unimi.dsi.fastutil.Pair;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import spout.common.moredatadriven.minecraft.type.BlockStateStringConversion;
import spout.client.fabric.moredatadriven.minecraft.type.BaseStateStringBlock;
import spout.client.fabric.moredatadriven.minecraft.type.PropertiesExtensions;
import spout.client.fabric.moredatadriven.minecraft.type.mixin.ItemBlockRenderTypesAccessor;
import spout.client.fabric.moredatadriven.minecraft.type.mixin.StairBlockAccessor;
import org.jspecify.annotations.Nullable;
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
                    String baseStateString = ((BaseStateStringBlock) stairBlock).spout$getBaseStateString();
                    BlockState baseState = BlockStateStringConversion.blockStateFromString(baseStateString);
                    StairBlockAccessor accessor = (StairBlockAccessor) stairBlock;
                    accessor.setBaseState(baseState);
                    accessor.setBase(baseState.getBlock());
                }
            }
            // Add render types
            for (Pair<ResourceKey<Block>, Block> resource : resources) {
            }
        }
    }

    @Override
    public void add(ResourceKey<Block> resourceKey, Block resource) {
        super.add(resourceKey, resource);
        // Add render type
        @Nullable ChunkSectionLayer chunkSectionLayer = ((PropertiesExtensions) resource.properties()).spout$getChunkSectionLayer();
        if (chunkSectionLayer != null) {
            ItemBlockRenderTypesAccessor.getTypeByBlock().put(resource, chunkSectionLayer);
        }
    }

    @Override
    public void remove(ResourceKey<Block> resourceKey, Block resource) {
        super.remove(resourceKey, resource);
        // Remove render type
        ItemBlockRenderTypesAccessor.getTypeByBlock().remove(resource);
    }

}
