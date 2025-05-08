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
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

import java.util.ArrayList;
import java.util.List;

public class AquaticTree {
    public static final MutationTrees aquatic = MutationTrees.addTree(new ArrayList<>(), "aquatic", Identifier.ofVanilla("textures/mob_effect/dolphins_grace.png"));

    public static void initialize() {
    }

    public static final Mutation gills1 = aquatic.addToTree(new Gills1Mutation("gills1", "aquatic", null ));
    public static final Mutation gills2 = aquatic.addToTree(new Gills2Mutation("gills2", "aquatic", gills1 ));
    public static final Mutation gills3 = aquatic.addToTree(new Gills3Mutation("gills3", "aquatic", gills2 ));
    public static final Mutation gills4 = aquatic.addToTree(new Gills4Mutation("gills4", "aquatic", gills3 ));

    public static final Mutation uwMineSpeed1 = aquatic.addToTree(new UWMineSpeed1Mutation("uwMineEff1", "aquatic", gills2 ));
    public static final Mutation UwMineSpeed2 = aquatic.addToTree(new UWMineSpeed2Mutation("uwMineEff2", "aquatic", uwMineSpeed1 ));

    public static final Mutation fastswim1 = aquatic.addToTree(new FastSwim1Mutation("fastswim1", "aquatic", null ));
    public static final Mutation fastswim2 = aquatic.addToTree(new FastSwim2Mutation("fastswim2", "aquatic", fastswim1 ));
    public static final Mutation fastswim3 = aquatic.addToTree(new FastSwim3Mutation("fastswim3", "aquatic", fastswim2 ));
    public static final Mutation fastswim4 = aquatic.addToTree(new FastSwim4Mutation("fastswim4", "aquatic", fastswim3 ));

    public static final Mutation sharkTail = aquatic.addToTree(new SharkTailMutation("sharktail", "aquatic", fastswim3, MutatableParts.TAIL));
    public static final Mutation thresherTail = aquatic.addToTree(new ThresherTailMutation("threshertail", "aquatic", sharkTail, MutatableParts.TAIL));

    public static final Mutation fishTail = aquatic.addToTree(new FishTailMutation("fishtail", "aquatic", fastswim2, MutatableParts.TAIL));
    public static final Mutation mermaidTail = aquatic.addToTree(new MermaidTailMutation("mermaidtail", "aquatic", fishTail,MutatableParts.TAIL,MutatableParts.LEG));

    public static class Gills1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "gills1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, MODIFIER);
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

    public static class Gills2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "gills2_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, MODIFIER);
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

    public static class Gills3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "gills3_modifier"), -1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, MODIFIER);
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

    public static class Gills4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "gills4_modifier"), -2, EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, MODIFIER);
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

    public static class UWMineSpeed1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "uw_mine_speed1_modifier"), 0.3, EntityAttributeModifier.Operation.ADD_VALUE);

        public UWMineSpeed1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.SUBMERGED_MINING_SPEED, MODIFIER);
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

    public static class UWMineSpeed2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "uw_mine_speed2_modifier"), 0.3, EntityAttributeModifier.Operation.ADD_VALUE);

        public UWMineSpeed2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.SUBMERGED_MINING_SPEED, MODIFIER);
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

    public static class FastSwim1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fastswim1_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);

        public FastSwim1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
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

    public static class FastSwim2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fastswim2_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);

        public FastSwim2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
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

    public static class FastSwim3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fastswim3_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public FastSwim3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
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

    public static class FastSwim4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fastswim4_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public FastSwim4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
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

    public static class SharkTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "sharktail_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);

        public SharkTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(sharkTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(135,169,179),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }


    public static class ThresherTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "threshertail_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);
        private int cooldown = 0;

        public ThresherTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(thresherTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(135,169,179),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient && MutationAttachments.getPartAttached(player,MutatableParts.TAIL).growth() >= this.getMaxGrowth()) {
                if (this.cooldown <= 0) {
                    MutationAttachments.setPartAnimating(player, MutatableParts.TAIL,true, player.age);
                    this.cooldown = 300;
                    int range = 4;
                    Vec3d boxPos = player.getPos();
                    for (int i = 0; i < range * 2; i++) {
                        List<Entity> colliders = player.getWorld().getOtherEntities(player, Box.of(boxPos, 2, 2, 2));
                        for (Entity entity : colliders) {
                            if (entity instanceof LivingEntity)
                                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 400, 20), player);
                        }
                        if (!colliders.isEmpty()) {
                            player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_TROPICAL_FISH_FLOP, SoundCategory.PLAYERS, 2F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                            break;
                        }
                        boxPos = boxPos.add(player.getRotationVector().normalize().multiply(0.5));
                        BlockPos boxBlock = BlockPos.ofFloored(Math.round(boxPos.x), Math.round(boxPos.y), Math.round(boxPos.z));
                        if (player.getWorld().getBlockState(boxBlock).isSolidBlock(player.getWorld(),boxBlock)) {
                            break;
                        }
                    }
                }
                else player.sendMessage(Text.translatable("mutations.mutation.cooldown.tailslap"),true);
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            if (partMut != null && partMut.isReceding() && partMut.growth() <= 2) {
                MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
                List<MutationInfo> mutList =  MutationAttachments.getMutationsAttached(player);
                mutList.remove(MutationTrees.mutationToCodec(thresherTail));
                MutationAttachments.setMutationsAttached(player,mutList);
                MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(sharkTail,partMut.patternIndex(),
                        partMut.color1(),partMut.color2(),sharkTail.getMaxGrowth()-3, true,
                        MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
            }
        }

        @Override
        public int getMaxGrowth() {
            return 500;
        }
    }

    public static class FishTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fishtail_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public FishTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(fishTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class MermaidTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "mermaidtail_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);
        private int cooldown =0;

        public MermaidTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts part1, MutatableParts part2) {
            super(mutID, treeID, prereq, part1,part2);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.removePartAttached(player, MutatableParts.LEG);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(mermaidTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, MutationTrees.mutationToCodec(mermaidTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if(!player.getWorld().isClient){
                cooldown = 1000;
                List<Entity> colliders = player.getWorld().getOtherEntities(player,Box.of(player.getPos(), 16, 16, 16));
                for(Entity entity : colliders){
                    if (entity instanceof PlayerEntity) {
                        ((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE,3600));
                        ((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER,3600));
                    }
                }

                player.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE,3600));
                player.getWorld().playSound(null,player.getBlockPos(),SoundEvents.BLOCK_CONDUIT_ACTIVATE, SoundCategory.PLAYERS,1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));

            }
            else player.sendMessage(Text.translatable("mutations.mutation.cooldown.buff"),true);
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
            MutationAttachments.setPartReceding(player, MutatableParts.LEG,true);
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }


}
