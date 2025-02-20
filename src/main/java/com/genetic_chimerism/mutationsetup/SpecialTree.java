package com.genetic_chimerism.mutationsetup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class SpecialTree {
    public static final MutationTrees special = MutationTrees.addTree(new ArrayList<Mutation>(), "special", Identifier.ofVanilla("textures/item/echo_shard"));

    public static void initialize() {
    }

    public static final Mutation shroomby_1 = special.addToTree(new Mutation("shroomby_1", "special",null));
}
