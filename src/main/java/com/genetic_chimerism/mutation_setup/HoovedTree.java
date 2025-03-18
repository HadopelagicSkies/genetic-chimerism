package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class HoovedTree {
    public static final MutationTrees hooved = MutationTrees.addTree(new ArrayList<Mutation>(), "hooved", Identifier.ofVanilla("textures/item/golden_horse_armor.png"));

    public static void initialize() {
    }

    public static final Mutation hooved_1 = hooved.addToTree(new Mutation("hooved_1", "hooved", null));
}
