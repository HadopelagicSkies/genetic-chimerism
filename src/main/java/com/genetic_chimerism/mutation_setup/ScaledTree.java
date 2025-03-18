package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ScaledTree {
    public static final MutationTrees scaled = MutationTrees.addTree(new ArrayList<Mutation>(), "scaled", Identifier.ofVanilla("textures/item/turtle_scute.png"));

    public static void initialize() {
    }

    public static final Mutation scaled_1 = scaled.addToTree(new Mutation("scaled_1", "scaled", null));


}
