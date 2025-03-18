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
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(135,169,179),0, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true));
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }


    public static class ThresherTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "threshertail_modifier"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);

        public ThresherTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(thresherTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(135,169,179),0, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true));
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            super.mutationAction(player);
        }

        @Override
        public int getMaxGrowth() {
            return 500;
        }
    }

    public static class FishTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fishtail_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);

        public FishTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(fishTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true));
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class MermaidTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "sharktail1_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);

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
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false));
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, MutationTrees.mutationToCodec(mermaidTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut1 = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL,new MutationBodyInfo(partMut1.mutID(), partMut1.treeID(),
                    partMut1.patternIndex(), partMut1.color1(), partMut1.color2(), partMut1.growth(), true));
            MutationBodyInfo partMut2 = MutationAttachments.getPartAttached(player, MutatableParts.LEG);
            MutationAttachments.setPartAttached(player, MutatableParts.LEG,new MutationBodyInfo(partMut2.mutID(), partMut2.treeID(),
                    partMut2.patternIndex(), partMut2.color1(), partMut2.color2(), partMut2.growth(), true));
        }

        @Override
        public int getMaxGrowth() {
            return 500;
        }
    }


}
