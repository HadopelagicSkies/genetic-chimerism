package com.genetic_chimerism.SynthBlock;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
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

    private final ItemStack inputA;
    private final ItemStack inputB;
    private final ItemStack inputC;
    private final ItemStack inputD;
    private final ItemStack inputE;
    private final ItemStack inputF;
    private final ItemStack inputG;


    private final ItemStack output;
    //private final Identifier id;

    public SynthRecipe(ItemStack result, ItemStack inputA, ItemStack inputB, ItemStack inputC, ItemStack inputD, ItemStack inputE, ItemStack inputF, ItemStack inputG) {
        //this.id = id;
        this.output = result;

        this.inputA = inputA;
        this.inputB = inputB;
        this.inputC = inputC;
        this.inputD = inputD;
        this.inputE = inputE;
        this.inputF = inputF;
        this.inputG = inputG;
    }

    public ItemStack getInputA() { return this.inputA;}
    public ItemStack getInputB() { return this.inputB;}
    public ItemStack getInputC() { return this.inputC;}
    public ItemStack getInputD() { return this.inputD;}
    public ItemStack getInputE() { return this.inputE;}
    public ItemStack getInputF() { return this.inputF;}
    public ItemStack getInputG() { return this.inputG;}

    public ItemStack[] getInputs(){return new ItemStack[] {this.inputA,this.inputB,this.inputC,this.inputD,this.inputE,this.inputF,this.inputG};}

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
        for (int i = 0; i < this.getInputs().length; i++) {
            if (!this.getInputs()[i].isEmpty()){
                ingredients.add(Ingredient.fromTag(RegistryEntryList.of(this.getInputs()[i].getRegistryEntry())));
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