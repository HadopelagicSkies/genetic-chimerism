package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.synthblock.SynthRecipe;
//import net.minecraft.client.model.ModelData;
//import net.minecraft.client.model.TexturedModelData;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class Mutation {

    private final String mutID;
    private final String mutationTree;
    private final Mutation prereq;
    private final Set<MutatableParts> parts;
    private SynthRecipe recipe;

    public Mutation(String mutID, String treeID, Mutation prereq, Set<MutatableParts> parts) {
        this.mutID = mutID;
        this.mutationTree = treeID;
        this.prereq = prereq;
        this.parts = parts;
    }

    public Mutation(String mutID, String treeID, Mutation prereq, MutatableParts firstPart ,MutatableParts... parts) {
        this(mutID,treeID,prereq,EnumSet.of(firstPart, parts));
    }

    public Mutation(String mutID, String treeID, Mutation prereq) {
        this(mutID,treeID,prereq,EnumSet.noneOf(MutatableParts.class));
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
    public Set<MutatableParts> getParts() {return parts; }

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

    public static final Mutation human = MutationTrees.human.addToTree(new Mutation("antigen", "human", null));


}
