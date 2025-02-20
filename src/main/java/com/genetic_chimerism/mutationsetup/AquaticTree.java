package com.genetic_chimerism.mutationsetup;

import com.genetic_chimerism.GeneticChimerism;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class AquaticTree {
    public static final MutationTrees aquatic = MutationTrees.addTree(new ArrayList<Mutation>(), "aquatic", Identifier.ofVanilla("textures/mob_effect/dolphins_grace.png"));

    public static void initialize() {
    }

    public static final Mutation gills1 = aquatic.addToTree(new Gills1Mutation("gills1", "aquatic", null));
    public static final Mutation gills2 = aquatic.addToTree(new Gills2Mutation("gills2", "aquatic", gills1));
    public static final Mutation gills3 = aquatic.addToTree(new Gills3Mutation("gills3", "aquatic", gills2));
    public static final Mutation gills4 = aquatic.addToTree(new Gills4Mutation("gills4", "aquatic", gills3));

    public static final Mutation fastswim1 = aquatic.addToTree(new FastSwim1Mutation("fastswim1", "aquatic", gills1));
    public static final Mutation sharktail1 = aquatic.addToTree(new SharkTail1Mutation("sharktail1", "aquatic", fastswim1));

    public static class Gills1Mutation extends Mutation{
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static EntityAttributeModifier GILLS1_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID,"gills1_modifier"),1,EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS,GILLS1_MODIFIER);
        }
        @Override
        public void onApplied(PlayerEntity player){
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }
        @Override
        public void onRemoved(PlayerEntity player){
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class Gills2Mutation extends Mutation{
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static EntityAttributeModifier GILLS2_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID,"gills2_modifier"),2,EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS,GILLS2_MODIFIER);
        }
        @Override
        public void onApplied(PlayerEntity player){
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }
        @Override
        public void onRemoved(PlayerEntity player){
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class Gills3Mutation extends Mutation{
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static EntityAttributeModifier GILLS3_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID,"gills3_modifier"),-1,EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS,GILLS3_MODIFIER);
        }
        @Override
        public void onApplied(PlayerEntity player){
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }
        @Override
        public void onRemoved(PlayerEntity player){
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class Gills4Mutation extends Mutation{
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static EntityAttributeModifier GILLS4_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID,"gills4_modifier"),-2,EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS,GILLS4_MODIFIER);
        }
        @Override
        public void onApplied(PlayerEntity player){
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }
        @Override
        public void onRemoved(PlayerEntity player){
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class FastSwim1Mutation extends Mutation{
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static EntityAttributeModifier FASTSWIM1_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID,"fastswim1_modifier"),0.2,EntityAttributeModifier.Operation.ADD_VALUE);

        public FastSwim1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY,FASTSWIM1_MODIFIER);
        }
        @Override
        public void onApplied(PlayerEntity player){
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }
        @Override
        public void onRemoved(PlayerEntity player){
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class SharkTail1Mutation extends Mutation{
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static EntityAttributeModifier SharkTail1_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID,"fastswim1_modifier"),0.4,EntityAttributeModifier.Operation.ADD_VALUE);

    public SharkTail1Mutation(String mutID, String treeID, Mutation prereq) {
        super(mutID, treeID, prereq);
        modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY,SharkTail1_MODIFIER);
    }
    @Override
    public void onApplied(PlayerEntity player){
        player.getAttributes().addTemporaryModifiers(modifierMultimap);
        player.setAttached(MutationAttachments.TAIL_MUTATION,MutationTrees.mutationToCodec(sharktail1));
    }
    @Override
    public void onRemoved(PlayerEntity player){
        player.getAttributes().removeModifiers(modifierMultimap);
    }
}


}
