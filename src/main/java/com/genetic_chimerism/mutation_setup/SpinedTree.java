package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

import java.util.ArrayList;

public class SpinedTree {
    public static final MutationTrees spined = MutationTrees.addTree(new ArrayList<Mutation>(), "spined", Identifier.ofVanilla("textures/mob_effect/resistance.png"));

    public static void initialize() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (player.getStackInHand(hand).isOf(Items.SHEARS) && player.isSneaking())
                if (MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(guardianSpikes2))) {
                    MutationBodyInfo torsoMutation = MutationAttachments.getPartAttached(player, MutatableParts.TORSO);
                    if (torsoMutation.growth() >= guardianSpikes2.getMaxGrowth()) {
                        int count = player.getRandom().nextBetween(2, 5);
                        ItemStack itemStack = new ItemStack(Items.PRISMARINE, count);
                        world.spawnEntity(new ItemEntity(world, player.getX(), player.getY(), player.getZ(), itemStack));
                        ((SpinedTree.GuardianSpikes1Mutation)SpinedTree.guardianSpikes2).breakSpikes(player);
                    }
                }
            return ActionResult.PASS;
        });
    }

    public static final Mutation thornsChance1 = spined.addToTree(new Mutation("thornsChance1", "spined", null));
    public static final Mutation thornsChance2 = spined.addToTree(new Mutation("thornsChance2", "spined", thornsChance1));
    public static final Mutation thornsChance3 = spined.addToTree(new Mutation("thornsChance3", "spined", thornsChance2));

    public static final Mutation smallSpines = spined.addToTree(new SmallSpinesMutation("smallSpines", "spined", thornsChance2,MutatableParts.TORSO));
    public static final Mutation shootQuills = spined.addToTree(new ShootQuillsMutation("shootQuills", "spined", smallSpines,MutatableParts.TORSO));

    public static final Mutation thornsDmg1 = spined.addToTree(new Mutation("thornsDmg1", "spined", null));
    public static final Mutation thornsDmg2 = spined.addToTree(new Mutation("thornsDmg2", "spined", thornsDmg1));
    public static final Mutation thornsDmg3 = spined.addToTree(new Mutation("thornsDmg3", "spined", thornsDmg2));

    public static final Mutation guardianSpikes1 = spined.addToTree(new GuardianSpikes1Mutation("guardianSpikes1", "spined", thornsDmg2,MutatableParts.TORSO));
    public static final Mutation guardianSpikes2 = spined.addToTree(new GuardianSpikes2Mutation("guardianSpikes2", "spined", guardianSpikes1,MutatableParts.TORSO));
    public static final Mutation hardeningSpikes = spined.addToTree(new HardeningSpikesMutation("hardeningSpikes", "spined", guardianSpikes1));

    public static class SmallSpinesMutation extends Mutation {

        public SmallSpinesMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(smallSpines,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
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

    public static class ShootQuillsMutation extends Mutation {

        public ShootQuillsMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(shootQuills,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            super.mutationAction(player);
        }

        public static void breakQuills(PlayerEntity player){
            int currentGrowth = MutationAttachments.getPartAttached(player,MutatableParts.TORSO).growth();
            int maxGrowth = shootQuills.getMaxGrowth();
            int newGrowth=0;

            if(currentGrowth < maxGrowth * 0.5){
                newGrowth = (int) (maxGrowth * 0.25 -1);
            }
            else if(currentGrowth < maxGrowth * 0.75){
                newGrowth = (int) (maxGrowth * 0.5 -1);
            }
            else if(currentGrowth < maxGrowth+5){
                newGrowth = (int) (maxGrowth * 0.75 -1);
            }
            MutationAttachments.setPartGrowth(player, MutatableParts.TORSO,newGrowth);
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }
    }

    public static class GuardianSpikes1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "guardianspikes1_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        public GuardianSpikes1Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);

            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(guardianSpikes1,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }

        public void breakSpikes(PlayerEntity player){
            int currentGrowth = MutationAttachments.getPartAttached(player,MutatableParts.TORSO).growth();
            int maxGrowth = getMaxGrowth();
            int newGrowth=0;

            if(currentGrowth < maxGrowth * 0.5){
                newGrowth = (int) (maxGrowth * 0.25 -1);
            }
            else if(currentGrowth < maxGrowth * 0.75){
                newGrowth = (int) (maxGrowth * 0.5 -1);
            }
            else if(currentGrowth < maxGrowth+5){
                newGrowth = (int) (maxGrowth * 0.75 -1);
            }
            MutationAttachments.setPartGrowth(player, MutatableParts.TORSO,newGrowth);
        }

        @Override
        public int getMaxGrowth() {
            return 1000;
        }
    }

    public static class GuardianSpikes2Mutation extends Mutation {
        public static final EntityAttributeModifier MODIFIER_25 = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "guardianspikes2_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);
        public static final EntityAttributeModifier MODIFIER_50 = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "guardianspikes2_modifier"), 4, EntityAttributeModifier.Operation.ADD_VALUE);
        public static final EntityAttributeModifier MODIFIER_75 = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "guardianspikes2_modifier"), 6, EntityAttributeModifier.Operation.ADD_VALUE);
        public static final EntityAttributeModifier MODIFIER_100 = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "guardianspikes2_modifier"), 8, EntityAttributeModifier.Operation.ADD_VALUE);
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();

        public GuardianSpikes2Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(guardianSpikes2,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
            player.getAttributes().removeModifiers(modifierMultimap);
        }

        @Override
        public void tick(PlayerEntity player) {
            int currentGrowth = MutationAttachments.getPartAttached(player,MutatableParts.TORSO).growth();
            if(currentGrowth == getMaxGrowth() * 0.25 || currentGrowth == getMaxGrowth() * 0.25 +1){
                modifierMultimap.clear();
                modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER_25);
            }
            else if(currentGrowth == getMaxGrowth() * 0.5|| currentGrowth == getMaxGrowth() * 0.5 +1){
                modifierMultimap.clear();
                modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER_50);
            }
            else if(currentGrowth == getMaxGrowth() * 0.75|| currentGrowth == getMaxGrowth() * 0.75 +1){
                modifierMultimap.clear();
                modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER_75);
            }
            else if(currentGrowth == getMaxGrowth()|| currentGrowth == getMaxGrowth()+1){
                modifierMultimap.clear();
                modifierMultimap.put(EntityAttributes.ARMOR_TOUGHNESS, MODIFIER_100);
            }
        }

        public void breakSpikes(PlayerEntity player){
            int currentGrowth = MutationAttachments.getPartAttached(player,MutatableParts.TORSO).growth();
            int maxGrowth = getMaxGrowth();
            int newGrowth=0;

            if(currentGrowth < maxGrowth * 0.5){
                newGrowth = (int) (maxGrowth * 0.25 -1);
            }
            else if(currentGrowth < maxGrowth * 0.75){
                newGrowth = (int) (maxGrowth * 0.5 -1);
            }
            else if(currentGrowth < maxGrowth+5){
                newGrowth = (int) (maxGrowth * 0.75 -1);
            }
            MutationAttachments.setPartGrowth(player, MutatableParts.TORSO,newGrowth);
        }

        @Override
        public int getMaxGrowth() {
            return 1500;
        }
    }

    public static class HardeningSpikesMutation extends Mutation {

        public HardeningSpikesMutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
        }

        @Override
        public void tick(PlayerEntity player) {
            if(player.hasStatusEffect(StatusEffects.CONDUIT_POWER)){
                int newGrowth = MutationAttachments.getPartAttached(player, MutatableParts.TORSO).growth() +1;
                MutationAttachments.setPartGrowth(player, MutatableParts.TORSO,newGrowth);
            }
        }
    }

}
