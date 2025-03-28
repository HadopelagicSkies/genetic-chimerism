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

public class AmphibiousTree {
    public static final MutationTrees amphibious = MutationTrees.addTree(new ArrayList<Mutation>(), "amphibious", Identifier.ofVanilla("textures/item/axolotl_bucket.png"));

    public static void initialize() {
    }

    public static final Mutation jump_1 = amphibious.addToTree(new Jump1Mutation("jump_1", "amphibious", null));
    public static final Mutation jump_2 = amphibious.addToTree(new Jump2Mutation("jump_2", "amphibious", jump_1));
    public static final Mutation jump_3 = amphibious.addToTree(new Jump3Mutation("jump_3", "amphibious", jump_2));
    public static final Mutation jump_4 = amphibious.addToTree(new Jump4Mutation("jump_4", "amphibious", jump_3));

    public static final Mutation amphi_gills = amphibious.addToTree(new AmphiGillsMutation("amphi_gills", "amphibious", jump_3));
    public static final Mutation axolotl_gills = amphibious.addToTree(new AxolotlGillsMutation("axolotl_gills", "amphibious", amphi_gills,MutatableParts.HEAD));

    public static final Mutation tadpole_tail = amphibious.addToTree(new TadpoleTailMutation("tadpole_tail", "amphibious", jump_2, MutatableParts.TAIL));
    public static final Mutation frog_tongue = amphibious.addToTree(new Mutation("frog_tongue", "amphibious", tadpole_tail));

    public static final Mutation growth_speed = amphibious.addToTree(new Mutation("growth_speed", "amphibious", jump_1));
    public static final Mutation regen_chance_1 = amphibious.addToTree(new Mutation("regen_chance_1", "amphibious", growth_speed));
    public static final Mutation regen_chance_2 = amphibious.addToTree(new Mutation("regen_chance_2", "amphibious", regen_chance_1));
    public static final Mutation burn_regen = amphibious.addToTree(new Mutation("burn_regen", "amphibious", regen_chance_1));


    public static class Jump1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "jump1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Jump1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
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

    public static class Jump2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "jump2_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Jump2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
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

    public static class Jump3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "jump3_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Jump3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
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

    public static class Jump4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "jump4_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Jump4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
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

    public static class AmphiGillsMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "amphi_gills_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public AmphiGillsMutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);}

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);}
    }

    public static class AxolotlGillsMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "axolotl_gills_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        public AxolotlGillsMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.HEAD);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD, MutationTrees.mutationToCodec(axolotl_gills,0,
                    ColorHelper.getArgb(0,0,0),ColorHelper.getArgb(0,0,0),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.HEAD);
            MutationAttachments.setPartAttached(player, MutatableParts.HEAD,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public int getMaxGrowth() {
            return 100;
        }
    }



    public static class TadpoleTailMutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "tadpole_tail_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public TadpoleTailMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.TAIL);
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL, MutationTrees.mutationToCodec(tadpole_tail,0,
                    ColorHelper.getArgb(0,0,0),ColorHelper.getArgb(0,0,0),0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
            MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, MutatableParts.TAIL);
            MutationAttachments.setPartAttached(player, MutatableParts.TAIL,new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
                    partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth(), true,false));
        }

        @Override
        public void mutationAction(PlayerEntity player) {
            if (player.getAttached(MutationAttachments.PLAYER_MUTATION_LIST).contains(MutationTrees.mutationToCodec(AmphibiousTree.frog_tongue))){

            }
        }

        @Override
        public int getMaxGrowth() {
            return 200;
        }
    }



}
