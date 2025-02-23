package com.genetic_chimerism.mutation_setup;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.encoding.StringEncoding;

public record MutationInfo(String mutID, String treeID){

    public static final Codec<MutationInfo> MUTATION_CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.fieldOf("mutID").forGetter(MutationInfo::mutID),
            Codec.STRING.fieldOf("treeID").forGetter(MutationInfo::treeID)
    ).apply(i, MutationInfo::new));

    public static final PacketCodec<ByteBuf,MutationInfo> MUTATION_PACKET_CODEC = new PacketCodec<ByteBuf, MutationInfo>() {
        @Override
        public MutationInfo decode(ByteBuf buf) {
            return new MutationInfo(StringEncoding.decode(buf,Short.MAX_VALUE),StringEncoding.decode(buf,Short.MAX_VALUE));
        }

        @Override
        public void encode(ByteBuf buf, MutationInfo value) {
            StringEncoding.encode(buf,value.mutID,Short.MAX_VALUE);
            StringEncoding.encode(buf,value.treeID,Short.MAX_VALUE);
        }
    };
}
