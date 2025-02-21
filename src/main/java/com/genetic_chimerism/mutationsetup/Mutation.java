package com.genetic_chimerism.mutationsetup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.synthblock.SynthRecipe;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class Mutation {
    public static final Identifier TEXTURE = Identifier.of(GeneticChimerism.MOD_ID,"textures/body_part/not_actually_a_thingy_lol.png");

    private final String mutID;
    private final String mutationTree;
    private final Mutation prereq;
    private SynthRecipe recipe;

    public Mutation(String mutID, String treeID, Mutation prereq) {
        this.mutID = mutID;
        this.mutationTree = treeID;
        this.prereq = prereq;
    }

    public static void initialize() {
    }

    public static TexturedModelData getTexturedModelData() {return TexturedModelData.of(new ModelData(), 64, 64); }

    public String getMutID(){
        return this.mutID;
    }
    public String getTreeID(){
        return this.mutationTree;
    }
    public Mutation getPrereq(){
        return this.prereq;
    }

    public void setRecipe(SynthRecipe recipe){
        this.recipe = recipe;
    }
    public SynthRecipe getRecipe(){
        return this.recipe;
    }

    public void onApplied(PlayerEntity player){}
    public void onRemoved(PlayerEntity player){}

    public static final Mutation human = MutationTrees.human.addToTree(new Mutation("antigen", "human", null));
}
