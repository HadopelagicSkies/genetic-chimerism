package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class TentacledTree {
    public static final MutationTrees tentacled = MutationTrees.addTree(new ArrayList<Mutation>(), "tentacled", Identifier.ofVanilla("textures/mob_effect/darkness.png"));

    public static void initialize() {
    }

    public static final Mutation tentacled_1 = tentacled.addToTree(new Mutation("tentacled_1", "tentacled", null));
    public static final Mutation chroma = tentacled.addToTree(new Mutation("chroma", "tentacled", null));
}

