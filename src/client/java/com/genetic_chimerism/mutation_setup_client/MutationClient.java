package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.util.Identifier;

public class MutationClient {
    private final String mutID;
    private final String mutationTree;

    public MutationClient(String mutID, String treeID) {
        this.mutID = mutID;
        this.mutationTree = treeID;
    }

    public static void initialize() {
    }

    public String getMutID(){
        return this.mutID;
    }
    public String getTreeID(){
        return this.mutationTree;
    }

    public TexturedModelData getTexturedModelData() {return TexturedModelData.of(new ModelData(), 64, 64);}
    public Identifier getTexture(){return Identifier.of(GeneticChimerism.MOD_ID,"textures/body_part/not_actually_a_thingy_lol.png");}

    public static final MutationClient human = MutationTreesClient.human.addToTree(new MutationClient("antigen", "human"));
}
