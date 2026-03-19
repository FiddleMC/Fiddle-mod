package org.fiddlemc.fiddle.client.moredatadriven.mixin;

import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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

    @Accessor("byId")
    ObjectList<Holder.Reference<T>> getById();

    @Accessor("toId")
    Reference2IntMap<T> getToId();

    @Accessor("byLocation")
    Map<Identifier, Holder.Reference<T>> getByLocation();

    @Accessor("byKey")
    Map<ResourceKey<T>, Holder.Reference<T>> getByKey();

    @Accessor("byValue")
    Map<T, Holder.Reference<T>> getByValue();

    @Accessor("registrationInfos")
    Map<ResourceKey<T>, RegistrationInfo> getRegistrationInfos();

}
