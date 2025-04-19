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
                    .syncWith(MutationInfo.MUTATION_PACKET_CODEC.collect(PacketCodecs.toList()), AttachmentSyncPredicate.targetOnly()));

    public static final Map<MutatableParts, AttachmentType<MutationBodyInfo>> PART_MUTATIONS = Util.mapEnum(MutatableParts.class,
        part -> AttachmentRegistry.create(Identifier.of(MOD_ID, part.asString() + "_mutation"), infoBuilder ->
        infoBuilder.initializer(() -> null)
            .persistent(MutationBodyInfo.MUTATION_BODY_CODEC)
            .syncWith(MutationBodyInfo.MUTATION_BODY_PACKET_CODEC, AttachmentSyncPredicate.targetOnly())));


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
                color1, color2, partInfo.growth(), partInfo.isReceding(),partInfo.isAnimating()));
    }

    public static void setPartGrowth(AttachmentTarget target, MutatableParts part, int growth){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), partInfo.patternIndex(),
                partInfo.color1(), partInfo.color2(), growth, partInfo.isReceding(),partInfo.isAnimating()));
    }

    public static void setPartReceding(AttachmentTarget target, MutatableParts part, boolean isReceding){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), partInfo.patternIndex(),
                partInfo.color1(), partInfo.color2(), partInfo.growth(), isReceding,partInfo.isAnimating()));
    }

    public static void setPartAnimating(AttachmentTarget target, MutatableParts part, boolean isAnimating){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), partInfo.patternIndex(),
                partInfo.color1(), partInfo.color2(), partInfo.growth(), partInfo.isReceding(),isAnimating));
    }


}
