package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.entity.DroppedTailEntity;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.kinds.IdF;
import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.data.tag.vanilla.VanillaBiomeTagProvider;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.ArrayList;
import java.util.Set;

import static com.genetic_chimerism.GeneticChimerismEntities.DROPPED_TAIL;

public class ScaledTree {
    public static final MutationTrees scaled = MutationTrees.addTree(new ArrayList<Mutation>(), "scaled", Identifier.ofVanilla("textures/item/turtle_scute.png"));

    public static void initialize() {
    }

    public static final Mutation poisonRes1 = scaled.addToTree(new Mutation("poisonRes1", "scaled", null));
    public static final Mutation coldBlooded = scaled.addToTree(new ColdBloodedMutation("coldBlooded", "scaled", poisonRes1));
    public static final Mutation poisonRes2 = scaled.addToTree(new Mutation("poisonRes2", "scaled", coldBlooded));
    public static final Mutation poisonRes3 = scaled.addToTree(new Mutation("poisonRes3", "scaled", poisonRes2));

    public static final Mutation snakeTail = scaled.addToTree(new SnakeTailMutation("snakeTail", "scaled", poisonRes2,MutatableParts.TAIL));
    public static final Mutation lamiaTail = scaled.addToTree(new LamiaTailMutation("lamiaTail", "scaled", snakeTail,MutatableParts.TAIL,MutatableParts.LEG));

    public static final Mutation burnTime1 = scaled.addToTree(new BurnTime1Mutation("burnTime1", "scaled", coldBlooded));
    public static final Mutation burnTime2 = scaled.addToTree(new BurnTime2Mutation("burnTime2", "scaled", burnTime1));
    public static final Mutation burnTime3 = scaled.addToTree(new BurnTime3Mutation("burnTime3", "scaled", burnTime2));

    public static final Mutation lizardTail1 = scaled.addToTree(new LizardTail1Mutation("lizardTail1", "scaled", burnTime1,MutatableParts.TAIL));
    public static final Mutation lizardTail2 = scaled.addToTree(new LizardTail2Mutation("lizardTail2", "scaled", lizardTail1,MutatableParts.TAIL));

