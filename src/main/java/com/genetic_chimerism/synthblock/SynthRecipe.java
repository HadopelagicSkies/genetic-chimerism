package com.genetic_chimerism.synthblock;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;


public class SynthRecipe implements Recipe<CraftingRecipeInput> {

    private final ItemStack output;
    private final List<ItemStack> inputs;


    public SynthRecipe(ItemStack result, List<ItemStack> inputs) {
        //this.id = id;
        this.output = result;
        this.inputs = inputs;
    }

    public List<ItemStack> getInputs(){return this.inputs;}

    public ItemStack getOutput() {return this.output;}

    //public Identifier getId() {return this.id; }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        //return input.equals(CraftingRecipeInput.create(0,0,Arrays.stream(this.getInputs()).toList()));
        return true;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return this.getOutput().copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<CraftingRecipeInput>> getSerializer() {return SynthRecipeSerializer.INSTANCE;}

    @Override
    public IngredientPlacement getIngredientPlacement() {
        List<Ingredient> ingredients = new ArrayList<>();
        for (ItemStack input : inputs) {
            if (!input.isEmpty()) {
                ingredients.add(Ingredient.fromTag(RegistryEntryList.of(input.getRegistryEntry())));
            }
        }
        return IngredientPlacement.forShapeless(ingredients);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }

    public static class Type implements RecipeType<SynthRecipe> {
        public Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "synth_recipe";
    }

    @Override
    public RecipeType<? extends Recipe<CraftingRecipeInput>> getType() {
        return Type.INSTANCE;
    }

}