package org.fiddlemc.fiddle.impl.moredatadriven.minecraft.type;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.fiddlemc.fiddle.impl.util.mojang.codec.ProxyCodec;

/**
 * A proxy enum for {@link NoteBlockInstrument}.
 */
public enum NoteBlockInstrumentProxy implements ProxyCodec.Proxy<NoteBlockInstrument> {

    HARP(NoteBlockInstrument.HARP),
    BASEDRUM(NoteBlockInstrument.BASEDRUM),
    SNARE(NoteBlockInstrument.SNARE),
    HAT(NoteBlockInstrument.HAT),
    BASS(NoteBlockInstrument.BASS),
    FLUTE(NoteBlockInstrument.FLUTE),
    BELL(NoteBlockInstrument.BELL),
    GUITAR(NoteBlockInstrument.GUITAR),
    CHIME(NoteBlockInstrument.CHIME),
    XYLOPHONE(NoteBlockInstrument.XYLOPHONE),
    IRON_XYLOPHONE(NoteBlockInstrument.IRON_XYLOPHONE),
    COW_BELL(NoteBlockInstrument.COW_BELL),
    DIDGERIDOO(NoteBlockInstrument.DIDGERIDOO),
    BIT(NoteBlockInstrument.BIT),
    BANJO(NoteBlockInstrument.BANJO),
    PLING(NoteBlockInstrument.PLING),
    ZOMBIE(NoteBlockInstrument.ZOMBIE),
    SKELETON(NoteBlockInstrument.SKELETON),
    CREEPER(NoteBlockInstrument.CREEPER),
    DRAGON(NoteBlockInstrument.DRAGON),
    WITHER_SKELETON(NoteBlockInstrument.WITHER_SKELETON),
    PIGLIN(NoteBlockInstrument.PIGLIN),
    CUSTOM_HEAD(NoteBlockInstrument.CUSTOM_HEAD);

    private final NoteBlockInstrument original;

    NoteBlockInstrumentProxy(NoteBlockInstrument original) {
        this.original = original;
    }

    @Override
    public NoteBlockInstrument getOriginal() {
        return this.original;
    }

}
