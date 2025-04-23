package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

import java.util.ArrayList;
import java.util.List;

public class InvertebrateTree {
    public static final MutationTrees invertebrate = MutationTrees.addTree(new ArrayList<Mutation>(), "invertebrate", Identifier.ofVanilla("textures/mob_effect/weaving.png"));

    public static void initialize() {
        AttackEntityCallback.EVENT.register(((playerEntity, world, hand, entity, entityHitResult) -> {
            LivingEntity livingEntity;
            if(!(entity instanceof LivingEntity)) return ActionResult.PASS;
            else livingEntity = (LivingEntity) entity;

            List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(playerEntity);
            if(mutations != null && mutations.contains(MutationTrees.mutationToCodec(poisonChance1))) {
                double poisonChance = 25;
                int poisonDamage = 1;
                if(mutations.contains(MutationTrees.mutationToCodec(poisonChance2))) {
                    poisonChance += 25;
                    if(mutations.contains(MutationTrees.mutationToCodec(poisonChance3))) {
                        poisonChance += 25;
                        if(mutations.contains(MutationTrees.mutationToCodec(poisonChance4))) {
                            poisonChance += 25;
                        }
                        if(mutations.contains(MutationTrees.mutationToCodec(scorpionStinger1))) {
                            poisonDamage += 1;
                            if(mutations.contains(MutationTrees.mutationToCodec(scorpionStinger2))) {
                                poisonDamage += 1;
                            }
                        }
                    }
                }
                if(playerEntity.getRandom().nextBetween(0,100) <= poisonChance){
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200,poisonDamage));
                }
            }
                return ActionResult.PASS;
        }));

    }

    public static final Mutation moveEff1 = invertebrate.addToTree(new MoveEff1Mutation("moveEff1", "invertebrate", null));
    public static final Mutation moveEff2 = invertebrate.addToTree(new MoveEff2Mutation("moveEff2", "invertebrate", moveEff1));
    public static final Mutation moveEff3 = invertebrate.addToTree(new MoveEff3Mutation("moveEff3", "invertebrate", moveEff2));

    public static final Mutation sneakSpeed1 = invertebrate.addToTree(new SneakSpeed1Mutation("sneakSpeed1", "invertebrate", moveEff2));
    public static final Mutation sneakSpeed2 = invertebrate.addToTree(new SneakSpeed1Mutation("sneakSpeed2", "invertebrate", sneakSpeed1));

    public static final Mutation wallClimb = invertebrate.addToTree(new Mutation("wallClimb", "invertebrate", moveEff1));
    public static final Mutation spiderAbdomen = invertebrate.addToTree(new MothAntennaeMutation("spiderAbdomen", "invertebrate", wallClimb,MutatableParts.TAIL));
    public static final Mutation mothAntennae = invertebrate.addToTree(new MothAntennaeMutation("mothAntennae", "invertebrate", wallClimb,MutatableParts.HEAD));
    public static final Mutation arachneBody = invertebrate.addToTree(new ArachneBodyMutation("arachneBody", "invertebrate", spiderAbdomen,MutatableParts.TAIL, MutatableParts.LEG));

    public static final Mutation poisonChance1 = invertebrate.addToTree(new Mutation("poisonChance1", "invertebrate", null));
    public static final Mutation poisonChance2 = invertebrate.addToTree(new Mutation("poisonChance2", "invertebrate", poisonChance1));
    public static final Mutation poisonChance3 = invertebrate.addToTree(new Mutation("poisonChance3", "invertebrate", poisonChance2));
    public static final Mutation poisonChance4 = invertebrate.addToTree(new Mutation("poisonChance4", "invertebrate", poisonChance3));

    public static final Mutation scorpionStinger1 = invertebrate.addToTree(new ScorpionStinger1Mutation("scorpionStinger1", "invertebrate", poisonChance3,MutatableParts.TAIL));
    public static final Mutation scorpionStinger2 = invertebrate.addToTree(new ScorpionStinger2Mutation("scorpionStinger2", "invertebrate", scorpionStinger1,MutatableParts.TAIL));

    public static final Mutation hivePheromones = invertebrate.addToTree(new Mutation("hivePheromones", "invertebrate", poisonChance1));
    public static final Mutation beeAbdomen = invertebrate.addToTree(new BeeAbdomenMutation("beeAbdomen", "invertebrate", hivePheromones,MutatableParts.TAIL));
    public static final Mutation queenPheromones = invertebrate.addToTree(new Mutation("queenPheromones", "invertebrate", beeAbdomen));

    public static class MoveEff1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "moveeff1_modifier"), 0.33, EntityAttributeModifier.Operation.ADD_VALUE);

        public MoveEff1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MOVEMENT_EFFICIENCY, MODIFIER);
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

    public static class MoveEff2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "moveeff2_modifier"), 0.33, EntityAttributeModifier.Operation.ADD_VALUE);

        public MoveEff2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MOVEMENT_EFFICIENCY, MODIFIER);
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

    public static class MoveEff3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "moveeff3_modifier"), 0.34, EntityAttributeModifier.Operation.ADD_VALUE);

        public MoveEff3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MOVEMENT_EFFICIENCY, MODIFIER);
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

    public static class SneakSpeed1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "sneakspeed1_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public SneakSpeed1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.SNEAKING_SPEED, MODIFIER);
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

    public static class SneakSpeed2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "sneakspeed2_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public SneakSpeed2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.SNEAKING_SPEED, MODIFIER);
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

    public static class SpiderAbdomenMutation extends Mutation {
        private int cooldown = 0;

        public SpiderAbdomenMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(spiderAbdomen, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {

        }
    }

    public static class MothAntennaeMutation extends Mutation {
        private int cooldown = 0;

        public MothAntennaeMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(mothAntennae, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.HEAD,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {

        }
    }


    public static class ArachneBodyMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "arachnebody_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);
        private int cooldown = 0;

        public ArachneBodyMutation(String mutID, String treeID, Mutation prereq, MutatableParts part1, MutatableParts part2) {
            super(mutID, treeID, prereq, part1,part2);
            modifierMultimap.put(EntityAttributes.SNEAKING_SPEED, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.LEG);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, MutationTrees.mutationToCodec(arachneBody, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(arachneBody, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.LEG,true);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {

        }
    }

    public static class ScorpionStinger1Mutation extends Mutation {
        private int cooldown = 0;

        public ScorpionStinger1Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(scorpionStinger1, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient && MutationAttachments.getPartAttached(player,MutatableParts.TAIL).growth() >= this.getMaxGrowth()) {
                if (this.cooldown <= 0) {
                    MutationAttachments.setPartAnimating(player,MutatableParts.TAIL , true);
                    this.cooldown = 300;
                    int range = 4;
                    Vec3d boxPos = player.getPos();
                    for (int i = 0; i < range * 2; i++) {
                        List<Entity> colliders = player.getWorld().getOtherEntities(player, Box.of(boxPos, 2, 2, 2));
                        for (Entity entity : colliders) {
                            if (entity instanceof LivingEntity) {
                                entity.addVelocity(entity.getPos().subtract(player.getPos()).add(0,.5,0).multiply(0.5));
                                entity.damage((ServerWorld) player.getWorld(), new DamageSource((RegistryEntry<DamageType>) DamageTypes.STING, player),2F);
                                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 10, 1), player);
                            }
                        }
                        if (!colliders.isEmpty()) {
                            player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_BEE_STING, SoundCategory.PLAYERS, 2F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                            break;
                        }
                        boxPos = boxPos.add(player.getRotationVector().normalize().multiply(0.5));
                        BlockPos boxBlock = BlockPos.ofFloored(Math.round(boxPos.x), Math.round(boxPos.y), Math.round(boxPos.z));
                        if (player.getWorld().getBlockState(boxBlock).isSolidBlock(player.getWorld(),boxBlock)){
                            break;
                        }
                    }
                }
                else player.sendMessage(Text.translatable("mutations.mutation.cooldown.tailslap"),true);
            }
        }
    }

    public static class ScorpionStinger2Mutation extends Mutation {
        private int cooldown = 0;

        public ScorpionStinger2Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(scorpionStinger2, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient && MutationAttachments.getPartAttached(player,MutatableParts.TAIL).growth() >= this.getMaxGrowth()) {
                if (this.cooldown <= 0) {
                    MutationAttachments.setPartAnimating(player,MutatableParts.TAIL , true);
                    this.cooldown = 300;
                    int range = 4;
                    Vec3d boxPos = player.getPos();
                    for (int i = 0; i < range * 2; i++) {
                        List<Entity> colliders = player.getWorld().getOtherEntities(player, Box.of(boxPos, 2, 2, 2));
                        for (Entity entity : colliders) {
                            if (entity instanceof LivingEntity) {
                                entity.addVelocity(entity.getPos().subtract(player.getPos()).add(0,.5,0).multiply(0.5));
                                entity.damage((ServerWorld) player.getWorld(), new DamageSource((RegistryEntry<DamageType>) DamageTypes.STING, player),4F);
                                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 10, 2), player);
                            }
                        }
                        if (!colliders.isEmpty()) {
                            player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_BEE_STING, SoundCategory.PLAYERS, 2F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                            break;
                        }
                        boxPos = boxPos.add(player.getRotationVector().normalize().multiply(0.5));
                        BlockPos boxBlock = BlockPos.ofFloored(Math.round(boxPos.x), Math.round(boxPos.y), Math.round(boxPos.z));
                        if (player.getWorld().getBlockState(boxBlock).isSolidBlock(player.getWorld(),boxBlock)){
                            break;
                        }
                    }
                }
                else player.sendMessage(Text.translatable("mutations.mutation.cooldown.tailslap"),true);
            }
        }
    }

    public static class BeeAbdomenMutation extends Mutation {
        private int cooldown = 0;
        private int storedHoney = 0;
        private int spellIndex = 0;

        public BeeAbdomenMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(beeAbdomen, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (!player.getWorld().isClient &&player.isSneaking()){
                boolean hasQueenMut = MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(queenPheromones));
                spellIndex = spellIndex == (hasQueenMut ? 4 : 3) ? 0 : spellIndex+1;
                player.sendMessage(Text.translatable("mutations.mutation.beeAbdomen.beeSelected"),true);
            }else if(!player.getWorld().isClient){
                if (this.cooldown <= 0) {
                    this.cooldown = 500;
                    int honeyCost=0;

                    if(spellIndex==0){
                        honeyCost = 50;
                        if (storedHoney >= honeyCost){

                        }
                    }
                    else if(spellIndex==1){
                        honeyCost = 50;
                        if (storedHoney >= honeyCost){

                        }
                    }
                    else if(spellIndex==2){
                        honeyCost = 50;
                        if (storedHoney >= honeyCost){

                        }
                    }
                    else if(spellIndex==3){
                        honeyCost = 50;
                        if (storedHoney >= honeyCost){

                        }
                    }
                    else if(spellIndex==4){
                        honeyCost = 50;
                        if (storedHoney >= honeyCost){

                        }
                    }
                    storedHoney -= honeyCost;
                }
                else player.sendMessage(Text.translatable("mutations.mutation.cooldown.beeMagic"),true);
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (storedHoney < 2000 && player.getRandom().nextBetween(0,100) < 10){
                storedHoney++;
            }

        }
    }


}
