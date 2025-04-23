package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.mutation_setup.WingedTree;
import net.minecraft.client.model.TexturedModelData;

import java.util.ArrayList;

public class WingedTreeClient {
    public static final MutationTreesClient winged = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "winged");

    public static void initialize() {
    }

    public static final MutationClient backWings1 = winged.addToTree(new MutationClient("backWings1", "winged"));
    public static final MutationClient backWings2 = winged.addToTree(new MutationClient("backWings2", "winged"));
    public static final MutationClient harpyWings = winged.addToTree(new MutationClient("harpyWings", "winged"));

    public static class HarpyWingsMutation extends MutationClient{

        public HarpyWingsMutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            return super.getTexturedModelData();
        }
    }

}
