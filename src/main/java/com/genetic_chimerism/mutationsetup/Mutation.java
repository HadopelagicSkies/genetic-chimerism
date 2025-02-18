package com.genetic_chimerism.mutationsetup;

import com.genetic_chimerism.synthblock.SynthRecipe;

public class Mutation {
    private final String mutID;
    private final String mutationTree;
    private final Mutation prereq;
    private SynthRecipe recipe;

    public Mutation(String mutID, String mTree, Mutation mPrereq) {
        this.mutID = mutID;
        this.mutationTree = mTree;
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
    public Mutation getPrereq (){
        return this.prereq;
    }

    public void setRecipe (SynthRecipe recipe){
        this.recipe = recipe;
    }
    public SynthRecipe getRecipe (){return this.recipe;
    }

    public static final Mutation human = MutationTrees.human.addToTree(new Mutation("antigen", "human", null));

    public static final Mutation horned_1 = MutationTrees.horned.addToTree(new Mutation("horned_1", "horned", null));
    public static final Mutation horned_2 = MutationTrees.horned.addToTree(new Mutation("horned_2", "horned", Mutation.horned_1));
    public static final Mutation horned_3 = MutationTrees.horned.addToTree(new Mutation("horned_3", "horned", Mutation.horned_2));
    public static final Mutation horned_4 = MutationTrees.horned.addToTree(new Mutation("horned_4", "horned", Mutation.horned_3));

    public static final Mutation horned_5 = MutationTrees.horned.addToTree(new Mutation("horned_5", "horned", Mutation.horned_1));
    public static final Mutation horned_6 = MutationTrees.horned.addToTree(new Mutation("horned_6", "horned", Mutation.horned_1));
    public static final Mutation horned_7 = MutationTrees.horned.addToTree(new Mutation("horned_7", "horned", Mutation.horned_6));
    public static final Mutation horned_8 = MutationTrees.horned.addToTree(new Mutation("horned_8", "horned", Mutation.horned_7));

    public static final Mutation winged_1 = MutationTrees.winged.addToTree(new Mutation("winged_1", "winged", null));
    public static final Mutation winged_2 = MutationTrees.winged.addToTree(new Mutation("winged_2", "winged", Mutation.winged_1));
    public static final Mutation winged_3 = MutationTrees.winged.addToTree(new Mutation("winged_3", "winged", Mutation.winged_2));
    public static final Mutation winged_4 = MutationTrees.winged.addToTree(new Mutation("winged_4", "winged", Mutation.winged_3));

    public static final Mutation winged_5 = MutationTrees.winged.addToTree(new Mutation("winged_5", "winged", null));
    public static final Mutation winged_6 = MutationTrees.winged.addToTree(new Mutation("winged_6", "winged", null));
    public static final Mutation winged_7 = MutationTrees.winged.addToTree(new Mutation("winged_7", "winged", null));
    public static final Mutation winged_8 = MutationTrees.winged.addToTree(new Mutation("winged_8", "winged", null));

    public static final Mutation shelled_1 = MutationTrees.shelled.addToTree(new Mutation("shelled_1", "shelled", null));
    public static final Mutation shelled_2 = MutationTrees.shelled.addToTree(new Mutation("shelled_2", "shelled", Mutation.shelled_1));
    public static final Mutation shelled_3 = MutationTrees.shelled.addToTree(new Mutation("shelled_3", "shelled", Mutation.shelled_2));
    public static final Mutation shelled_4 = MutationTrees.shelled.addToTree(new Mutation("shelled_4", "shelled", Mutation.shelled_3));

    public static final Mutation amphibious_1 = MutationTrees.amphibious.addToTree(new Mutation("amphibious_1", "amphibious", null));
    public static final Mutation scaled_1 = MutationTrees.scaled.addToTree(new Mutation("scaled_1", "scaled", null));
    public static final Mutation hooved_1 = MutationTrees.hooved.addToTree(new Mutation("hooved_1", "hooved", null));
    public static final Mutation aquatic_1 = MutationTrees.aquatic.addToTree(new Mutation("aquatic_1", "aquatic", null));
    public static final Mutation invertebrate_1 = MutationTrees.invertebrate.addToTree(new Mutation("invertebrate_1", "invertebrate", null));
    public static final Mutation small_mammal_1 = MutationTrees.small_mammal.addToTree(new Mutation("small_mammal_1", "small_mammal", null));
    public static final Mutation spined_1 = MutationTrees.spined.addToTree(new Mutation("spined_1", "spined", null));
    public static final Mutation woolen_1 = MutationTrees.woolen.addToTree(new Mutation("woolen_1", "woolen", null));
    public static final Mutation tusked_1 = MutationTrees.tusked.addToTree(new Mutation("tusked_1", "tusked", null));
    public static final Mutation shroomby_1 = MutationTrees.special.addToTree(new Mutation("shroomby_1", "special",null));

}
