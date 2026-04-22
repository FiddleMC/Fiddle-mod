package spout.client.fabric.moredatadriven;

import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

/**
 * The {@link TemporaryRegistryModifier} for {@link BuiltInRegistries#ITEM}.
 */
public final class TemporaryItemRegistryModifier extends TemporaryRegistryModifier<Item, DefaultedMappedRegistry<Item>> {

    TemporaryItemRegistryModifier() {
        super((DefaultedMappedRegistry<Item>) BuiltInRegistries.ITEM);
    }

}
