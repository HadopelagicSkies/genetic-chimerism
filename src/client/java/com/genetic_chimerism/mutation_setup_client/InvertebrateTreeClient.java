package com.genetic_chimerism.mutation_setup_client;

import java.util.ArrayList;

public class InvertebrateTreeClient {

    public static final MutationTreesClient invertebrate = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "invertebrate");

    public static void initialize() {
    }

    public static final MutationClient spiderAbdomen = invertebrate.addToTree(new MutationClient("spiderAbdomen", "invertebrate"));
    public static final MutationClient arachneBody = invertebrate.addToTree(new MutationClient("arachneBody", "invertebrate"));
    public static final MutationClient scorpionStinger1 = invertebrate.addToTree(new MutationClient("scorpionStinger1", "invertebrate"));
    public static final MutationClient ScorpionStinger2 = invertebrate.addToTree(new MutationClient("scorpionStinger2", "invertebrate"));
    public static final MutationClient beeAbdomen = invertebrate.addToTree(new MutationClient("beeAbdomen", "invertebrate"));

}
