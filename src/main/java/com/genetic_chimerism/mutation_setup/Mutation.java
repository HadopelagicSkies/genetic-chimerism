package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.synthblock.SynthRecipe;
//import net.minecraft.client.model.ModelData;
//import net.minecraft.client.model.TexturedModelData;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

public class Mutation {

    private final String mutID;
    private final String mutationTree;
    private final Mutation prereq;
    private final List<String> parts;
    private SynthRecipe recipe;

    public Mutation(String mutID, String treeID, Mutation prereq, List<String> parts) {
        this.mutID = mutID;
        this.mutationTree = treeID;
        this.prereq = prereq;
        this.parts = parts;
    }

    public static void initialize() {
    }

    public String getMutID(){
        return this.mutID;
    }
    public String getTreeID(){
        return this.mutationTree;
    }
    public Mutation getPrereq(){
        return this.prereq;
    }
    public List<String> getParts() {return parts; }

    public void setRecipe(SynthRecipe recipe){
        this.recipe = recipe;
    }
    public SynthRecipe getRecipe(){
        return this.recipe;
    }

    public void onApplied(PlayerEntity player){}
    public void onRemoved(PlayerEntity player){}
    public void mutationAction(PlayerEntity player) {}
    public void tick(PlayerEntity player){};

    public static final Mutation human = MutationTrees.human.addToTree(new Mutation("antigen", "human", null, List.of()));


}
