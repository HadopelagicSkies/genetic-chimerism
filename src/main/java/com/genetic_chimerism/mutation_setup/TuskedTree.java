package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class TuskedTree {
    public static final MutationTrees tusked = MutationTrees.addTree(new ArrayList<Mutation>(), "tusked", Identifier.ofVanilla("textures/mob_effect/hunger.png"));

    public static void initialize() {
    }

    public static final Mutation tusked_1 = tusked.addToTree(new Mutation("tusked_1", "tusked", null));
}
