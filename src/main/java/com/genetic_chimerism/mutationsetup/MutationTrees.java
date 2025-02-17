package com.genetic_chimerism.mutationsetup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.ModComponents;
import com.genetic_chimerism.ModItems;
import com.genetic_chimerism.synthblock.SynthRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MutationTrees {

    public final List<Mutation> mutations;
    public final String treeID;
    public final Identifier icon;

    public static List<MutationTrees> mutationTreesList = new ArrayList<>();
    public MutationTrees(List<Mutation> mutations, String treeID, Identifier iconId){
    this.mutations = mutations;
    this.treeID = treeID;
    this.icon = iconId;
    }

    public static MutationTrees addTree(List<Mutation> tree, String treeID, Identifier iconId){
        MutationTrees mutationTree = new MutationTrees(tree, treeID, iconId);
        mutationTreesList.add(mutationTree);
        return mutationTree;
    }
    public static void assignRecipes(ServerWorld server){
        for (MutationTrees trees : mutationTreesList) {
            for (Mutation setMutation : trees.mutations) {
                ItemStack result = new ItemStack(ModItems.MUTAGEN_VIAL, 1);
                result.set(ModComponents.MUTATION_STORED, MutationTrees.mutationToCodec(setMutation));
                if (!server.isClient) {
                    ServerRecipeManager manager = server.getRecipeManager();
                    Collection<RecipeEntry<?>> recipes = manager.values();
                    for (RecipeEntry<?> recipeEntry : recipes) {
                        if (recipeEntry.value().getType().equals(SynthRecipe.Type.INSTANCE)) {
                            SynthRecipe recipe = (SynthRecipe) recipeEntry.value();
                            if (result.get(ModComponents.MUTATION_STORED).mutID().equals(recipe.getOutput().get(ModComponents.MUTATION_STORED).mutID())
                                    && result.get(ModComponents.MUTATION_STORED).treeID().equals(recipe.getOutput().get(ModComponents.MUTATION_STORED).treeID())) {
                                setMutation.setRecipe(recipe);
                            }
                        }

                    }

                }
            }
        }
        GeneticChimerism.LOGGER.info("Synth Recipes Assigned");
    }
    public static void initialize() {
    }

    public Mutation addToTree(Mutation mutation){
        this.mutations.add(mutation);
        return mutation;
    }

    public static int treeDepth(Mutation mutation, int depth) {

        if (mutation.getPrereq() != null){depth += treeDepth(mutation.getPrereq(), depth);}
        return depth;
    }

    public static List<MutationTrees> listTrees() {
        return mutationTreesList;
    }

    public static MutationInfo mutationToCodec(Mutation mutation){
        return new MutationInfo(mutation.getMutID(), mutation.getTreeID());
    }
    public static Mutation mutationFromCodec(MutationInfo mutationCodec){
        for(MutationTrees trees: mutationTreesList){
            if (trees.treeID.equals(mutationCodec.treeID())){
                for(Mutation mut: trees.mutations){
                    if (mut.getMutID().equals(mutationCodec.mutID())){return mut;}
                }
            }
        }
        return null;
    }


    public static final MutationTrees human = addTree(new ArrayList<Mutation>(), "human", Identifier.ofVanilla("textures/mob_effect/haste.png"));
    public static final MutationTrees horned = addTree(new ArrayList<Mutation>(), "horned", Identifier.ofVanilla("textures/item/goat_horn.png"));
    public static final MutationTrees winged = addTree(new ArrayList<Mutation>(), "winged", Identifier.ofVanilla("textures/mob_effect/slow_falling.png"));
    public static final MutationTrees shelled = addTree(new ArrayList<Mutation>(), "shelled", Identifier.ofVanilla("textures/item/turtle_shell"));
    public static final MutationTrees amphibious = addTree(new ArrayList<Mutation>(), "amphibious", Identifier.ofVanilla("textures/item/axolotl_bucket"));
    public static final MutationTrees scaled = addTree(new ArrayList<Mutation>(), "scaled", Identifier.ofVanilla("textures/block/turtle_scute.png"));
    public static final MutationTrees hooved = addTree(new ArrayList<Mutation>(), "hooved", Identifier.ofVanilla("textures/item/golden_horse_armor.png"));
    public static final MutationTrees aquatic = addTree(new ArrayList<Mutation>(), "aquatic", Identifier.ofVanilla("textures/mob_effect/dolphins_grace.png"));
    public static final MutationTrees invertebrate = addTree(new ArrayList<Mutation>(), "invertebrate", Identifier.ofVanilla("textures/mob_effect/weaving.png"));
    public static final MutationTrees small_mammal = addTree(new ArrayList<Mutation>(), "small_mammal", Identifier.ofVanilla("textures/item/name_tag.png"));
    public static final MutationTrees spined = addTree(new ArrayList<Mutation>(), "spined", Identifier.ofVanilla("textures/mob_effect/resistance.png"));
    public static final MutationTrees woolen = addTree(new ArrayList<Mutation>(), "woolen", Identifier.ofVanilla("textures/mob_effect/levitation.png"));
    public static final MutationTrees tusked = addTree(new ArrayList<Mutation>(), "tusked", Identifier.ofVanilla("textures/mob_effect/hunger.png"));
    public static final MutationTrees special = addTree(new ArrayList<Mutation>(), "special", Identifier.ofVanilla("textures/block/dragon_egg"));








}
