package com.genetic_chimerism;

import com.genetic_chimerism.SynthBlock.SynthRecipe;

public class Mutation {
    private final String mutID;
    private final String mutationTree;
    private final String desc;
    private final Mutation prereq;
    private SynthRecipe recipe;

    public Mutation(String mutID, String mTree, String mDesc, Mutation mPrereq) {
        this.mutID = mutID;
        this.mutationTree = mTree;
        this.desc = mDesc;
        this.prereq = mPrereq;
    }

    public static void initialize() {

    }

    public String getMutID(){
        return this.mutID;
    }
    public String getTreeID(){
        return this.mutationTree;
    }
    public String getDesc(){
        return this.desc;
    }
    public Mutation getPrereq (){
        return this.prereq;
    }

    public void setRecipe (SynthRecipe recipe){
        this.recipe = recipe;
    }
    public SynthRecipe getRecipe (){return this.recipe;
    }

    public static final Mutation human = MutationTrees.human.addToTree(new Mutation("antigen", "human","Antigen to revert all mutations", null));

    public static final Mutation horned_1 = MutationTrees.horned.addToTree(new Mutation("horned_1", "horned","h1", null));
    public static final Mutation horned_2 = MutationTrees.horned.addToTree(new Mutation("horned_2", "horned","h2", Mutation.horned_1));
    public static final Mutation horned_3 = MutationTrees.horned.addToTree(new Mutation("horned_3", "horned","h3", Mutation.horned_2));
    public static final Mutation horned_4 = MutationTrees.horned.addToTree(new Mutation("horned_4", "horned","h4", Mutation.horned_3));

    public static final Mutation horned_5 = MutationTrees.horned.addToTree(new Mutation("horned_5", "horned","h5", Mutation.horned_1));
    public static final Mutation horned_6 = MutationTrees.horned.addToTree(new Mutation("horned_6", "horned","h6", Mutation.horned_1));
    public static final Mutation horned_7 = MutationTrees.horned.addToTree(new Mutation("horned_7", "horned","h7", Mutation.horned_6));
    public static final Mutation horned_8 = MutationTrees.horned.addToTree(new Mutation("horned_8", "horned","h8", Mutation.horned_7));

    public static final Mutation winged_1 = MutationTrees.winged.addToTree(new Mutation("winged_1", "winged", "w1", null));
    public static final Mutation winged_2 = MutationTrees.winged.addToTree(new Mutation("winged_2", "winged", "w2", Mutation.winged_1));
    public static final Mutation winged_3 = MutationTrees.winged.addToTree(new Mutation("winged_3", "winged", "w3", Mutation.winged_2));
    public static final Mutation winged_4 = MutationTrees.winged.addToTree(new Mutation("winged_4", "winged", "w4", Mutation.winged_3));

    public static final Mutation winged_5 = MutationTrees.winged.addToTree(new Mutation("winged_5", "winged", "w5", null));
    public static final Mutation winged_6 = MutationTrees.winged.addToTree(new Mutation("winged_6", "winged", "w6", null));
    public static final Mutation winged_7 = MutationTrees.winged.addToTree(new Mutation("winged_7", "winged", "w7", null));
    public static final Mutation winged_8 = MutationTrees.winged.addToTree(new Mutation("winged_8", "winged", "w8", null));

    public static final Mutation shelled_1 = MutationTrees.shelled.addToTree(new Mutation("shelled_1", "shelled", "s1", null));
    public static final Mutation shelled_2 = MutationTrees.shelled.addToTree(new Mutation("shelled_2", "shelled", "s2", Mutation.shelled_1));
    public static final Mutation shelled_3 = MutationTrees.shelled.addToTree(new Mutation("shelled_3", "shelled", "s3", Mutation.shelled_2));
    public static final Mutation shelled_4 = MutationTrees.shelled.addToTree(new Mutation("shelled_4", "shelled", "s4", Mutation.shelled_3));
}
