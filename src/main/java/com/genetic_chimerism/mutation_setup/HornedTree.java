package com.genetic_chimerism.mutation_setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class HornedTree {
    public static final MutationTrees horned = MutationTrees.addTree(new ArrayList<Mutation>(), "horned", Identifier.ofVanilla("textures/item/goat_horn.png"));

    public static void initialize() {
    }

    public static final Mutation horned_1 = horned.addToTree(new Horned1("horned_1", "horned", null, List.of()));
    public static final Mutation horned_2 = horned.addToTree(new Mutation("horned_2", "horned", horned_1, List.of()));
    public static final Mutation horned_3 = horned.addToTree(new Mutation("horned_3", "horned", horned_2, List.of()));
    public static final Mutation horned_4 = horned.addToTree(new Mutation("horned_4", "horned", horned_3, List.of()));

    public static final Mutation horned_5 = horned.addToTree(new Mutation("horned_5", "horned", horned_1, List.of()));
    public static final Mutation horned_6 = horned.addToTree(new Mutation("horned_6", "horned", horned_1, List.of()));
    public static final Mutation horned_7 = horned.addToTree(new Mutation("horned_7", "horned", horned_6, List.of()));
    public static final Mutation horned_8 = horned.addToTree(new Mutation("horned_8", "horned", horned_7, List.of()));

    public static class Horned1 extends Mutation{
        public Horned1(String mutID, String treeID, Mutation prereq, List<String> parts) {
            super(mutID, treeID, prereq,parts);
        }
        @Override
        public void onApplied(PlayerEntity player){

        }
        @Override
        public void onRemoved(PlayerEntity player){

        }
    }




}
