package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class AmphibiousTree {
    public static final MutationTrees amphibious = MutationTrees.addTree(new ArrayList<Mutation>(), "amphibious", Identifier.ofVanilla("textures/item/axolotl_bucket.png"));

    public static void initialize() {
    }

    public static final Mutation amphibious_1 = amphibious.addToTree(new Mutation("amphibious_1", "amphibious", null, List.of()));
}
