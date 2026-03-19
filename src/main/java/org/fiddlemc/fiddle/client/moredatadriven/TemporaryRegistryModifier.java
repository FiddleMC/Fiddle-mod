package org.fiddlemc.fiddle.client.moredatadriven;

import it.unimi.dsi.fastutil.Pair;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import org.fiddlemc.fiddle.client.moredatadriven.mixin.MappedRegistryAccessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * A wrapper around a registry, that can make temporarily additions.
 */
public abstract class TemporaryRegistryModifier<T, R extends MappedRegistry<T>> {

    public final R registry;

    TemporaryRegistryModifier(R registry) {
        this.registry = registry;
    }

    public MappedRegistryAccessor<T> getRegistryAccessor() {
        return (MappedRegistryAccessor<T>) this.registry;
    }

    public void unfreeze() {
        MappedRegistryAccessor<T> accessor = this.getRegistryAccessor();
        accessor.setFrozen(false);
        if (accessor.getUnregisteredIntrusiveHolders() == null) {
            accessor.setUnregisteredIntrusiveHolders(new IdentityHashMap<>());
        }
        try {
            allTagsField.set(this.registry, unboundMethod.invoke(null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void refreeze() {
        this.registry.freeze();
    }

    public void addAndRefreeze(List<Pair<Identifier, T>> resources) {
        for (Pair<Identifier, T> resource : resources) {
            Registry.register(this.registry, resource.left(), resource.right());
        }
        this.refreeze();
    }

    public void remove() {
        this.unfreeze();
        // TODO
        this.refreeze();
    }

    private static Field allTagsField;
    private static Method unboundMethod;
    static {
        try {
            allTagsField = Arrays.stream(MappedRegistry.class.getDeclaredFields())
                .filter(f -> f.getType().getSimpleName().equals("TagSet"))
                .findFirst()
                .orElseThrow();
            allTagsField.setAccessible(true);
            unboundMethod = allTagsField.getType().getDeclaredMethod("unbound");
            unboundMethod.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
