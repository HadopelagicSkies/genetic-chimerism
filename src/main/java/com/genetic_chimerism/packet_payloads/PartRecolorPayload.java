package com.genetic_chimerism.packet_payloads;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PartRecolorPayload(MutatableParts part, int patternIndex, int color1, int color2) implements CustomPayload {
    public static final Identifier PART_RECOLOR_PACKET_ID = Identifier.of(GeneticChimerism.MOD_ID,"part_recolor");
    public static final Id<PartRecolorPayload> ID = new Id<>(PART_RECOLOR_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, PartRecolorPayload> PART_RECOLOR_CODEC = PacketCodec.tuple(
            MutatableParts.MUTATABLE_PARTS_PACKET_CODEC, PartRecolorPayload::part,
            PacketCodecs.INTEGER, PartRecolorPayload::patternIndex,
            PacketCodecs.INTEGER, PartRecolorPayload::color1,
            PacketCodecs.INTEGER, PartRecolorPayload::color2,
            PartRecolorPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
