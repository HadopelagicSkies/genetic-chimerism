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
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class HornedTree {
    public static final MutationTrees horned = MutationTrees.addTree(new ArrayList<Mutation>(), "horned", Identifier.ofVanilla("textures/item/goat_horn.png"));

    public static void initialize() {
    }

    public static final Mutation horned_1 = horned.addToTree(new Horned1("horned_1", "horned", null));
    public static final Mutation horned_2 = horned.addToTree(new Mutation("horned_2", "horned", horned_1));
    public static final Mutation horned_3 = horned.addToTree(new Mutation("horned_3", "horned", horned_2));
    public static final Mutation horned_4 = horned.addToTree(new Mutation("horned_4", "horned", horned_3));

    public static final Mutation horned_5 = horned.addToTree(new Mutation("horned_5", "horned", horned_1));
    public static final Mutation horned_6 = horned.addToTree(new Mutation("horned_6", "horned", horned_1));
    public static final Mutation horned_7 = horned.addToTree(new Mutation("horned_7", "horned", horned_6));
    public static final Mutation horned_8 = horned.addToTree(new Mutation("horned_8", "horned", horned_7));

    public static final Mutation ramhorns1 = horned.addToTree(new RamHorns1Mutation("ramhorns1", "horned", horned_3, MutatableParts.HEAD));

    public static class Horned1 extends Mutation{
        public Horned1(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
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
        private boolean ramming = false;
        private int rammingTime = 0;
        private int cooldown = 0;

        public RamHorns1Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq,parts);
            modifierMultimap.put(EntityAttributes.KNOCKBACK_RESISTANCE, RAMHORNS1_MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            if(player.getAttached(MutationAttachments.HEAD_MUTATION) != null)
                player.removeAttached(MutationAttachments.HEAD_MUTATION);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            player.setAttached(MutationAttachments.HEAD_MUTATION, MutationTrees.mutationToCodec(ramhorns1,0,0,0));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            player.removeAttached(MutationAttachments.HEAD_MUTATION);
        }

        @Override
        public void mutationAction(PlayerEntity player){
            if (!ramming) this.ramming = rammingTime == 0 && cooldown == 0 && !player.isGliding();
            if (cooldown > 0) player.sendMessage(Text.translatable("mutations.mutation.cooldown.ramhorns1"),true);
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
                this.rammingTime++;
                if(rammingTime > 60 || !ramming){

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
    }



}
