package com.genetic_chimerism;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record MutActionPayload(boolean isPressed, String keyPressed) implements CustomPayload {
    public static final Identifier MUT_ACTION_PACKET_ID = Identifier.of(GeneticChimerism.MOD_ID,"mut_action");
    public static final CustomPayload.Id<MutActionPayload> ID = new CustomPayload.Id<>(MUT_ACTION_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf,MutActionPayload> MUT_ACTION_CODEC = PacketCodec.tuple(PacketCodecs.BOOLEAN, MutActionPayload::isPressed, PacketCodecs.STRING, MutActionPayload::keyPressed, MutActionPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
