package com.genetic_chimerism.mutation_setup;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static com.genetic_chimerism.GeneticChimerism.MOD_ID;

public class MutationAttachments {
    public static void initialize() {
    }

    public static final AttachmentType<List<MutationInfo>> PLAYER_MUTATION_LIST = AttachmentRegistry.create(Identifier.of(MOD_ID, "player_mutation_list"), listBuilder ->
            listBuilder.initializer(() -> new ArrayList<>())
                    .persistent(Codec.list(MutationInfo.MUTATION_CODEC))
                    .syncWith(MutationInfo.MUTATION_PACKET_CODEC.collect(PacketCodecs.toList()), AttachmentSyncPredicate.targetOnly()));

    public static final AttachmentType<MutationBodyInfo> HEAD_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "head_mutation"), infoBuilder ->
            infoBuilder.initializer(() -> null)
                    .persistent(MutationBodyInfo.MUTATION_BODY_CODEC)
                    .syncWith(MutationBodyInfo.MUTATION_BODY_PACKET_CODEC, AttachmentSyncPredicate.targetOnly()));
    public static final AttachmentType<MutationBodyInfo> TORSO_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "torso_mutation"), infoBuilder ->
            infoBuilder.initializer(() -> null)
                    .persistent(MutationBodyInfo.MUTATION_BODY_CODEC)
                    .syncWith(MutationBodyInfo.MUTATION_BODY_PACKET_CODEC, AttachmentSyncPredicate.targetOnly()));
    public static final AttachmentType<MutationBodyInfo> ARM_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "arm_mutation"), infoBuilder ->
            infoBuilder.initializer(() -> null)
                    .persistent(MutationBodyInfo.MUTATION_BODY_CODEC)
                    .syncWith(MutationBodyInfo.MUTATION_BODY_PACKET_CODEC, AttachmentSyncPredicate.targetOnly()));
    public static final AttachmentType<MutationBodyInfo> LEG_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "leg_mutation"), infoBuilder ->
            infoBuilder.initializer(() -> null)
                    .persistent(MutationBodyInfo.MUTATION_BODY_CODEC)
                    .syncWith(MutationBodyInfo.MUTATION_BODY_PACKET_CODEC, AttachmentSyncPredicate.targetOnly()));
    public static final AttachmentType<MutationBodyInfo> TAIL_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "tail_mutation"), infoBuilder ->
            infoBuilder.initializer(() -> null)
                    .persistent(MutationBodyInfo.MUTATION_BODY_CODEC)
                    .syncWith(MutationBodyInfo.MUTATION_BODY_PACKET_CODEC, AttachmentSyncPredicate.targetOnly()));

}
