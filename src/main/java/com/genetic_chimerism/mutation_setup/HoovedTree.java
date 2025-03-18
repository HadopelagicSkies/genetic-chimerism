package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

import java.util.ArrayList;

public class HoovedTree {
    public static final MutationTrees hooved = MutationTrees.addTree(new ArrayList<Mutation>(), "hooved", Identifier.ofVanilla("textures/item/golden_horse_armor.png"));

    public static void initialize() {
    }

    public static final Mutation speed1 = hooved.addToTree(new Speed1Mutation("speed1", "hooved", null));
    public static final Mutation speed2 = hooved.addToTree(new Speed2Mutation("speed2", "hooved", speed1));
    public static final Mutation speed3 = hooved.addToTree(new Speed3Mutation("speed3", "hooved", speed2));
    public static final Mutation speed4 = hooved.addToTree(new Speed4Mutation("speed4", "hooved", speed3));

    public static final Mutation sprint1 = hooved.addToTree(new Speed4Mutation("sprint1", "hooved", speed3));
    public static final Mutation sprint2 = hooved.addToTree(new Speed4Mutation("sprint2", "hooved", sprint1));
    public static final Mutation lavaSprint = hooved.addToTree(new Speed4Mutation("lavasprint", "hooved", sprint1));

    public static final Mutation step1 = hooved.addToTree(new Step1Mutation("step1", "hooved", null));
    public static final Mutation step2 = hooved.addToTree(new Step2Mutation("step2", "hooved", step1));
    public static final Mutation step3 = hooved.addToTree(new Step3Mutation("step3", "hooved", step2));
    public static final Mutation step4 = hooved.addToTree(new Step4Mutation("step4", "hooved", step3));

    public static final Mutation hooves = hooved.addToTree(new HoovesMutation("hooves", "hooved", step2, MutatableParts.LEG));
    public static final Mutation centaur = hooved.addToTree(new CentaurMutation("centaur", "hooved", step3, MutatableParts.LEG, MutatableParts.TAIL));
    public static final Mutation camelHump = hooved.addToTree(new CamelHumpMutation("camelHump", "hooved", step2, MutatableParts.TORSO));


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

        public HoovesMutation(String mutID, String treeID, Mutation prereq, MutatableParts part) {
            super(mutID, treeID, prereq, part);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.LEG);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, MutationTrees.mutationToCodec(hooves, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.LEG);
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true));
        }
    }

    public static class CentaurMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "centaur_modifier"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public CentaurMutation(String mutID, String treeID, Mutation prereq, MutatableParts part1, MutatableParts part2) {
            super(mutID, treeID, prereq, part1, part2);
            modifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.LEG);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, MutationTrees.mutationToCodec(centaur, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false));
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(centaur, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.LEG);
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true));
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut2 = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, new MutationBodyInfo(partMut2.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true));
        }
    }

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
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TORSO);
            MutationAttachments.setPartAttached(player, MutatableParts.TORSO, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true));
        }
    }

}
