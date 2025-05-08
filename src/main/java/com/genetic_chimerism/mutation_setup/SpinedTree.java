package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.MutatableParts;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

import java.util.ArrayList;

public class SpinedTree {
    public static final MutationTrees spined = MutationTrees.addTree(new ArrayList<Mutation>(), "spined", Identifier.ofVanilla("textures/mob_effect/resistance.png"));

    public static void initialize() {
    }

    public static final Mutation thornsChance1 = spined.addToTree(new Mutation("thornsChance1", "spined", null));
    public static final Mutation thornsChance2 = spined.addToTree(new Mutation("thornsChance2", "spined", thornsChance1));
    public static final Mutation thornsChance3 = spined.addToTree(new Mutation("thornsChance3", "spined", thornsChance2));

    public static final Mutation smallSpines = spined.addToTree(new SmallSpinesMutation("smallSpines", "spined", thornsChance2,MutatableParts.TORSO));

    public static final Mutation thornsDmg1 = spined.addToTree(new Mutation("thornsDmg1", "spined", null));
    public static final Mutation thornsDmg2 = spined.addToTree(new Mutation("thornsDmg2", "spined", thornsDmg1));
    public static final Mutation thornsDmg3 = spined.addToTree(new Mutation("thornsDmg3", "spined", thornsDmg2));


    public static final Mutation bigQuills1 = spined.addToTree(new BigQuills1Mutation("bigQuills1", "spined", thornsDmg2,MutatableParts.TORSO));
    public static final Mutation bigQuills2 = spined.addToTree(new BigQuills2Mutation("bigQuills2", "spined", bigQuills1,MutatableParts.TORSO));

    public static class SmallSpinesMutation extends Mutation {

        public SmallSpinesMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(smallSpines,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }
    }

    public static class BigQuills1Mutation extends Mutation {

        public BigQuills1Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(bigQuills1,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }
    }

    public static class BigQuills2Mutation extends Mutation {

        public BigQuills2Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(bigQuills2,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }

        @Override
        public int getMaxGrowth() {
            return 500;
        }
    }

}
