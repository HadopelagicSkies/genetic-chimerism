package com.genetic_chimerism.mutation_setup_client;

import java.util.ArrayList;

public class HoovedTreeClient {
    public static final MutationTreesClient hooved = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "hooved");

    public static void initialize() {
    }

    public static final MutationClient hooves = hooved.addToTree(new MutationClient("hooves", "hooved"));
    public static final MutationClient camelHump = hooved.addToTree(new MutationClient("camelHump", "hooved"));
}
