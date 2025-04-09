package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

import java.util.ArrayList;

public class WoolenTree {
    public static final MutationTrees woolen = MutationTrees.addTree(new ArrayList<Mutation>(), "woolen", Identifier.ofVanilla("textures/mob_effect/levitation.png"));

    public static void initialize() {
    }

    public static final Mutation expResist1 = woolen.addToTree(new ExpResist1Mutation("expResist1", "woolen", null));
    public static final Mutation expResist2 = woolen.addToTree(new ExpResist2Mutation("expResist1", "woolen", expResist1));
    public static final Mutation expResist3 = woolen.addToTree(new ExpResist3Mutation("expResist1", "woolen", expResist2));
    public static final Mutation expResist4 = woolen.addToTree(new ExpResist4Mutation("expResist1", "woolen", expResist3));

    public static final Mutation lanolinRegen = woolen.addToTree(new Mutation("lanolinRegen", "woolen", expResist3));
    public static final Mutation coldResist = woolen.addToTree(new Mutation("coldResist", "woolen", expResist3));
    public static final Mutation snowWalk = woolen.addToTree(new Mutation("snowWalk", "woolen", coldResist));

    public static final Mutation eatGrass = woolen.addToTree(new Mutation("eatGrass", "woolen", expResist1));
    public static final Mutation woolCoat = woolen.addToTree(new WoolCoatMutation("woolCoat", "woolen", eatGrass, MutatableParts.MISC));
    public static final Mutation bouncyLanding = woolen.addToTree(new Mutation("bouncyLanding", "woolen", eatGrass));

    public static class ExpResist1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "expresist1_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public ExpResist1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class ExpResist2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "expresist2_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public ExpResist2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class ExpResist3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "expresist3_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public ExpResist3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class ExpResist4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "expresist4_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public ExpResist4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class WoolCoatMutation extends Mutation {

        public WoolCoatMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.MISC);
            MutationAttachments.setPartAttached(player, MutatableParts.MISC, MutationTrees.mutationToCodec(woolCoat,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.MISC);
            MutationAttachments.setPartAttached(player, MutatableParts.MISC,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

    }


}
