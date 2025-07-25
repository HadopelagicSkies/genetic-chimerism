package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.GeneticChimerismComponents;
import com.genetic_chimerism.GeneticChimerismItems;
import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.synthblock.SynthRecipe;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.player.PlayerEntity;
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

    public static Mutation mutationFromCodec(MutationBodyInfo mutationCodec){
        if(mutationCodec != null) {
            for (MutationTrees trees : mutationTreesList) {
                if (trees.treeID.equals(mutationCodec.treeID())) {
                    for (Mutation mut : trees.mutations) {
                        if (mut.getMutID().equals(mutationCodec.mutID())) {
                            return mut;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void assignRecipes(ServerWorld server){
        for (MutationTrees trees : mutationTreesList) {
            for (Mutation setMutation : trees.mutations) {
                ItemStack result = new ItemStack(GeneticChimerismItems.MUTAGEN_VIAL, 1);
                result.set(GeneticChimerismComponents.MUTATION_STORED, MutationTrees.mutationToCodec(setMutation));
                if (!server.isClient) {
                    ServerRecipeManager manager = server.getRecipeManager();
                    Collection<RecipeEntry<?>> recipes = manager.values();
                    for (RecipeEntry<?> recipeEntry : recipes) {
                        if (recipeEntry.value().getType().equals(SynthRecipe.Type.INSTANCE)) {
                            SynthRecipe recipe = (SynthRecipe) recipeEntry.value();
                            if (result.get(GeneticChimerismComponents.MUTATION_STORED).mutID().equals(recipe.getOutput().get(GeneticChimerismComponents.MUTATION_STORED).mutID())
                                    && result.get(GeneticChimerismComponents.MUTATION_STORED).treeID().equals(recipe.getOutput().get(GeneticChimerismComponents.MUTATION_STORED).treeID())) {
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
        Mutation.initialize();
        AmphibiousTree.initialize();
        AquaticTree.initialize();
        HoovedTree.initialize();
        HornedTree.initialize();
        InvertebrateTree.initialize();
        ScaledTree.initialize();
        ShelledTree.initialize();
        SmallMammalTree.initialize();
        SpinedTree.initialize();
        TentacledTree.initialize();
        TuskedTree.initialize();
        WingedTree.initialize();
        WoolenTree.initialize();
        SpecialTree.initialize();
    }

    public <T extends Mutation> T addToTree(T mutation){
        this.mutations.add(mutation);
        return mutation;
    }

    public static List<MutationTrees> listTrees() {
        return mutationTreesList;
    }

    public static int treeDepth(Mutation mutation, int depth) {
        if (mutation.getPrereq() != null){depth += treeDepth(mutation.getPrereq(), depth);}
        return depth;
    }

    public static boolean hasValidPrereqParts(Mutation mutation, PlayerEntity player, boolean prev) {
        //check if current slot mutation is a prereq of supplied mutation
        for (MutatableParts part : mutation.getParts()) {
            MutationBodyInfo partInfo = MutationAttachments.getPartAttached(player, part);
            if (partInfo != null) {
                Mutation partMut = mutationFromCodec(partInfo);
                if (mutation.equals(partMut)) prev = true;
                else if (mutation.getPrereq() != null) prev = hasValidPrereqParts(mutation.getPrereq(), player, prev);
                else prev = false;
            }
        }
        return prev;
    }

    public static MutationInfo mutationToCodec(Mutation mutation){
        return new MutationInfo(mutation.getMutID(), mutation.getTreeID());
    }

    public static MutationBodyInfo mutationToCodec(Mutation mutation, int patternIndex, int color1, int color2, int growth, boolean isReceding, AnimationState partAnim, AnimationState actionAnim){
        return new MutationBodyInfo(mutation.getMutID(), mutation.getTreeID(),patternIndex,color1,color2,growth,isReceding,partAnim,actionAnim);
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
    public static final MutationTrees human = addTree(new ArrayList<>(), "human", Identifier.ofVanilla("textures/mob_effect/haste.png"));
}
