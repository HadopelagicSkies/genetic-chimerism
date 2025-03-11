package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

public record PartRecolorPayload(MutatableParts part, MutationBodyInfo bodyInfo) implements CustomPayload {
    public static final Identifier PART_RECOLOR_PACKET_ID = Identifier.of(GeneticChimerism.MOD_ID,"part_recolor");
    public static final Id<PartRecolorPayload> ID = new Id<>(PART_RECOLOR_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, PartRecolorPayload> PART_RECOLOR_CODEC = PacketCodec.tuple(MutatableParts.MUTATABLE_PARTS_PACKET_CODEC, PartRecolorPayload::part, MutationBodyInfo.MUTATION_BODY_PACKET_CODEC, PartRecolorPayload::bodyInfo, PartRecolorPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