    public static class ColdBloodedMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> warmModifierMultimap = HashMultimap.create();
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> coldModifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier WARM_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "cold_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);
        public static final EntityAttributeModifier COLD_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "warm_modifier"), -1, EntityAttributeModifier.Operation.ADD_VALUE);
        public ColdBloodedMutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            warmModifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, WARM_MODIFIER);
            coldModifierMultimap.put(EntityAttributes.MOVEMENT_SPEED, COLD_MODIFIER);
        }

        @Override
        public void tick(PlayerEntity player) {
            RegistryEntry<Biome> biome = player.getWorld().getBiome(player.getBlockPos());

            if (biome.isIn(BiomeTags.SPAWNS_COLD_VARIANT_FROGS) && !player.getAttributes().hasModifierForAttribute(EntityAttributes.MOVEMENT_SPEED,Identifier.of(GeneticChimerism.MOD_ID,"cold_modifier"))){
                player.getAttributes().addTemporaryModifiers(coldModifierMultimap);
            }
            else if (biome.isIn(BiomeTags.SPAWNS_WARM_VARIANT_FROGS) && !player.getAttributes().hasModifierForAttribute(EntityAttributes.MOVEMENT_SPEED,Identifier.of(GeneticChimerism.MOD_ID,"warm_modifier"))) {
                player.getAttributes().addTemporaryModifiers(warmModifierMultimap);
            }
            else {
                player.getAttributes().removeModifiers(coldModifierMultimap);
                player.getAttributes().removeModifiers(warmModifierMultimap);
            }
        }
    }


    public static class BurnTime1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "burntime1_modifier"), -0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public BurnTime1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.BURNING_TIME, MODIFIER);
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

    public static class BurnTime2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "burntime1_modifier"), -0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public BurnTime2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.BURNING_TIME, MODIFIER);
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

    public static class BurnTime3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "burntime1_modifier"), -0.5, EntityAttributeModifier.Operation.ADD_VALUE);

        public BurnTime3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.BURNING_TIME, MODIFIER);
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

    public static class SnakeTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        //public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "snaketail_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);

        public SnakeTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        //    modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(snakeTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class LamiaTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        //public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "lamiatail_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);

        public LamiaTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts part1, MutatableParts part2) {
            super(mutID, treeID, prereq, part1,part2);
        //    modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            MutationAttachments.removePartAttached(player, MutatableParts.LEG);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(lamiaTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
            MutationAttachments.setPartAttached(player, MutatableParts.LEG, MutationTrees.mutationToCodec(lamiaTail,0,
                    ColorHelper.getArgb(99,141,153),ColorHelper.getArgb(125,164,137),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
            MutationAttachments.setPartReceding(player, MutatableParts.LEG,true);
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class LizardTail1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        //public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "lizardtail1_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);

        public LizardTail1Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        //    modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(lizardTail1,0,
                    ColorHelper.getArgb(229,222,191),ColorHelper.getArgb(103,96,65),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public void tick(PlayerEntity player) {
            if(MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(lizardTail2)) && MutationTrees.mutationFromCodec(MutationAttachments.getPartAttached(player,MutatableParts.TAIL)) == lizardTail1 && MutationAttachments.getPartAttached(player,MutatableParts.TAIL).growth() >= getMaxGrowth()){
                MutationBodyInfo oldMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
                MutationAttachments.setPartAttached(player, MutatableParts.TAIL, new MutationBodyInfo("lizardTail2","scaled",oldMut.patternIndex(),oldMut.color1(),oldMut.color2(),0,false,false));
            }
        }

        @Override
        public int getMaxGrowth() {
            return 2000;
        }
    }

    public static class LizardTail2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        private int cooldown;
        // public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "lizardtail2_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);

        public LizardTail2Mutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        //    modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationBodyInfo oldMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            int newPatternIndex = oldMut !=null ? oldMut.patternIndex() : 0;
            int newColor1 = oldMut !=null ? oldMut.color1() : ColorHelper.getArgb(229,222,191);
            int newColor2 = oldMut !=null ? oldMut.color2() : ColorHelper.getArgb(103,96,65);
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(lizardTail2,newPatternIndex,
                    newColor1, newColor2  ,0, false, false));
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            DroppedTailEntity droppableTail = new DroppedTailEntity(DROPPED_TAIL, player.getWorld());
            if(!player.getWorld().isClient && MutationAttachments.getPartAttached(player,MutatableParts.TAIL).growth() >= getMaxGrowth() && MutationTrees.mutationFromCodec(MutationAttachments.getPartAttached(player,MutatableParts.TAIL)) == lizardTail2) {
                cooldown = 3600;
                droppableTail.setPos(player.getX(),player.getY(),player.getZ());
                droppableTail.setVisuals(MutationAttachments.getPartAttached(player,MutatableParts.TAIL).patternIndex(),
                        MutationAttachments.getPartAttached(player,MutatableParts.TAIL).color1(),
                        MutationAttachments.getPartAttached(player,MutatableParts.TAIL).color2());
                player.getWorld().spawnEntity(droppableTail);
                MutationBodyInfo oldMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
                MutationAttachments.setPartAttached(player, MutatableParts.TAIL, new MutationBodyInfo("lizardTail1","scaled",oldMut.patternIndex(),oldMut.color1(),oldMut.color2(),0,false,false));
            }
        }

        @Override
        public void tick(PlayerEntity player) {
            if (this.cooldown > 0) this.cooldown--;
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationAttachments.setPartReceding(player, MutatableParts.TAIL,true);
        }

        @Override
        public int getMaxGrowth() {
            return 400;
        }
    }


}
