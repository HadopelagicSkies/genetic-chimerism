package com.genetic_chimerism.mutation_setup;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.encoding.VarInts;

public record MutationCounters(int cooldown, int mutationResources, int effectIndex) {
    public static final Codec<MutationCounters> MUTATION_COUNTERS_CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("cooldown").forGetter(MutationCounters::cooldown),
            Codec.INT.fieldOf("mutationResources").forGetter(MutationCounters::mutationResources),
            Codec.INT.fieldOf("effectIndex").forGetter(MutationCounters::effectIndex)
    ).apply(i, MutationCounters::new));

    public static final PacketCodec<ByteBuf,MutationCounters> MUTATION_COUNTERS_PACKET_CODEC = new PacketCodec<ByteBuf, MutationCounters>() {
        @Override
        public MutationCounters decode(ByteBuf buf) {
            return new MutationCounters(VarInts.read(buf),VarInts.read(buf),VarInts.read(buf));
        }

        @Override
        public void encode(ByteBuf buf, MutationCounters value) {
            VarInts.write(buf,value.cooldown);
            VarInts.write(buf,value.mutationResources);
            VarInts.write(buf,value.effectIndex);
        }
    };
}
