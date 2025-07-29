package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.mutation_setup.Mutation;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class MutationClient {
    private final String mutID;
    private final String mutationTree;
    final Map<String,Animation> animations = new HashMap<>();
    private final TexturedModelData modelData;

    public MutationClient(String mutID, String treeID) {
        this.mutID = mutID;
        this.mutationTree = treeID;
        this.modelData = this.getTexturedModelData();
        this.animations.put("part",this.createPartAnimation());
        this.animations.put("growth",this.createGrowthAnimation());
        this.animations.put("action",this.createActionAnimation());
        this.animations.put("mirror",this.createMirrorAnimation());
    }

    public static void initialize() {
    }

    public String getMutID(){
        return this.mutID;
    }
    public String getTreeID(){
        return this.mutationTree;
    }

    public Animation getAnimation(String key) {
        return animations.get(key);
    }

    public TexturedModelData getModelData() {
        return modelData;
    }

    public TexturedModelData getTexturedModelData() {
        return TexturedModelData.of(new ModelData(), 32, 32);
    }
    public Identifier getTexture1(){return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/blank.png");}
    public Identifier getTexture2(){return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/blank.png");}

    public Animation createPartAnimation() {return null;}

    public Animation createGrowthAnimation() {return null;}

    public Animation createActionAnimation() {return null;}

    public Animation createMirrorAnimation() {return null;}

    public void mutationAction(ClientPlayerEntity player) {}

    public Mutation getNotClient(){
        return MutationTrees.mutationFromCodec(MutationTreesClient.mutationToCodec(this));
    }

    public int getMaxGrowth(){
        return this.getNotClient().getMaxGrowth();
    }

    public static final MutationClient human = MutationTreesClient.human.addToTree(new MutationClient("antigen", "human"));


}
