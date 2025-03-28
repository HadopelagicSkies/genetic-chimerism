package com.genetic_chimerism;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record UnsetAnimPayload(MutatableParts part,boolean isAnimating) implements CustomPayload {
        public static final Identifier UNSET_ANIM_PACKET_ID = Identifier.of(GeneticChimerism.MOD_ID,"unset_anim");
        public static final Id<UnsetAnimPayload> ID = new Id<>(UNSET_ANIM_PACKET_ID);
        public static final PacketCodec<RegistryByteBuf, UnsetAnimPayload> UNSET_ANIM_CODEC = PacketCodec.tuple(MutatableParts.MUTATABLE_PARTS_PACKET_CODEC, UnsetAnimPayload::part, PacketCodecs.BOOLEAN, UnsetAnimPayload::isAnimating, UnsetAnimPayload::new);

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
}
