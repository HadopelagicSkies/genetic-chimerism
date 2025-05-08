package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class TentacledTree {
    public static final MutationTrees tentacled = MutationTrees.addTree(new ArrayList<Mutation>(), "tentacled", Identifier.ofVanilla("textures/mob_effect/darkness.png"));

    public static void initialize() {
    }

    public static final Mutation range1 = tentacled.addToTree(new Range1Mutation("range1", "tentacled", null));
    public static final Mutation range2 = tentacled.addToTree(new Range2Mutation("range2", "tentacled", range1));
    public static final Mutation range3 = tentacled.addToTree(new Range3Mutation("range3", "tentacled", range2));
    public static final Mutation range4 = tentacled.addToTree(new Range4Mutation("range4", "tentacled", range3));

    public static final Mutation tentacleArm1 = tentacled.addToTree(new TentacleArm1Mutation("tentacleArm1", "tentacled", range3, MutatableParts.ARM));
    public static final Mutation tentacleArm2 = tentacled.addToTree(new TentacleArm2Mutation("tentacleArm2", "tentacled", tentacleArm1, MutatableParts.ARM));

    public static final Mutation atkSpd1 = tentacled.addToTree(new AtkSpd1Mutation("atkSpd1", "tentacled", range2));
    public static final Mutation atkSpd2 = tentacled.addToTree(new AtkSpd2Mutation("atkSpd2", "tentacled", atkSpd1));
    public static final Mutation atkSpd3 = tentacled.addToTree(new AtkSpd3Mutation("atkSpd3", "tentacled", atkSpd2));

    public static final Mutation chroma = tentacled.addToTree(new Mutation("chroma", "tentacled", null));
    public static final Mutation chromaInvis = tentacled.addToTree(new ChromaInvisMutation("chromaInvis", "tentacled", chroma));

    public static final Mutation inkInvis = tentacled.addToTree(new InkInvisMutation("inkInvis", "tentacled", chromaInvis, MutatableParts.MISC));
    public static final Mutation inkBlind = tentacled.addToTree(new InkBlindMutation("inkBlind", "tentacled", inkInvis, MutatableParts.MISC));
    public static final Mutation siphonJet = tentacled.addToTree(new SiphonJetMutation("siphonJet", "tentacled", inkBlind, MutatableParts.TORSO));

    public static final Mutation inkGlow = tentacled.addToTree(new InkGlowMutation("inkGlow", "tentacled", chromaInvis, MutatableParts.MISC));
    public static final Mutation inkFirey = tentacled.addToTree(new InkFireyMutation("inkFirey", "tentacled", inkGlow, MutatableParts.MISC));
    public static final Mutation fireyJet = tentacled.addToTree(new FireyJetMutation("fireyJet", "tentacled", inkFirey, MutatableParts.TORSO));

    public static class Range1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "range1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Range1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ENTITY_INTERACTION_RANGE, MODIFIER);
            modifierMultimap.put(EntityAttributes.BLOCK_INTERACTION_RANGE, MODIFIER);
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

    public static class Range2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "range2_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Range2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ENTITY_INTERACTION_RANGE, MODIFIER);
            modifierMultimap.put(EntityAttributes.BLOCK_INTERACTION_RANGE, MODIFIER);
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

    public static class Range3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "range3_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Range3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ENTITY_INTERACTION_RANGE, MODIFIER);
            modifierMultimap.put(EntityAttributes.BLOCK_INTERACTION_RANGE, MODIFIER);
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

    public static class Range4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "range4_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Range4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ENTITY_INTERACTION_RANGE, MODIFIER);
            modifierMultimap.put(EntityAttributes.BLOCK_INTERACTION_RANGE, MODIFIER);
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

    public static class AtkSpd1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "atkspd1_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public AtkSpd1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ATTACK_SPEED, MODIFIER);
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

    public static class AtkSpd2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "atkspd2_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public AtkSpd2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ATTACK_SPEED, MODIFIER);
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

    public static class AtkSpd3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "atkspd3_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public AtkSpd3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ATTACK_SPEED, MODIFIER);
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

    public static class ChromaInvisMutation extends Mutation {
        private int crouchTime = 0;

        public ChromaInvisMutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
        }

        @Override
        public void tick(PlayerEntity player) {
            if (!player.isSneaking() && crouchTime > 0) {
                crouchTime = 0;
                player.removeStatusEffect(StatusEffects.INVISIBILITY);
            } else {
                if (crouchTime >= 100) {
                    if (player.getStatusEffect(StatusEffects.INVISIBILITY) == null)
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 10));
                    else if (player.getStatusEffect(StatusEffects.INVISIBILITY) != null && player.getStatusEffect(StatusEffects.INVISIBILITY).getDuration() <= 3)
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 10));
                } else crouchTime++;
            }
        }
    }

    public static class TentacleArm1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "tentaclearm1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public TentacleArm1Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.ENTITY_INTERACTION_RANGE, MODIFIER);
            modifierMultimap.put(EntityAttributes.BLOCK_INTERACTION_RANGE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.ARM);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.ARM, MutationTrees.mutationToCodec(tentacleArm1, 0,
                    ColorHelper.getArgb(99, 141, 153), ColorHelper.getArgb(125, 164, 137), 0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.ARM,true);
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class TentacleArm2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "tentaclearm2_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        public TentacleArm2Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.ENTITY_INTERACTION_RANGE, MODIFIER);
            modifierMultimap.put(EntityAttributes.BLOCK_INTERACTION_RANGE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.ARM);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.ARM, MutationTrees.mutationToCodec(tentacleArm1, 0,
                    ColorHelper.getArgb(99, 141, 153), ColorHelper.getArgb(125, 164, 137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.ARM,true);
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }
    }

    public static class InkInvisMutation extends Mutation {
        private int cooldown = 0;

        public InkInvisMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.MISC);
            MutationAttachments.setPartAttached(player, MutatableParts.MISC, MutationTrees.mutationToCodec(inkInvis, 0,
                    ColorHelper.getArgb(99, 141, 153), ColorHelper.getArgb(125, 164, 137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.MISC,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient) {
                if (this.cooldown <= 0) {
                    this.cooldown = 500;
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 1000, 1), player);

                    ((ServerWorld) player.getWorld()).spawnParticles(ParticleTypes.SQUID_INK, player.getX(), player.getY(), player.getZ(), 3000, 0, 0, 0, 3);

                    player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SQUID_SQUIRT, SoundCategory.PLAYERS, 1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                } else player.sendMessage(Text.translatable("mutations.mutation.cooldown.ink"), true);
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
        }
    }

    public static class InkBlindMutation extends Mutation {
        private int cooldown = 0;

        public InkBlindMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.MISC);
            MutationAttachments.setPartAttached(player, MutatableParts.MISC, MutationTrees.mutationToCodec(inkBlind, 0,
                    ColorHelper.getArgb(99, 141, 153), ColorHelper.getArgb(125, 164, 137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.MISC,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient) {
                if (this.cooldown <= 0) {
                    this.cooldown = 500;
                    List<Entity> colliders = player.getWorld().getOtherEntities(player, Box.of(player.getPos(), 8, 2, 8));
                    for (Entity entity : colliders) {
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 1000, 1), player);
                        }
                    }
                    ((ServerWorld) player.getWorld()).spawnParticles(ParticleTypes.SQUID_INK, player.getX(), player.getY(), player.getZ(), 3000, 0, 0, 0, 3);
                    player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SQUID_SQUIRT, SoundCategory.PLAYERS, 1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));

                } else player.sendMessage(Text.translatable("mutations.mutation.cooldown.ink"), true);
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
        }
    }

    public static class InkGlowMutation extends Mutation {
        private int cooldown = 0;

        public InkGlowMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.MISC);
            MutationAttachments.setPartAttached(player, MutatableParts.MISC, MutationTrees.mutationToCodec(inkGlow, 0,
                    ColorHelper.getArgb(99, 141, 153), ColorHelper.getArgb(125, 164, 137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.MISC,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient) {
                if (this.cooldown <= 0) {
                    this.cooldown = 500;
                    List<Entity> colliders = player.getWorld().getOtherEntities(player, Box.of(player.getPos(), 8, 2, 8));
                    for (Entity entity : colliders) {
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1000), player);
                        }
                    }
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 1000, 1), player);
                    ((ServerWorld) player.getWorld()).spawnParticles(ParticleTypes.GLOW_SQUID_INK, player.getX(), player.getY(), player.getZ(), 3000, 0, 0, 0, 3);
                    player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SQUID_SQUIRT, SoundCategory.PLAYERS, 1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));

                } else player.sendMessage(Text.translatable("mutations.mutation.cooldown.ink"), true);
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
        }
    }

    public static class InkFireyMutation extends Mutation {
        private int cooldown = 0;

        public InkFireyMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.MISC);
            MutationAttachments.setPartAttached(player, MutatableParts.MISC, MutationTrees.mutationToCodec(inkFirey, 0,
                    ColorHelper.getArgb(99, 141, 153), ColorHelper.getArgb(125, 164, 137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.MISC,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient) {
                if (this.cooldown <= 0) {
                    this.cooldown = 500;
                    List<Entity> colliders = player.getWorld().getOtherEntities(player, Box.of(player.getPos(), 8, 2, 8));
                    for (Entity entity : colliders) {
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1000), player);
                            entity.setOnFireFor(10F);
                        }
                    }
                    ((ServerWorld) player.getWorld()).spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, player.getX(), player.getY(), player.getZ(), 2000, 0, 0, 0, 3);
                    ((ServerWorld) player.getWorld()).spawnParticles(ParticleTypes.GLOW_SQUID_INK, player.getX(), player.getY(), player.getZ(), 1500, 0, 0, 0, 3);
                    player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SQUID_SQUIRT, SoundCategory.PLAYERS, 1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                } else player.sendMessage(Text.translatable("mutations.mutation.cooldown.ink"), true);
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
        }
    }

    public static class SiphonJetMutation extends Mutation {
        private int cooldown = 0;
        private int spawnParticles = 100;

        public SiphonJetMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(siphonJet, 0,
                    ColorHelper.getArgb(34, 59, 77), ColorHelper.getArgb(56, 82, 101),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient) {
                if (this.cooldown <= 0) {
                    this.cooldown = 100;
                    MutationAttachments.setPartAnimating(player, MutatableParts.TORSO, true, player.age);
                    Vec3d lookVec = player.getRotationVector(player.getPitch(),player.headYaw).multiply(3);
                    player.addVelocity(lookVec.x,lookVec.y+0.1,lookVec.z);
                    player.velocityModified=true;
                    player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ITEM_TRIDENT_RIPTIDE_3.value(), SoundCategory.PLAYERS, 1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                    spawnParticles = 0;


                } else player.sendMessage(Text.translatable("mutations.mutation.cooldown.jet"), true);
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
            if (!player.getWorld().isClient) {
                if (spawnParticles < 20) {
                    float plusAngle = (float) (MathHelper.wrapDegrees(player.bodyYaw + 180f) + MathHelper.DEGREES_PER_RADIAN * MathHelper.atan2(3, 4));
                    float minusAngle = (float) (MathHelper.wrapDegrees(player.bodyYaw + 180f) - MathHelper.DEGREES_PER_RADIAN * MathHelper.atan2(3 , 4));
                    Vec3d leftJet = player.getRotationVector(0, plusAngle).multiply(0.25);
                    Vec3d rightJet = player.getRotationVector(0, minusAngle).multiply(0.25);

                    if(player.isGliding() || player.isInSwimmingPose()){
                        leftJet = leftJet.add(player.getRotationVector(0,player.bodyYaw).normalize().multiply(0.25));
                        rightJet = rightJet.add(player.getRotationVector(0,player.bodyYaw).normalize().multiply(0.25));
                        ((ServerWorld) player.getWorld()).spawnParticles(ParticleTypes.FALLING_WATER, player.getX() + leftJet.x, player.getY() + leftJet.y + 0.5, player.getZ() + leftJet.z, 100, 0.1, 0, 0.1, 0.2);
                        ((ServerWorld) player.getWorld()).spawnParticles(ParticleTypes.FALLING_WATER, player.getX() + rightJet.x, player.getY() + rightJet.y + 0.5, player.getZ() + rightJet.z, 100, 0.1, 0, 0.1, 0.2);
                    }else {
                        ((ServerWorld) player.getWorld()).spawnParticles(ParticleTypes.FALLING_WATER, player.getX() + leftJet.x, player.getY() + leftJet.y + 1 , player.getZ() + leftJet.z, 100, 0.1, 0, 0.1, 0.2);
                        ((ServerWorld) player.getWorld()).spawnParticles(ParticleTypes.FALLING_WATER, player.getX() + rightJet.x, player.getY() + rightJet.y + 1 , player.getZ() + rightJet.z, 100, 0.1, 0, 0.1, 0.2);
                    }
                    spawnParticles++;
                }
            }
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }
    }

    public static class FireyJetMutation extends Mutation {
        private int cooldown = 0;

        public FireyJetMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(fireyJet, 0,
                    ColorHelper.getArgb(99, 141, 153), ColorHelper.getArgb(125, 164, 137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient) {
                if (this.cooldown <= 0) {
                    this.cooldown = 500;
                } else player.sendMessage(Text.translatable("mutations.mutation.cooldown.jet"), true);
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }
    }


}

