package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BrushableBlock;
import net.minecraft.command.argument.ParticleEffectArgumentType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

import java.util.ArrayList;
import java.util.List;

public class TuskedTree {
    public static final MutationTrees tusked = MutationTrees.addTree(new ArrayList<Mutation>(), "tusked", Identifier.ofVanilla("textures/mob_effect/hunger.png"));

    public static void initialize() {
    }

    public static final Mutation bonusFood1 = tusked.addToTree(new Mutation("bonusFood1", "tusked", null));
    public static final Mutation bonusFood2 = tusked.addToTree(new Mutation("bonusFood2", "tusked", bonusFood1));
    public static final Mutation bonusFood3 = tusked.addToTree(new Mutation("bonusFood3", "tusked", bonusFood2));
    public static final Mutation bonusFood4 = tusked.addToTree(new Mutation("bonusFood4", "tusked", bonusFood3));

    public static final Mutation slowHunger1 = tusked.addToTree(new Mutation("slowHunger1", "tusked", bonusFood2));
    public static final Mutation slowHunger2 = tusked.addToTree(new Mutation("slowHunger2", "tusked", slowHunger1));

    public static final Mutation tusks = tusked.addToTree(new TusksMutation("tusks", "tusked", slowHunger1,MutatableParts.HEAD));

    public static final Mutation noSickness = tusked.addToTree(new NoSicknessMutation("noSickness", "tusked", bonusFood1));
    public static final Mutation maxHealth1 = tusked.addToTree(new MaxHealth1Mutation("maxHealth1", "tusked", noSickness));
    public static final Mutation maxHealth2 = tusked.addToTree(new MaxHealth2Mutation("maxHealth2", "tusked", maxHealth1));
    public static final Mutation maxHealth3 = tusked.addToTree(new MaxHealth3Mutation("maxHealth3", "tusked", maxHealth2));

    public static final Mutation sniffSnout = tusked.addToTree(new SniffSnoutMutation("sniffSnout", "tusked", maxHealth2,MutatableParts.HEAD));


    public static class NoSicknessMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "nosickness_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        public NoSicknessMutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MAX_HEALTH, MODIFIER);
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

    public static class MaxHealth1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "maxhealth1_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        public MaxHealth1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MAX_HEALTH, MODIFIER);
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

    public static class MaxHealth2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "maxhealth2_modifier"), 4, EntityAttributeModifier.Operation.ADD_VALUE);

        public MaxHealth2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MAX_HEALTH, MODIFIER);
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

    public static class MaxHealth3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "maxhealth3_modifier"), 6, EntityAttributeModifier.Operation.ADD_VALUE);

        public MaxHealth3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MAX_HEALTH, MODIFIER);
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

    public static class TusksMutation extends Mutation {
        public TusksMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(tusks,0,
                    ColorHelper.getArgb(0,0,0),ColorHelper.getArgb(0,0,0),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.HEAD,true);
        }

        @Override
        public int getMaxGrowth() {
            return 200;
        }
    }

    public static class SniffSnoutMutation extends Mutation {
        public SniffSnoutMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(sniffSnout,0,
                    ColorHelper.getArgb(0,0,0),ColorHelper.getArgb(0,0,0),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.HEAD,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if(player.getWorld() instanceof ServerWorld serverWorld){
                int boxRadius = 64;
                List<BlockPos> archaeologyBlocks = new ArrayList<>(List.of());
                for (int i = -boxRadius; i < boxRadius; i++) {
                    for (int k = -boxRadius; k < boxRadius; k++) {
                        for (int j= -boxRadius; j < boxRadius; j++) {
                            if(serverWorld.testBlockState(BlockPos.ofFloored(i,k,j),(blockState)-> blockState.getBlock() instanceof BrushableBlock)){
                                archaeologyBlocks.add(BlockPos.ofFloored(i,k,j));
                            }
                        }
                    }
                }
                for(BlockPos block : archaeologyBlocks) {
                    //serverWorld.spawnParticles(player, ,true,true,block.toCenterPos().x,block.toCenterPos().y,block.toCenterPos().z,);
                }
            }
        }

        @Override
        public int getMaxGrowth() {
            return 200;
        }
    }


}
