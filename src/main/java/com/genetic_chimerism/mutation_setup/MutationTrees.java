package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.ModComponents;
import com.genetic_chimerism.ModItems;
import com.genetic_chimerism.synthblock.SynthRecipe;
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
        Mutation.initialize();
        HornedTree.initialize();
        WingedTree.initialize();
        ShelledTree.initialize();
        AmphibiousTree.initialize();
        ScaledTree.initialize();
        HoovedTree.initialize();
        AquaticTree.initialize();
        InvertebrateTree.initialize();
        SmallMammalTree.initialize();
        SpinedTree.initialize();
        TuskedTree.initialize();
        WoolenTree.initialize();
        SpecialTree.initialize();

    }

    public Mutation addToTree(Mutation mutation){
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
        if (player.getAttached(MutationAttachments.HEAD_MUTATION) != null && mutation.getParts().contains("head")) {
            Mutation headMut = mutationFromCodec(player.getAttached(MutationAttachments.HEAD_MUTATION));
            if (headMut.equals(mutation)) prev = true;
            else if (mutation.getPrereq() != null) prev = hasValidPrereqParts(mutation.getPrereq(),player, prev);
            else prev = false;
        }
        if (player.getAttached(MutationAttachments.TORSO_MUTATION) != null && mutation.getParts().contains("torso")) {
            Mutation torsoMut = mutationFromCodec(player.getAttached(MutationAttachments.TORSO_MUTATION));
            if (torsoMut.equals(mutation)) prev = true;
            else if (mutation.getPrereq() != null) prev = hasValidPrereqParts(mutation.getPrereq(),player, prev);
            else prev = false;
        }
        if (player.getAttached(MutationAttachments.ARM_MUTATION) != null && mutation.getParts().contains("arm")) {
            Mutation armMut = mutationFromCodec(player.getAttached(MutationAttachments.ARM_MUTATION));
            if (armMut.equals(mutation)) prev = true;
            else if (mutation.getPrereq() != null) prev = hasValidPrereqParts(mutation.getPrereq(),player, prev);
            else prev = false;
        }
        if (player.getAttached(MutationAttachments.LEG_MUTATION) != null && mutation.getParts().contains("leg")) {
            Mutation legMut = mutationFromCodec(player.getAttached(MutationAttachments.LEG_MUTATION));
            if (legMut.equals(mutation)) prev = true;
            else if (mutation.getPrereq() != null) prev = hasValidPrereqParts(mutation.getPrereq(),player, prev);
            else prev = false;
        }
        if (player.getAttached(MutationAttachments.TAIL_MUTATION) != null && mutation.getParts().contains("tail")) {
            Mutation tailMut = mutationFromCodec(player.getAttached(MutationAttachments.TAIL_MUTATION));
            if (tailMut.equals(mutation)) prev = true;
            else if (mutation.getPrereq() != null) prev = hasValidPrereqParts(mutation.getPrereq(),player, prev);
            else prev = false;
        }
        return prev;
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
}
