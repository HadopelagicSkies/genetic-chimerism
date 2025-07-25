package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.MutatableParts;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.genetic_chimerism.GeneticChimerism.MOD_ID;

@SuppressWarnings("UnstableApiUsage")
public class MutationAttachments {
    public static void initialize() {
    }

    public static final AttachmentType<List<MutationInfo>> PLAYER_MUTATION_LIST = AttachmentRegistry.create(Identifier.of(MOD_ID, "player_mutation_list"), listBuilder ->
            listBuilder.initializer(ArrayList::new)
                    .persistent(Codec.list(MutationInfo.MUTATION_CODEC))
                    .copyOnDeath()
                    .syncWith(MutationInfo.MUTATION_PACKET_CODEC.collect(PacketCodecs.toList()), AttachmentSyncPredicate.targetOnly()));

    public static final Map<MutatableParts, AttachmentType<MutationBodyInfo>> PART_MUTATIONS = Util.mapEnum(MutatableParts.class,
        part -> AttachmentRegistry.create(Identifier.of(MOD_ID, part.asString() + "_mutation"), infoBuilder ->
        infoBuilder.initializer(() -> null)
            .persistent(MutationBodyInfo.MUTATION_BODY_CODEC)
            .copyOnDeath()
            .syncWith(MutationBodyInfo.MUTATION_BODY_PACKET_CODEC, AttachmentSyncPredicate.targetOnly())));

    public static final AttachmentType<Direction> WALK_FACE_DIRECTION = AttachmentRegistry.create(Identifier.of(MOD_ID, "walk_face_direction"), infoBuilder ->
            infoBuilder.initializer(() -> Direction.UP)
                    .persistent(Direction.CODEC)
                    .syncWith(Direction.PACKET_CODEC,AttachmentSyncPredicate.targetOnly()));


    public static List<MutationInfo> getMutationsAttached(AttachmentTarget target) {
        return target.getAttached(PLAYER_MUTATION_LIST);
    }

    public static void setMutationsAttached(AttachmentTarget target, List<MutationInfo> info) {
        target.setAttached(PLAYER_MUTATION_LIST, info);
    }

    public static MutationBodyInfo getPartAttached(AttachmentTarget target, MutatableParts part) {
        return target.getAttached(PART_MUTATIONS.get(part));
    }

    public static void setPartAttached(AttachmentTarget target, MutatableParts part, MutationBodyInfo info) {
        target.setAttached(PART_MUTATIONS.get(part), info);
    }

    public static void removePartAttached(AttachmentTarget target, MutatableParts part) {
        target.removeAttached(PART_MUTATIONS.get(part));
    }

    public static void setPartVisuals(AttachmentTarget target, MutatableParts part, int patternIndex, int color1, int color2){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), patternIndex,
                color1, color2, partInfo.growth(), partInfo.isReceding(),partInfo.partAnim(),partInfo.actionAnim()));
    }

    public static void setPartGrowth(AttachmentTarget target, MutatableParts part, int growth){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), partInfo.patternIndex(),
                partInfo.color1(), partInfo.color2(), growth, partInfo.isReceding(),partInfo.partAnim(),partInfo.actionAnim()));
    }

    public static void setPartReceding(AttachmentTarget target, MutatableParts part, boolean isReceding){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        if (partInfo != null)
            MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), partInfo.patternIndex(),
                    partInfo.color1(), partInfo.color2(), partInfo.growth(), isReceding,partInfo.partAnim(),partInfo.actionAnim()));
    }

    public static void setPartAnimating(AttachmentTarget target, MutatableParts part, boolean isAnimating, int startTick){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), partInfo.patternIndex(),
                partInfo.color1(), partInfo.color2(), partInfo.growth(), partInfo.isReceding(),
                MutationBodyInfo.animationStateFromInts(1,startTick),
                MutationBodyInfo.animationStateFromInts(isAnimating?1:0,startTick)));
    }

    public static Direction getWalkFaceDirection(AttachmentTarget target) {
        return target.getAttached(WALK_FACE_DIRECTION);
    }

    public static void setWalkFaceDirection(AttachmentTarget target, Direction info) {
        target.setAttached(WALK_FACE_DIRECTION, info);
    }

}
