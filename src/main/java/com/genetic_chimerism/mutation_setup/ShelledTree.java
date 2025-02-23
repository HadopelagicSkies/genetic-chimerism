package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ShelledTree {
    public static final MutationTrees shelled = MutationTrees.addTree(new ArrayList<Mutation>(), "shelled", Identifier.ofVanilla("textures/item/turtle_helmet.png"));

    public static void initialize() {
    }

    public static final Mutation shelled_1 = shelled.addToTree(new Mutation("shelled_1", "shelled", null, List.of()));
    public static final Mutation shelled_2 = shelled.addToTree(new Mutation("shelled_2", "shelled", shelled_1, List.of()));
    public static final Mutation shelled_3 = shelled.addToTree(new Mutation("shelled_3", "shelled", shelled_2, List.of()));
    public static final Mutation shelled_4 = shelled.addToTree(new Mutation("shelled_4", "shelled", shelled_3, List.of()));
}
