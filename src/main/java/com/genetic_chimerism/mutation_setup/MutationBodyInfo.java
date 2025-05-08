package com.genetic_chimerism.mutation_setup;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.AnimationState;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.encoding.StringEncoding;
import net.minecraft.network.encoding.VarInts;

import java.util.ArrayList;
import java.util.Arrays;

public record MutationBodyInfo(String mutID, String treeID, int patternIndex, int color1, int color2, int growth, boolean isReceding, AnimationState partAnim, AnimationState actionAnim){

    public static final Codec<AnimationState> ANIMATION_STATE_CODEC = Codec.list(Codec.INT).xmap(
            list -> animationStateFromInts(list.getFirst(),list.get(1)),
            animationState -> Arrays.asList(animationState.isRunning()?1:0,animationState.startTick));

    public static final Codec<MutationBodyInfo> MUTATION_BODY_CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.fieldOf("mutID").forGetter(MutationBodyInfo::mutID),
            Codec.STRING.fieldOf("treeID").forGetter(MutationBodyInfo::treeID),
            Codec.INT.fieldOf("patternIndex").forGetter(MutationBodyInfo::patternIndex),
            Codec.INT.fieldOf("color1").forGetter(MutationBodyInfo::color1),
            Codec.INT.fieldOf("color2").forGetter(MutationBodyInfo::color2),
            Codec.INT.fieldOf("growth").forGetter(MutationBodyInfo::growth),
            Codec.BOOL.fieldOf("isReceding").forGetter(MutationBodyInfo::isReceding),
            ANIMATION_STATE_CODEC.fieldOf("partAnim").forGetter(MutationBodyInfo::partAnim),
            ANIMATION_STATE_CODEC.fieldOf("actionAnim").forGetter(MutationBodyInfo::actionAnim)
    ).apply(i, MutationBodyInfo::new));



    public static final PacketCodec<ByteBuf,MutationBodyInfo> MUTATION_BODY_PACKET_CODEC = new PacketCodec<ByteBuf, MutationBodyInfo>() {
        @Override
        public MutationBodyInfo decode(ByteBuf buf) {
            return new MutationBodyInfo(StringEncoding.decode(buf,Short.MAX_VALUE),StringEncoding.decode(buf,Short.MAX_VALUE),
                    VarInts.read(buf),VarInts.read(buf),VarInts.read(buf),VarInts.read(buf),VarInts.read(buf) == 1,
                    animationStateFromInts(VarInts.read(buf),VarInts.read(buf)),animationStateFromInts(VarInts.read(buf),VarInts.read(buf)));
        }

        @Override
        public void encode(ByteBuf buf, MutationBodyInfo value) {
            StringEncoding.encode(buf,value.mutID,Short.MAX_VALUE);
            StringEncoding.encode(buf,value.treeID,Short.MAX_VALUE);
            VarInts.write(buf,value.patternIndex);
            VarInts.write(buf,value.color1);
            VarInts.write(buf,value.color2);
            VarInts.write(buf,value.growth);
            VarInts.write(buf,value.isReceding ? 1:0);
            VarInts.write(buf,value.partAnim.isRunning() ? 1:0);
            VarInts.write(buf,value.partAnim.startTick);
            VarInts.write(buf,value.actionAnim.isRunning() ? 1:0);
            VarInts.write(buf,value.actionAnim.startTick);
        }
    };

    public static AnimationState animationStateFromInts(int isRunning, int startTick){
        AnimationState animationState = new AnimationState();
        animationState.setRunning(isRunning==1,startTick);
        return animationState;
    }

}
