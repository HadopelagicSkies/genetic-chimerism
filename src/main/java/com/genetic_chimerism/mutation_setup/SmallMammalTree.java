package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SmallMammalTree {
    public static final MutationTrees small_mammal = MutationTrees.addTree(new ArrayList<Mutation>(), "small_mammal", Identifier.ofVanilla("textures/item/name_tag.png"));

    public static void initialize() {
    }

    public static final Mutation small_mammal_1 = small_mammal.addToTree(new Mutation("small_mammal_1", "small_mammal", null));
}
