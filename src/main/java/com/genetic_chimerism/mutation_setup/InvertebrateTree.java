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

public class InvertebrateTree {
    public static final MutationTrees invertebrate = MutationTrees.addTree(new ArrayList<Mutation>(), "invertebrate", Identifier.ofVanilla("textures/mob_effect/weaving.png"));

    public static void initialize() {
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
    public static final Mutation beeStinger = invertebrate.addToTree(new BeeStingerMutation("beeStinger", "invertebrate", hivePheromones,MutatableParts.TAIL));
    public static final Mutation queenPheromones = invertebrate.addToTree(new Mutation("queenPheromones", "invertebrate", beeStinger));

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
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
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
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
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
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(arachneBody, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.LEG);
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut2 = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, new MutationBodyInfo(partMut2.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
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
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public void mutationAction(PlayerEntity player) {

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
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public void mutationAction(PlayerEntity player) {

        }
    }

    public static class BeeStingerMutation extends Mutation {
        private int cooldown = 0;

        public BeeStingerMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(beeStinger, 0,
                    ColorHelper.getArgb(115, 110, 99), ColorHelper.getArgb(136, 127, 107), 0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public void mutationAction(PlayerEntity player) {

        }
    }


}
