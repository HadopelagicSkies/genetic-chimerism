package com.genetic_chimerism.mutation_setup;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.encoding.StringEncoding;
import net.minecraft.network.encoding.VarInts;

public record MutationBodyInfo(String mutID, String treeID, int patternIndex, int color1,int color2){

    public static final Codec<MutationBodyInfo> MUTATION_BODY_CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.fieldOf("mutID").forGetter(MutationBodyInfo::mutID),
            Codec.STRING.fieldOf("treeID").forGetter(MutationBodyInfo::treeID),
            Codec.INT.fieldOf("patternIndex").forGetter(MutationBodyInfo::patternIndex),
            Codec.INT.fieldOf("color1").forGetter(MutationBodyInfo::patternIndex),
            Codec.INT.fieldOf("color2").forGetter(MutationBodyInfo::patternIndex)
    ).apply(i, MutationBodyInfo::new));

    public static final PacketCodec<ByteBuf,MutationBodyInfo> MUTATION_BODY_PACKET_CODEC = new PacketCodec<ByteBuf, MutationBodyInfo>() {
        @Override
        public MutationBodyInfo decode(ByteBuf buf) {
            return new MutationBodyInfo(StringEncoding.decode(buf,Short.MAX_VALUE),StringEncoding.decode(buf,Short.MAX_VALUE),VarInts.read(buf),VarInts.read(buf),VarInts.read(buf));
        }

        @Override
        public void encode(ByteBuf buf, MutationBodyInfo value) {
            StringEncoding.encode(buf,value.mutID,Short.MAX_VALUE);
            StringEncoding.encode(buf,value.treeID,Short.MAX_VALUE);
            VarInts.write(buf,value.patternIndex);
            VarInts.write(buf,value.color1);
            VarInts.write(buf,value.color2);

        }
    };
}
