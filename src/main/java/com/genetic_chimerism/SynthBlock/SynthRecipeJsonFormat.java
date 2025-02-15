package com.genetic_chimerism.SynthBlock;

import com.google.gson.JsonObject;

public class SynthRecipeJsonFormat {
    JsonObject inputA;
    JsonObject inputB;
    JsonObject inputC;
    JsonObject inputD;
    JsonObject inputE;
    JsonObject inputF;
    JsonObject inputG;
    JsonObject inputH;

    String outputItem;
    String outputTree;
    String outputMutation;

    public static class SynthRecipeInputFormat{
        String item;
        int count;
        String tags;
    }
}
