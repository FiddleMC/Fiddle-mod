package org.fiddlemc.fiddle.client.moredatadriven.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.Map;

@Mixin(MappedRegistry.class)
public interface MappedRegistryAccessor<T> {

    @Accessor("frozen")
    void setFrozen(boolean frozen);

    @Accessor("unregisteredIntrusiveHolders")
    void setUnregisteredIntrusiveHolders(Map<T, Holder.Reference<T>> map);

    @Accessor("unregisteredIntrusiveHolders")
    Map<T, Holder.Reference<T>> getUnregisteredIntrusiveHolders();

}
