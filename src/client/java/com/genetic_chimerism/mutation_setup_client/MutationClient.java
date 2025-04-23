package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.mutation_setup.Mutation;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartData;
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

    public TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        return TexturedModelData.of(modelData, 32, 32);
    }
    public Identifier getTexture1(){return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/blank.png");}
    public Identifier getTexture2(){return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/blank.png");}

    public Animation getPartAnimation() {return null;}
    public Animation getPartAnimationL() {return null;}
    public Animation getPartAnimationR() {return null;}

    public Animation getGrowthAnimation() {return null;}
    public Animation getGrowthAnimationL() {return null;}
    public Animation getGrowthAnimationR() {return null;}

    public Animation getActionAnimation() {return null;}
    public Animation getActionAnimationL() {return null;}
    public Animation getActionAnimationR() {return null;}

    public void mutationAction(ClientPlayerEntity player) {}

    public Mutation getNotClient(){
        return MutationTrees.mutationFromCodec(MutationTreesClient.mutationToCodec(this));
    }

    public static final MutationClient human = MutationTreesClient.human.addToTree(new MutationClient("antigen", "human"));

}
