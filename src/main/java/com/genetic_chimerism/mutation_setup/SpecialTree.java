package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SpecialTree {
    public static final MutationTrees special = MutationTrees.addTree(new ArrayList<Mutation>(), "special", Identifier.ofVanilla("textures/item/echo_shard.png"));

    public static void initialize() {
    }

    public static final Mutation shroomby_1 = special.addToTree(new Mutation("shroomby_1", "special",null, List.of()));
}
