package com.genetic_chimerism.packet_payloads;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SetAnimPayload(MutatableParts part, boolean isAnimating) implements CustomPayload {
        public static final Identifier SET_ANIM_PACKET_ID = Identifier.of(GeneticChimerism.MOD_ID,"unset_anim");
        public static final Id<SetAnimPayload> ID = new Id<>(SET_ANIM_PACKET_ID);
        public static final PacketCodec<RegistryByteBuf, SetAnimPayload> SET_ANIM_CODEC = PacketCodec.tuple(
                MutatableParts.MUTATABLE_PARTS_PACKET_CODEC, SetAnimPayload::part,
                PacketCodecs.BOOLEAN, SetAnimPayload::isAnimating,
                SetAnimPayload::new);

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
}
