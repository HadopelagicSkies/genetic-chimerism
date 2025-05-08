package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HoovedTree {
    public static final MutationTrees hooved = MutationTrees.addTree(new ArrayList<Mutation>(), "hooved", Identifier.ofVanilla("textures/item/golden_horse_armor.png"));

    public static void initialize() {
    }

    public static final Mutation speed1 = hooved.addToTree(new Speed1Mutation("speed1", "hooved", null));
    public static final Mutation speed2 = hooved.addToTree(new Speed2Mutation("speed2", "hooved", speed1));
    public static final Mutation speed3 = hooved.addToTree(new Speed3Mutation("speed3", "hooved", speed2));
    public static final Mutation speed4 = hooved.addToTree(new Speed4Mutation("speed4", "hooved", speed3));

    public static final Mutation sprint1 = hooved.addToTree(new Sprint1Mutation("sprint1", "hooved", speed3));
    public static final Mutation sprint2 = hooved.addToTree(new Sprint2Mutation("sprint2", "hooved", sprint1));
    public static final Mutation lavaSprint = hooved.addToTree(new LavaSprintMutation("lavasprint", "hooved", sprint1));

    public static final Mutation step1 = hooved.addToTree(new Step1Mutation("step1", "hooved", null));
    public static final Mutation step2 = hooved.addToTree(new Step2Mutation("step2", "hooved", step1));
    public static final Mutation step3 = hooved.addToTree(new Step3Mutation("step3", "hooved", step2));
    public static final Mutation step4 = hooved.addToTree(new Step4Mutation("step4", "hooved", step3));

    public static final Mutation hooves = hooved.addToTree(new HoovesMutation("hooves", "hooved", step3, MutatableParts.LEG));
    //public static final Mutation centaur = hooved.addToTree(new CentaurMutation("centaur", "hooved", hooves, MutatableParts.LEG, MutatableParts.TAIL));     probably not able to get this implemented
    public static final Mutation camelHump = hooved.addToTree(new CamelHumpMutation("camelHump", "hooved", step2, MutatableParts.TORSO));

    public static class Sprint1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "sprint1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);
        public Sprint1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
        }

        @Override
        public void tick(PlayerEntity player) {
            if(player.isSprinting() && !player.getAttributes().hasModifierForAttribute(EntityAttributes.MOVEMENT_SPEED,Identifier.of(GeneticChimerism.MOD_ID,"sprint1_modifier"))){
                player.getAttributes().addTemporaryModifiers(modifierMultimap);
            }
            else if(!player.isSprinting()){
                player.getAttributes().removeModifiers(modifierMultimap);
            }
        }
    }

    public static class Sprint2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "sprint1_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);
        public Sprint2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
        }

        @Override
        public void tick(PlayerEntity player) {
            if(player.isSprinting() && !player.getAttributes().hasModifierForAttribute(EntityAttributes.MOVEMENT_SPEED,Identifier.of(GeneticChimerism.MOD_ID,"sprint1_modifier"))){
                player.getAttributes().addTemporaryModifiers(modifierMultimap);
            }
            else if(!player.isSprinting()){
                player.getAttributes().removeModifiers(modifierMultimap);
            }
        }
    }

    public static class LavaSprintMutation extends Mutation {
        public LavaSprintMutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
        }

        @Override
        public void tick(PlayerEntity player) {
            if (player.isSubmergedIn(FluidTags.LAVA) || player.getWorld().getBlockState(player.getBlockPos().down()).getFluidState().getFluid().equals(Fluids.LAVA)) {
                player.setOnGround(true);

            }
        }
    }

    public static class Speed1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "speed1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Speed1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
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

    public static class Speed2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "speed2_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Speed2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
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

    public static class Speed3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "speed3_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Speed3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
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

    public static class Speed4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "speed4_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Speed4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
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

    public static class Step1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "step1_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public Step1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.STEP_HEIGHT, MODIFIER);
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

    public static class Step2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "step2_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public Step2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.STEP_HEIGHT, MODIFIER);
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

    public static class Step3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "step3_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public Step3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.STEP_HEIGHT, MODIFIER);
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

    public static class Step4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "step4_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public Step4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.STEP_HEIGHT, MODIFIER);
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

    public static class HoovesMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "hooves_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);
        private int cooldown;

        public HoovesMutation(String mutID, String treeID, Mutation prereq, MutatableParts part) {
            super(mutID, treeID, prereq, part);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.LEG);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, MutationTrees.mutationToCodec(hooves, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if(!player.getWorld().isClient){
                cooldown = 1200;
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,600,1));
                player.getWorld().playSound(null,player.getBlockPos(), SoundEvents.ENTITY_HORSE_GALLOP, SoundCategory.PLAYERS,1F, MathHelper.nextBetween(player.getWorld().random, 0.8F, 1.2F));

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
            MutationAttachments.setPartReceding(player, MutatableParts.LEG,true);
        }
    }

//    public static class CentaurMutation extends Mutation {
//        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
//        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "centaur_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);
//
//        public CentaurMutation(String mutID, String treeID, Mutation prereq, MutatableParts part1, MutatableParts part2) {
//            super(mutID, treeID, prereq, part1, part2);
//            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
//        }
//
//        @Override
//        public void onApplied(PlayerEntity player) {
//            MutationAttachments.removePartAttached(player, MutatableParts.LEG);
//            player.getAttributes().addTemporaryModifiers(modifierMultimap);
//            MutationAttachments.setPartAttached(player, MutatableParts.LEG, MutationTrees.mutationToCodec(centaur, 0,
//                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
//            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
//            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(centaur, 0,
//                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
//        }
//
//        @Override
//        public void onRemoved(PlayerEntity player) {
//            player.getAttributes().removeModifiers(modifierMultimap);
//            MutationAttachments.setPartReceding(player, MutatableParts.LEG,true);
//            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
//        }
//    }

    public static class CamelHumpMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "camelhump_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);

        public CamelHumpMutation(String mutID, String treeID, Mutation prereq, MutatableParts part) {
            super(mutID, treeID, prereq, part);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TORSO);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, MutationTrees.mutationToCodec(camelHump, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107),0, false,
                    MutationBodyInfo.animationStateFromInts(1, player.age),MutationBodyInfo.animationStateFromInts(0, player.age)));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TORSO,true);
        }
    }

}
