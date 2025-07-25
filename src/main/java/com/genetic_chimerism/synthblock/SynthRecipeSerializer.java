package com.genetic_chimerism.synthblock;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.RecipeSerializer;

public class SynthRecipeSerializer implements RecipeSerializer<SynthRecipe> {
    private final MapCodec<SynthRecipe> codec;
    private final PacketCodec<RegistryByteBuf, SynthRecipe> packetCodec;

    public SynthRecipeSerializer() {
        this.codec = RecordCodecBuilder.mapCodec((instance) -> {
            var parameters = instance.group(
                    ItemStack.VALIDATED_CODEC.fieldOf("output").forGetter(SynthRecipe::getOutput),
                    Codec.list(ItemStack.CODEC).fieldOf("inputs").forGetter(SynthRecipe::getInputs));
            return parameters.apply(instance, SynthRecipe::new);
        });

        this.packetCodec = PacketCodec.tuple(
                ItemStack.PACKET_CODEC, SynthRecipe::getOutput,
                ItemStack.PACKET_CODEC.collect(PacketCodecs.toList()), SynthRecipe::getInputs,
                SynthRecipe::new);
    }

    public MapCodec<SynthRecipe> codec() {return this.codec;}

    public PacketCodec<RegistryByteBuf, SynthRecipe> packetCodec() {return this.packetCodec;}

    public static final SynthRecipeSerializer INSTANCE = new SynthRecipeSerializer();
    public static final String ID = "synth_recipe";


}
