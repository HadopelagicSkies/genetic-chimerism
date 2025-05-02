package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

import java.util.ArrayList;
import java.util.List;

public class AmphibiousTree {
    public static final MutationTrees amphibious = MutationTrees.addTree(new ArrayList<Mutation>(), "amphibious", Identifier.ofVanilla("textures/item/axolotl_bucket.png"));

    public static void initialize() {
    }

    public static final Mutation jump1 = amphibious.addToTree(new Jump1Mutation("jump1", "amphibious", null));
    public static final Mutation jump2 = amphibious.addToTree(new Jump2Mutation("jump2", "amphibious", jump1));
    public static final Mutation jump3 = amphibious.addToTree(new Jump3Mutation("jump3", "amphibious", jump2));
    public static final Mutation jump4 = amphibious.addToTree(new Jump4Mutation("jump4", "amphibious", jump3));

    public static final Mutation amphiGills = amphibious.addToTree(new AmphiGillsMutation("amphi_gills", "amphibious", jump3));
    public static final Mutation axolotlGills = amphibious.addToTree(new AxolotlGillsMutation("axolotl_gills", "amphibious", amphiGills,MutatableParts.HEAD));

    public static final Mutation tadpoleTail = amphibious.addToTree(new TadpoleTailMutation("tadpoleTail", "amphibious", jump2, MutatableParts.TAIL));
    public static final Mutation frogTongue = amphibious.addToTree(new Mutation("frogTongue", "amphibious", tadpoleTail));

    public static final Mutation growthSpeed = amphibious.addToTree(new Mutation("growthSpeed", "amphibious", jump1));
    public static final Mutation regenChance1 = amphibious.addToTree(new Mutation("regenChance1", "amphibious", growthSpeed));
    public static final Mutation regenChance2 = amphibious.addToTree(new Mutation("regenChance2", "amphibious", regenChance1));
    public static final Mutation burnRegen = amphibious.addToTree(new Mutation("burnRegen", "amphibious", regenChance1));


    public static class Jump1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "jump1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Jump1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.JUMP_STRENGTH, MODIFIER);
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

    public static class Jump2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "jump2_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Jump2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.JUMP_STRENGTH, MODIFIER);
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

    public static class Jump3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "jump3_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Jump3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.JUMP_STRENGTH, MODIFIER);
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

    public static class Jump4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "jump4_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Jump4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.JUMP_STRENGTH, MODIFIER);
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

    public static class AmphiGillsMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "amphi_gills_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public AmphiGillsMutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);}

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);}
    }

    public static class AxolotlGillsMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "axolotl_gills_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        public AxolotlGillsMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(axolotlGills,0,
                    ColorHelper.getArgb(0,0,0),ColorHelper.getArgb(0,0,0),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.HEAD,true);
        }

        @Override
        public int getMaxGrowth() {
            return 100;
        }
    }



    public static class TadpoleTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "tadpole_tail_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);
        int cooldown = 0;

        public TadpoleTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(tadpoleTail,0,
                    ColorHelper.getArgb(47,85,57),ColorHelper.getArgb(70,133,87),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(AmphibiousTree.frogTongue))){
                if (!player.getWorld().isClient) {
                    if (this.cooldown <= 0) {
                        MutationAttachments.setPartAnimating(player,MutatableParts.TAIL, true);
                        this.cooldown = 100;
                        int range = 8;
                        Vec3d boxPos = player.getPos().add(0,1,0);
                        for (int i = 0; i < range * 2; i++) {
                            List<Entity> colliders = player.getWorld().getOtherEntities(player, Box.of(boxPos, 2, 2, 2));
                            for (Entity entity : colliders) {
                                if(entity instanceof LivingEntity)
                                    entity.addVelocity(player.getPos().subtract(entity.getPos()).normalize().multiply(0.25).add(0,0.05,0));
                                else if(entity instanceof ItemEntity)
                                    entity.addVelocity(player.getPos().subtract(entity.getPos()).normalize().multiply(0.25).add(0,0.05,0));
                            }
                            if (!colliders.isEmpty()) {
                                player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_FROG_TONGUE, SoundCategory.PLAYERS, 2F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                            }
                            boxPos = boxPos.add(player.getRotationVector().normalize().multiply(0.5));
                            BlockPos boxBlock = BlockPos.ofFloored(Math.round(boxPos.x), Math.round(boxPos.y), Math.round(boxPos.z));
                            if (player.getWorld().getBlockState(boxBlock).isSolidBlock(player.getWorld(),boxBlock)) {
                                break;
                            }
                        }
                    }
                    else player.sendMessage(Text.translatable("mutations.mutation.cooldown.tongue"),true);
                }
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
        }
        @Override
        public int getMaxGrowth() {
            return 200;
        }
    }
}
