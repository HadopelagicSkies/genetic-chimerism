package com.genetic_chimerism.mutation_setup_client;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.animation.Animation;
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

    public TexturedModelData getTexturedModelData() {return null;}
    public Identifier getTexture(){return null;}
    public Animation getPartAnimation() {return null;}
    public Animation getPartAnimationL() {return null;}
    public Animation getPartAnimationR() {return null;}
    public void mutationAction(ClientPlayerEntity player) {}

    public static final MutationClient human = MutationTreesClient.human.addToTree(new MutationClient("antigen", "human"));

}
