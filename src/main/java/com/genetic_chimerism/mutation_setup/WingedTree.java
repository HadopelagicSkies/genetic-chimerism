package com.genetic_chimerism.mutation_setup;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class WingedTree {
    public static final MutationTrees winged = MutationTrees.addTree(new ArrayList<Mutation>(), "winged", Identifier.ofVanilla("textures/mob_effect/slow_falling.png"));

    public static void initialize() {
    }

    public static final Mutation winged_1 = winged.addToTree(new Mutation("winged_1", "winged", null, List.of()));
    public static final Mutation winged_2 = winged.addToTree(new Mutation("winged_2", "winged", winged_1, List.of()));
    public static final Mutation winged_3 = winged.addToTree(new Mutation("winged_3", "winged", winged_2, List.of()));
    public static final Mutation winged_4 = winged.addToTree(new Mutation("winged_4", "winged", winged_3, List.of()));

    public static final Mutation winged_5 = winged.addToTree(new Mutation("winged_5", "winged", null, List.of()));
    public static final Mutation winged_6 = winged.addToTree(new Mutation("winged_6", "winged", null, List.of()));
    public static final Mutation winged_7 = winged.addToTree(new Mutation("winged_7", "winged", null, List.of()));
    public static final Mutation winged_8 = winged.addToTree(new Mutation("winged_8", "winged", null, List.of()));
}
