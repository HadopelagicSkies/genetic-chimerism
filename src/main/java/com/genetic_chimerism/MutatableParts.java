package com.genetic_chimerism;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;

public enum MutatableParts implements StringIdentifiable {
    HEAD("head"),
    TORSO("torso"),
    ARM("arm"),
    LEG("leg"),
    TAIL("tail");

    private final String name;
    private final Text translatableName;

    MutatableParts(String name) {
        this.name = name;
        this.translatableName = Text.translatable(GeneticChimerism.MOD_ID+"."+name+"_part");
    }

    public Text getTranslatableName() {
        return translatableName;
    }

    @Override
    public String asString() {
        return name;
    }

    public static final IntFunction<MutatableParts> INDEX_TO_VALUE = ValueLists.createIdToValueFunction(MutatableParts::ordinal, values(), ValueLists.OutOfBoundsHandling.ZERO);
    public static final PacketCodec<ByteBuf, MutatableParts> MUTATABLE_PARTS_PACKET_CODEC = PacketCodecs.indexed(INDEX_TO_VALUE, MutatableParts::ordinal);

}
