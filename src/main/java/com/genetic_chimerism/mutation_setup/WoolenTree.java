package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class WoolenTree {
    public static final MutationTrees woolen = MutationTrees.addTree(new ArrayList<Mutation>(), "woolen", Identifier.ofVanilla("textures/mob_effect/levitation.png"));

    public static void initialize() {
    }

    public static final Mutation woolen_1 = woolen.addToTree(new Mutation("woolen_1", "woolen", null));
}
