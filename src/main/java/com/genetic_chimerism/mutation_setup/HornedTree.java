package com.genetic_chimerism.mutation_setup;

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
import java.util.List;

public class HornedTree {
    public static final MutationTrees horned = MutationTrees.addTree(new ArrayList<Mutation>(), "horned", Identifier.ofVanilla("textures/item/goat_horn.png"));

    public static void initialize() {
    }

    public static final Mutation horned_1 = horned.addToTree(new Horned1("horned_1", "horned", null, List.of()));
    public static final Mutation horned_2 = horned.addToTree(new Mutation("horned_2", "horned", horned_1, List.of()));
    public static final Mutation horned_3 = horned.addToTree(new Mutation("horned_3", "horned", horned_2, List.of()));
    public static final Mutation horned_4 = horned.addToTree(new Mutation("horned_4", "horned", horned_3, List.of()));

    public static final Mutation horned_5 = horned.addToTree(new Mutation("horned_5", "horned", horned_1, List.of()));
    public static final Mutation horned_6 = horned.addToTree(new Mutation("horned_6", "horned", horned_1, List.of()));
    public static final Mutation horned_7 = horned.addToTree(new Mutation("horned_7", "horned", horned_6, List.of()));
    public static final Mutation horned_8 = horned.addToTree(new Mutation("horned_8", "horned", horned_7, List.of()));

    public static final Mutation ramhorns1 = horned.addToTree(new RamHorns1Mutation("ramhorns1", "horned", horned_3, List.of()));

    public static class Horned1 extends Mutation{
        public Horned1(String mutID, String treeID, Mutation prereq, List<String> parts) {
            super(mutID, treeID, prereq,parts);
        }
        @Override
        public void onApplied(PlayerEntity player){

        }
        @Override
        public void onRemoved(PlayerEntity player){

        }
    }

    public static class RamHorns1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier RAMHORNS1_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "ramhorns1_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);

        public RamHorns1Mutation(String mutID, String treeID, Mutation prereq, List<String> parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.KNOCKBACK_RESISTANCE, RAMHORNS1_MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            if(player.getAttached(MutationAttachments.HEAD_MUTATION) != null)
                player.removeAttached(MutationAttachments.HEAD_MUTATION);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            player.setAttached(MutationAttachments.HEAD_MUTATION, MutationTrees.mutationToCodec(ramhorns1));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            player.removeAttached(MutationAttachments.HEAD_MUTATION);
        }
    }



}
