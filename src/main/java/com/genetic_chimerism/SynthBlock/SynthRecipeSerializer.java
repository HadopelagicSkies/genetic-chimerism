package com.genetic_chimerism.SynthBlock;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.RecipeSerializer;

public class SynthRecipeSerializer implements RecipeSerializer<SynthRecipe> {
    private final MapCodec<SynthRecipe> codec;
    private final PacketCodec<RegistryByteBuf, SynthRecipe> packetCodec;

    public SynthRecipeSerializer() {
        codec = RecordCodecBuilder.mapCodec((instance) -> {
            var parameters = instance.group(
                    ItemStack.VALIDATED_CODEC.fieldOf("output").forGetter(SynthRecipe::getOutput),
                    ItemStack.CODEC.fieldOf("inputA").forGetter(SynthRecipe::getInputA),
                    ItemStack.OPTIONAL_CODEC.optionalFieldOf("inputB",ItemStack.EMPTY).forGetter(SynthRecipe::getInputB),
                    ItemStack.OPTIONAL_CODEC.optionalFieldOf("inputC",ItemStack.EMPTY).forGetter(SynthRecipe::getInputC),
                    ItemStack.OPTIONAL_CODEC.optionalFieldOf("inputD",ItemStack.EMPTY).forGetter(SynthRecipe::getInputD),
                    ItemStack.OPTIONAL_CODEC.optionalFieldOf("inputE",ItemStack.EMPTY).forGetter(SynthRecipe::getInputE),
                    ItemStack.OPTIONAL_CODEC.optionalFieldOf("inputF",ItemStack.EMPTY).forGetter(SynthRecipe::getInputF),
                    ItemStack.OPTIONAL_CODEC.optionalFieldOf("inputG",ItemStack.EMPTY).forGetter(SynthRecipe::getInputG));

            return parameters.apply(instance, SynthRecipe::new);
        });

        this.packetCodec = PacketCodec.tuple(
                ItemStack.PACKET_CODEC, SynthRecipe::getOutput,
                ItemStack.PACKET_CODEC, SynthRecipe::getInputA,
                ItemStack.OPTIONAL_PACKET_CODEC, SynthRecipe::getInputB,
                ItemStack.OPTIONAL_PACKET_CODEC, SynthRecipe::getInputC,
                ItemStack.OPTIONAL_PACKET_CODEC, SynthRecipe::getInputD,
                ItemStack.OPTIONAL_PACKET_CODEC, SynthRecipe::getInputE,
                ItemStack.OPTIONAL_PACKET_CODEC, SynthRecipe::getInputF,
                ItemStack.OPTIONAL_PACKET_CODEC, SynthRecipe::getInputG,
                SynthRecipe::new);
    }

    public MapCodec<SynthRecipe> codec() {return this.codec;}

    public PacketCodec<RegistryByteBuf, SynthRecipe> packetCodec() {return this.packetCodec;}

    public static final SynthRecipeSerializer INSTANCE = new SynthRecipeSerializer();
    // This will be the "type" field in the json
    public static final String ID = "synth_recipe";


}
