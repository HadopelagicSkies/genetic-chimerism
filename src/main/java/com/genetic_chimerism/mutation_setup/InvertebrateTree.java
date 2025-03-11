package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class InvertebrateTree {
    public static final MutationTrees invertebrate = MutationTrees.addTree(new ArrayList<Mutation>(), "invertebrate", Identifier.ofVanilla("textures/mob_effect/weaving.png"));

    public static void initialize() {
    }
    public static final Mutation invertebrate_1 = invertebrate.addToTree(new Mutation("invertebrate_1", "invertebrate", null));
}
