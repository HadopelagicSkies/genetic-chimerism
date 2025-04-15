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

public class SmallMammalTree {
    public static final MutationTrees small_mammal = MutationTrees.addTree(new ArrayList<Mutation>(), "small_mammal", Identifier.ofVanilla("textures/item/name_tag.png"));

    public static void initialize() {
    }

    public static final Mutation miningEff1 = small_mammal.addToTree(new MiningEff1Mutation("miningEff1", "small_mammal", null));
    public static final Mutation miningEff2 = small_mammal.addToTree(new MiningEff2Mutation("miningEff2", "small_mammal", miningEff1));
    public static final Mutation miningEff3 = small_mammal.addToTree(new MiningEff3Mutation("miningEff3", "small_mammal", miningEff2));
    public static final Mutation miningEff4 = small_mammal.addToTree(new MiningEff4Mutation("miningEff4", "small_mammal", miningEff3));

    public static final Mutation breakSpeed1 = small_mammal.addToTree(new BreakSpeed1Mutation("breakSpeed1", "small_mammal", miningEff3));
    public static final Mutation breakSpeed2 = small_mammal.addToTree(new BreakSpeed2Mutation("breakSpeed2", "small_mammal", breakSpeed1));

    public static final Mutation followRange1 = small_mammal.addToTree(new FollowRange1Mutation("followRange1", "small_mammal", null));
    public static final Mutation followRange2 = small_mammal.addToTree(new FollowRange2Mutation("followRange2", "small_mammal", followRange1));
    public static final Mutation followRange3 = small_mammal.addToTree(new FollowRange3Mutation("followRange3", "small_mammal", followRange2));
    public static final Mutation followRange4 = small_mammal.addToTree(new FollowRange4Mutation("followRange4", "small_mammal", followRange3));

    public static final Mutation fallMultDown1 = small_mammal.addToTree(new FallMultDown1Mutation("fallMultDown1", "small_mammal", followRange2));
    public static final Mutation fallMultDown2 = small_mammal.addToTree(new FallMultDown2Mutation("fallMultDown2", "small_mammal", fallMultDown1));

    public static final Mutation dogEars = small_mammal.addToTree(new DogEarsMutation("dogEars", "small_mammal", null, MutatableParts.HEAD));
    public static final Mutation catEars = small_mammal.addToTree(new CatEarsMutation("catEars", "small_mammal", null, MutatableParts.HEAD));
    public static final Mutation bunnyEars = small_mammal.addToTree(new BunnyEarsMutation("bunnyEars", "small_mammal", null, MutatableParts.HEAD));
    public static final Mutation foxEars = small_mammal.addToTree(new FoxEarsMutation("foxEars", "small_mammal", null, MutatableParts.HEAD));

    public static final Mutation dogTail = small_mammal.addToTree(new DogTailMutation("dogTail", "small_mammal", dogEars, MutatableParts.TAIL));
    public static final Mutation catTail = small_mammal.addToTree(new CatTailMutation("catTail", "small_mammal", catEars, MutatableParts.TAIL));
    public static final Mutation bunnyTail = small_mammal.addToTree(new BunnyTailMutation("bunnyTail", "small_mammal", bunnyEars, MutatableParts.TAIL));
    public static final Mutation foxTail = small_mammal.addToTree(new FoxTailMutation("foxTail", "small_mammal", foxEars, MutatableParts.TAIL));







    public static class FollowRange1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "followrange1_modifier"), 4, EntityAttributeModifier.Operation.ADD_VALUE);

        public FollowRange1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FOLLOW_RANGE, MODIFIER);
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

    public static class FollowRange2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "followrange2_modifier"), 4, EntityAttributeModifier.Operation.ADD_VALUE);

        public FollowRange2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FOLLOW_RANGE, MODIFIER);
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

    public static class FollowRange3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "followrange3_modifier"), 4, EntityAttributeModifier.Operation.ADD_VALUE);

        public FollowRange3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FOLLOW_RANGE, MODIFIER);
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

    public static class FollowRange4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "followrange4_modifier"), 4, EntityAttributeModifier.Operation.ADD_VALUE);

        public FollowRange4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FOLLOW_RANGE, MODIFIER);
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

    public static class FallMultDown1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fallmultdown1_modifier"), -0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public FallMultDown1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FALL_DAMAGE_MULTIPLIER, MODIFIER);
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

    public static class FallMultDown2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fallmultdown2_modifier"), -0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public FallMultDown2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.FALL_DAMAGE_MULTIPLIER, MODIFIER);
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

    public static class MiningEff1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "miningeff1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public MiningEff1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MINING_EFFICIENCY, MODIFIER);
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

    public static class MiningEff2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "miningeff2_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public MiningEff2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MINING_EFFICIENCY, MODIFIER);
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

    public static class MiningEff3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "miningeff3_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public MiningEff3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MINING_EFFICIENCY, MODIFIER);
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

    public static class MiningEff4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "miningeff4_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public MiningEff4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MINING_EFFICIENCY, MODIFIER);
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

    public static class BreakSpeed1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "breakspeed1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public BreakSpeed1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MINING_EFFICIENCY, MODIFIER);
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

    public static class BreakSpeed2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "breakspeed2_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public BreakSpeed2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.MINING_EFFICIENCY, MODIFIER);
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

    public static class DogEarsMutation extends Mutation {

        public DogEarsMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(dogEars,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public int getMaxGrowth() {
            return 100;
        }
    }
    public static class CatEarsMutation extends Mutation {

        public CatEarsMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(catEars,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public int getMaxGrowth() {
            return 500;
        }
    }
    public static class BunnyEarsMutation extends Mutation {

        public BunnyEarsMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(bunnyEars,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public int getMaxGrowth() {
            return 500;
        }
    }
    public static class FoxEarsMutation extends Mutation {

        public FoxEarsMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(foxEars,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public int getMaxGrowth() {
            return 500;
        }
    }

    public static class DogTailMutation extends Mutation {

        public DogTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(dogTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }
    public static class CatTailMutation extends Mutation {

        public CatTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(catTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }
    public static class BunnyTailMutation extends Mutation {

        public BunnyTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(bunnyTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }
    public static class FoxTailMutation extends Mutation {

        public FoxTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(foxTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }
}
