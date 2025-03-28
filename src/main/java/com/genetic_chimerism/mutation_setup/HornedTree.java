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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class HornedTree {
    public static final MutationTrees horned = MutationTrees.addTree(new ArrayList<>(), "horned", Identifier.ofVanilla("textures/item/goat_horn.png"));

    public static void initialize() {
    }

    public static final Mutation armorTough1 = horned.addToTree(new ArmorTough1Mutation("armorTough1", "horned", null));
    public static final Mutation armorTough2 = horned.addToTree(new ArmorTough2Mutation("armorTough2", "horned", armorTough1));
    public static final Mutation armorTough3 = horned.addToTree(new ArmorTough3Mutation("armorTough3", "horned", armorTough2));
    public static final Mutation armorTough4 = horned.addToTree(new ArmorTough4Mutation("armorTough4", "horned", armorTough3));

    public static final Mutation hurtHorns1 = horned.addToTree(new HurtHorns1Mutation("hurtHorns1", "horned", armorTough3, MutatableParts.HEAD));
    public static final Mutation hurtHorns2 = horned.addToTree(new HurtHorns2Mutation("hurtHorns2", "horned", hurtHorns1, MutatableParts.HEAD));

    public static final Mutation knockback1 = horned.addToTree(new Knockback1Mutation("knockback1", "horned", null));
    public static final Mutation knockback2 = horned.addToTree(new Knockback2Mutation("knockback2", "horned", knockback1));
    public static final Mutation knockback3 = horned.addToTree(new Knockback3Mutation("knockback3", "horned", knockback2));
    public static final Mutation knockback4 = horned.addToTree(new Knockback4Mutation("knockback4", "horned", knockback3));


    public static final Mutation ramHorns1 = horned.addToTree(new RamHorns1Mutation("ramHorns1", "horned", knockback2, MutatableParts.HEAD));
    public static final Mutation ramHorns2 = horned.addToTree(new RamHorns2Mutation("ramHorns2", "horned", ramHorns1, MutatableParts.HEAD));
    public static final Mutation ramLegs = horned.addToTree(new RamLegsMutation("ramLegs", "horned", ramHorns1, MutatableParts.LEG));



    public static class ArmorTough1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "armortough1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public ArmorTough1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER);
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

    public static class ArmorTough2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "armortough2_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public ArmorTough2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER);
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

    public static class ArmorTough3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "armortough3_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public ArmorTough3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER);
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

    public static class ArmorTough4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "armortough4_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public ArmorTough4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER);
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

    public static class Knockback1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "knockback1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Knockback1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER);
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

    public static class Knockback2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "knockback2_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Knockback2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER);
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

    public static class Knockback3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "knockback3_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Knockback3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER);
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

    public static class Knockback4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "knockback4_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Knockback4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER);
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

    public static class RamHorns1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "ramhorns1_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);
        private boolean ramming = false;
        private int rammingTime = 0;
        private int cooldown = 0;

        public RamHorns1Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq,parts);
            modifierMultimap.put(EntityAttributes.KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(ramHorns1,0,
                    ColorHelper.getArgb(115,110,99),ColorHelper.getArgb(136,127,107),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public void mutationAction(PlayerEntity player){
            if (!ramming) this.ramming = rammingTime == 0 && cooldown == 0 && !player.isGliding();
            if (cooldown > 0) player.sendMessage(Text.translatable("mutations.mutation.cooldown.ramming"),true);
        }

        @Override
        public void tick(PlayerEntity player){
            if (this.ramming && !player.getWorld().isClient) {
                if (this.cooldown <= 0) this.cooldown = 300;
                player.addVelocity(player.getRotationVector(0F,player.headYaw).multiply(.375));

                List<Entity> colliders = player.getWorld().getOtherEntities(player,Box.of(player.getPos(), 1, 1, 1));
                for(Entity entity : colliders){
                    if (entity instanceof LivingEntity) entity.addVelocity(entity.getPos().subtract(player.getPos()).add(0,.5,0).multiply(1.5));
                }

                if(!colliders.isEmpty()){
                    player.getWorld().playSound(null,player.getBlockPos(),SoundEvents.ENTITY_GOAT_RAM_IMPACT, SoundCategory.PLAYERS,1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                    ramming = false;
                }

                int maxRammingTime = MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(HornedTree.ramLegs)) ? 120:60;
                this.rammingTime++;
                if(rammingTime > maxRammingTime || !ramming){

                    this.ramming=false;
                    this.rammingTime=0;
                }
            } else if (this.ramming && player.getWorld().isClient) {
                player.addVelocity(player.getRotationVector(0F,player.headYaw).multiply(.375));
                List<Entity> colliders = player.getWorld().getOtherEntities(player,Box.of(player.getPos(), 1, 1, 1));
                if(!colliders.isEmpty()){
                    ramming = false;
                }
                this.rammingTime++;
                if(rammingTime > 60 || !ramming){

                    this.ramming=false;
                    this.rammingTime=0;
                }
            } else rammingTime = 0;
            if (this.cooldown > 0) this.cooldown--;
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class RamHorns2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "ramhorns2_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);
        private boolean ramming = false;
        private int rammingTime = 0;
        private int cooldown = 0;

        public RamHorns2Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq,parts);
            modifierMultimap.put(EntityAttributes.KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(ramHorns2,0,
                    ColorHelper.getArgb(115,110,99),ColorHelper.getArgb(136,127,107),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public void mutationAction(PlayerEntity player){
            if (!ramming) this.ramming = rammingTime == 0 && cooldown == 0 && !player.isGliding();
            if (cooldown > 0) player.sendMessage(Text.translatable("mutations.mutation.cooldown.ramming"),true);
        }

        @Override
        public void tick(PlayerEntity player){
            if (this.ramming && !player.getWorld().isClient) {
                if (this.cooldown == 0) this.cooldown = 300;
                player.addVelocity(player.getRotationVector(0F,player.headYaw).multiply(.375));

                List<Entity> colliders = player.getWorld().getOtherEntities(player,Box.of(player.getPos(), 1, 1, 1));
                for(Entity entity : colliders){
                    if (entity instanceof LivingEntity) entity.addVelocity(entity.getPos().subtract(player.getPos()).add(0,.5,0).multiply(1.5));
                }

                if(!colliders.isEmpty()){
                    player.getWorld().playSound(null,player.getBlockPos(),SoundEvents.ENTITY_GOAT_RAM_IMPACT, SoundCategory.PLAYERS,1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                    ramming = false;
                }
                int maxRammingTime = MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(HornedTree.ramLegs)) ? 120:60;
                this.rammingTime++;
                if(rammingTime > maxRammingTime || !ramming){

                    this.ramming=false;
                    this.rammingTime=0;
                }
            } else if (this.ramming && player.getWorld().isClient) {
                player.addVelocity(player.getRotationVector(0F,player.headYaw).multiply(.375));
                List<Entity> colliders = player.getWorld().getOtherEntities(player,Box.of(player.getPos(), 1, 1, 1));
                if(!colliders.isEmpty()){
                    ramming = false;
                }
                this.rammingTime++;
                if(rammingTime > 60 || !ramming){

                    this.ramming=false;
                    this.rammingTime=0;
                }
            } else rammingTime = 0;
            if (this.cooldown > 0) this.cooldown--;
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class HurtHorns1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "hurthorns1_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);
        private boolean ramming = false;
        private int rammingTime = 0;
        private int cooldown = 0;

        public HurtHorns1Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq,parts);
            modifierMultimap.put(EntityAttributes.KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(hurtHorns1,0,
                    ColorHelper.getArgb(115,110,99),ColorHelper.getArgb(136,127,107),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public void mutationAction(PlayerEntity player){
            if (!ramming) this.ramming = rammingTime == 0 && cooldown == 0 && !player.isGliding();
            if (cooldown > 0) player.sendMessage(Text.translatable("mutations.mutation.cooldown.ramming"),true);
        }

        @Override
        public void tick(PlayerEntity player){
            if (this.ramming && !player.getWorld().isClient) {
                if (this.cooldown == 0) this.cooldown = 300;
                player.addVelocity(player.getRotationVector(0F,player.headYaw).multiply(.375));

                List<Entity> colliders = player.getWorld().getOtherEntities(player,Box.of(player.getPos(), 1, 1, 1));
                for(Entity entity : colliders){
                    //make damage instead
                    //if (entity instanceof LivingEntity) entity.addVelocity(entity.getPos().subtract(player.getPos()).add(0,.5,0).multiply(1.5));
                }

                if(!colliders.isEmpty()){
                    player.getWorld().playSound(null,player.getBlockPos(),SoundEvents.ENTITY_GOAT_RAM_IMPACT, SoundCategory.PLAYERS,1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                    ramming = false;
                }
                int maxRammingTime = MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(HornedTree.ramLegs)) ? 120:60;
                this.rammingTime++;
                if(rammingTime > maxRammingTime || !ramming){

                    this.ramming=false;
                    this.rammingTime=0;
                }
            } else if (this.ramming && player.getWorld().isClient) {
                player.addVelocity(player.getRotationVector(0F,player.headYaw).multiply(.375));
                List<Entity> colliders = player.getWorld().getOtherEntities(player,Box.of(player.getPos(), 1, 1, 1));
                if(!colliders.isEmpty()){
                    ramming = false;
                }
                this.rammingTime++;
                if(rammingTime > 60 || !ramming){

                    this.ramming=false;
                    this.rammingTime=0;
                }
            } else rammingTime = 0;
            if (this.cooldown > 0) this.cooldown--;
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class HurtHorns2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "hurthorns2_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);
        private boolean ramming = false;
        private int rammingTime = 0;
        private int cooldown = 0;

        public HurtHorns2Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq,parts);
            modifierMultimap.put(EntityAttributes.KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(hurtHorns2,0,
                    ColorHelper.getArgb(115,110,99),ColorHelper.getArgb(136,127,107),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public void mutationAction(PlayerEntity player){
            if (!ramming) this.ramming = rammingTime == 0 && cooldown == 0 && !player.isGliding();
            if (cooldown > 0) player.sendMessage(Text.translatable("mutations.mutation.cooldown.ramming"),true);
        }

        @Override
        public void tick(PlayerEntity player){
            if (this.ramming && !player.getWorld().isClient) {
                if (this.cooldown == 0) this.cooldown = 300;
                player.addVelocity(player.getRotationVector(0F,player.headYaw).multiply(.375));

                List<Entity> colliders = player.getWorld().getOtherEntities(player,Box.of(player.getPos(), 1, 1, 1));
                for(Entity entity : colliders){
                    // make damage instead
                    //if (entity instanceof LivingEntity) entity.addVelocity(entity.getPos().subtract(player.getPos()).add(0,.5,0).multiply(1.5));
                }

                if(!colliders.isEmpty()){
                    player.getWorld().playSound(null,player.getBlockPos(),SoundEvents.ENTITY_GOAT_RAM_IMPACT, SoundCategory.PLAYERS,1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));
                    ramming = false;
                }
                int maxRammingTime = MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(HornedTree.ramLegs)) ? 120:60;
                this.rammingTime++;
                if(rammingTime > maxRammingTime || !ramming){

                    this.ramming=false;
                    this.rammingTime=0;
                }
            } else if (this.ramming && player.getWorld().isClient) {
                player.addVelocity(player.getRotationVector(0F,player.headYaw).multiply(.375));
                List<Entity> colliders = player.getWorld().getOtherEntities(player,Box.of(player.getPos(), 1, 1, 1));
                if(!colliders.isEmpty()){
                    ramming = false;
                }
                this.rammingTime++;
                if(rammingTime > 60 || !ramming){

                    this.ramming=false;
                    this.rammingTime=0;
                }
            } else rammingTime = 0;
            if (this.cooldown > 0) this.cooldown--;
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class RamLegsMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "ramlegs_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public RamLegsMutation(String mutID, String treeID, Mutation prereq, MutatableParts part) {
            super(mutID, treeID, prereq, part);
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



}
