package com.genetic_chimerism.mutationsetup;

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
    public static void initialize() {}
    public static final AttachmentType<List<MutationInfo>> PLAYER_MUTATION_LIST = AttachmentRegistry.create(Identifier.of(MOD_ID, "player_mutation_list"),listBuilder ->
            listBuilder.initializer(() -> new ArrayList<>())
                    .persistent(Codec.list(MutationInfo.MUTATION_CODEC))
                    .syncWith(MutationInfo.MUTATION_PACKET_CODEC.collect(PacketCodecs.toList()),AttachmentSyncPredicate.targetOnly()));

    public static final AttachmentType<MutationInfo> HEAD_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "head_mutation"));
    public static final AttachmentType<MutationInfo> BACK_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "back_mutation"));
    public static final AttachmentType<MutationInfo> ARM_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "arm_mutation"));
    public static final AttachmentType<MutationInfo> LEG_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "leg_mutation"));
    public static final AttachmentType<MutationInfo> TAIL_MUTATION = AttachmentRegistry.create(Identifier.of(MOD_ID, "tail_mutation"));

}
