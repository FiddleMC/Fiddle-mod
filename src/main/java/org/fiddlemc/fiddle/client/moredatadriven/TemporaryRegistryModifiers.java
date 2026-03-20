package org.fiddlemc.fiddle.client.moredatadriven;

import it.unimi.dsi.fastutil.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;
import java.util.List;

/**
 * A central access point for modifying the registries we wish to modify.
 */
public final class TemporaryRegistryModifiers {

    private TemporaryRegistryModifiers() {
        throw new UnsupportedOperationException();
    }

    /**
     * The Minecraft block registry modifier, or null if not initialized yet.
     */
    private static @Nullable TemporaryRegistryModifier<Block, ?> blockRegistryModifier;

    /**
     * The Minecraft block registry modifier, or null if not initialized yet.
     */
    private static @Nullable TemporaryRegistryModifier<Item, ?> itemRegistryModifier;

    private static void initializeIfNecessary() {
        if (blockRegistryModifier != null) {
            // Already initialized
            return;
        }
        blockRegistryModifier = new TemporaryBlockRegistryModifier();
        itemRegistryModifier = new TemporaryItemRegistryModifier();
    }

    public static void prepareToAddCustomContent() {
        initializeIfNecessary();
        blockRegistryModifier.unfreeze();
        itemRegistryModifier.unfreeze();
    }

    public static void addCustomContent(
        List<Pair<ResourceKey<Block>, Block>> blocks,
        List<Pair<ResourceKey<Item>, Item>> items
    ) {
        System.out.println("Adding custom content");
        initializeIfNecessary();
        blockRegistryModifier.addAndRefreeze(blocks);
        itemRegistryModifier.addAndRefreeze(items);
    }

    public static void removeCustomContent() {
        System.out.println("Removing custom content");
        initializeIfNecessary();
        blockRegistryModifier.remove();
        itemRegistryModifier.remove();
    }

}
