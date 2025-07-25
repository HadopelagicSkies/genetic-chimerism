package com.genetic_chimerism.mutation_setup_client;

import java.util.ArrayList;

public class SpinedTreeClient {
    public static final MutationTreesClient spined = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "spined");

    public static void initialize() {
    }

    public static final MutationClient smallSpines = spined.addToTree(new MutationClient("smallSpines", "spined"));
    public static final MutationClient shootQuills = spined.addToTree(new MutationClient("shootQuills", "spined"));
    public static final MutationClient guardianSpikes1 = spined.addToTree(new MutationClient("guardianSpikes1", "spined"));
    public static final MutationClient guardianSpikes2 = spined.addToTree(new MutationClient("guardianSpikes2", "spined"));
}
