package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.mutation_setup.Mutation;
import com.genetic_chimerism.mutation_setup.TentacledTree;

import java.util.ArrayList;

public class TentacledTreeClient  {
    public static final MutationTreesClient tentacled = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "tentacled");

    public static void initialize() {
    }

    public static final MutationClient tentacleArm1 = tentacled.addToTree(new MutationClient("tentacleArm1", "tentacled"));
    public static final MutationClient tentacleArm2 = tentacled.addToTree(new MutationClient("tentacleArm2", "tentacled"));

    public static final MutationClient inkInvis = tentacled.addToTree(new MutationClient("inkInvis", "tentacled"));
    public static final MutationClient inkBlind = tentacled.addToTree(new MutationClient("inkBlind", "tentacled"));
    public static final MutationClient siphonJet = tentacled.addToTree(new MutationClient("siphonJet", "tentacled"));

    public static final MutationClient inkGlow = tentacled.addToTree(new MutationClient("inkGlow", "tentacled"));
    public static final MutationClient inkFirey = tentacled.addToTree(new MutationClient("inkFirey", "tentacled"));
    public static final MutationClient fireyJet = tentacled.addToTree(new MutationClient("fireyJet", "tentacled"));

}
