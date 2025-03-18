package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SpinedTree {
    public static final MutationTrees spined = MutationTrees.addTree(new ArrayList<Mutation>(), "spined", Identifier.ofVanilla("textures/mob_effect/resistance.png"));

    public static void initialize() {
    }

    public static final Mutation spined_1 = spined.addToTree(new Mutation("spined_1", "spined", null));
}
