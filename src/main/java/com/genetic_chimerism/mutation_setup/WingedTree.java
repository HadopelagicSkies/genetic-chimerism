package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import jdk.jshell.Snippet;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stat;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

import java.util.ArrayList;

public class WingedTree {
    public static final MutationTrees winged = MutationTrees.addTree(new ArrayList<Mutation>(), "winged", Identifier.ofVanilla("textures/mob_effect/slow_falling.png"));

    public static void initialize() {
    }

    public static final Mutation safeFall1 = winged.addToTree(new SafeFall1Mutation("safeFall1", "winged", null));
    public static final Mutation safeFall2 = winged.addToTree(new SafeFall2Mutation("safeFall2", "winged", safeFall1));
    public static final Mutation safeFall3 = winged.addToTree(new SafeFall3Mutation("safeFall3", "winged", safeFall2));
    public static final Mutation safeFall4 = winged.addToTree(new SafeFall4Mutation("safeFall4", "winged", safeFall3));

    public static final Mutation fallMultUp1 = winged.addToTree(new FallMultUp1Mutation("fallMultUp1", "winged", safeFall1));
    public static final Mutation backWings1 = winged.addToTree(new BackWings1Mutation("backWings1", "winged", fallMultUp1,MutatableParts.TORSO));
    public static final Mutation fallMultUp2 = winged.addToTree(new FallMultUp2Mutation("fallMultUp2", "winged", backWings1));
    public static final Mutation backWings2 = winged.addToTree(new BackWings2Mutation("backWings2", "winged", fallMultUp2,MutatableParts.TORSO));
    public static final Mutation harpyWings = winged.addToTree(new HarpyWingsMutation("harpyWings", "winged", fallMultUp2, MutatableParts.ARM));

    public static final Mutation flySpeed1 = winged.addToTree(new Mutation("flySpeed1", "winged", fallMultUp1));
    public static final Mutation flySpeed2 = winged.addToTree(new Mutation("flySpeed2", "winged", flySpeed1));
    public static final Mutation flySpeed3 = winged.addToTree(new Mutation("flySpeed3", "winged", flySpeed2));

    public static class SafeFall1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "safefall1_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        public SafeFall1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.SAFE_FALL_DISTANCE, MODIFIER);
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

    public static class SafeFall2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "safefall2_modifier"), 3, EntityAttributeModifier.Operation.ADD_VALUE);

        public SafeFall2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.SAFE_FALL_DISTANCE, MODIFIER);
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

    public static class SafeFall3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "safefall3_modifier"), 4, EntityAttributeModifier.Operation.ADD_VALUE);

        public SafeFall3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.SAFE_FALL_DISTANCE, MODIFIER);
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

    public static class SafeFall4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "safefall4_modifier"), 5, EntityAttributeModifier.Operation.ADD_VALUE);

        public SafeFall4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.SAFE_FALL_DISTANCE, MODIFIER);
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

    public static class FallMultUp1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fallmultup1_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public FallMultUp1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FALL_DAMAGE_MULTIPLIER, MODIFIER);
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

    public static class FallMultUp2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fallmultup2_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public FallMultUp2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FALL_DAMAGE_MULTIPLIER, MODIFIER);
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

    public static class FlySpeed1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "flyspeed1_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public FlySpeed1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FLYING_SPEED, MODIFIER);
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

    public static class FlySpeed2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "flyspeed2_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public FlySpeed2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FLYING_SPEED, MODIFIER);
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

    public static class FlySpeed3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "flyspeed3_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public FlySpeed3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FLYING_SPEED, MODIFIER);
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

    public static class BackWings1Mutation extends Mutation {
        int fallRefresh = 0;
        int cooldown=0;

        public BackWings1Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(backWings1,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if(!player.isOnGround() && cooldown <= 0){
                cooldown = 300;
                MutationAttachments.setPartAnimating(player,MutatableParts.TORSO,true);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING,100));
            } else player.sendMessage(Text.translatable("mutations.mutation.cooldown.wings"), true);

        }

        @Override
        public void tick(PlayerEntity player) {
            if(!player.isOnGround()&& player.hasStatusEffect(StatusEffects.SLOW_FALLING) && fallRefresh>=90){
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING,5,2));
            }
            else if(player.isOnGround()){
                player.removeStatusEffect(StatusEffects.SLOW_FALLING);
                MutationAttachments.setPartAnimating(player,MutatableParts.TORSO,false);
            }
            if (player.hasStatusEffect(StatusEffects.SLOW_FALLING)){
                fallRefresh++;
            }
            if (cooldown >0){
                cooldown--;
            }
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }
    }

    public static class BackWings2Mutation extends Mutation {

        public BackWings2Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(backWings2,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }
    }

    public static class HarpyWingsMutation extends Mutation {

        public HarpyWingsMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.ARM);
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
            MutationAttachments.setPartAttached(player, MutatableParts.ARM, MutationTrees.mutationToCodec(harpyWings,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.ARM,true);
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }

    }

}
