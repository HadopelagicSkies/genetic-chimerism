package com.genetic_chimerism.mutationsetup;

import com.genetic_chimerism.GeneticChimerism;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.*;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AquaticTree {
    public static final MutationTrees aquatic = MutationTrees.addTree(new ArrayList<Mutation>(), "aquatic", Identifier.ofVanilla("textures/mob_effect/dolphins_grace.png"));

    public static void initialize() {
    }

    public static final Mutation gills1 = aquatic.addToTree(new Gills1Mutation("gills1", "aquatic", null));
    public static final Mutation gills2 = aquatic.addToTree(new Gills2Mutation("gills2", "aquatic", gills1));
    public static final Mutation gills3 = aquatic.addToTree(new Gills3Mutation("gills3", "aquatic", gills2));
    public static final Mutation gills4 = aquatic.addToTree(new Gills4Mutation("gills4", "aquatic", gills3));

    public static final Mutation fastswim1 = aquatic.addToTree(new FastSwim1Mutation("fastswim1", "aquatic", gills1));
    public static final Mutation sharktail1 = aquatic.addToTree(new SharkTail1Mutation("sharktail1", "aquatic", fastswim1));

    public static class Gills1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier GILLS1_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "gills1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, GILLS1_MODIFIER);
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
        public static final EntityAttributeModifier GILLS2_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "gills2_modifier"), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, GILLS2_MODIFIER);
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
        public static final EntityAttributeModifier GILLS3_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "gills3_modifier"), -1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, GILLS3_MODIFIER);
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
        public static final EntityAttributeModifier GILLS4_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "gills4_modifier"), -2, EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.OXYGEN_BONUS, GILLS4_MODIFIER);
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
        public static final EntityAttributeModifier FASTSWIM1_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fastswim1_modifier"), 0.2, EntityAttributeModifier.Operation.ADD_VALUE);

        public FastSwim1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, FASTSWIM1_MODIFIER);
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

    public static class SharkTail1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier SHARKTAIL1_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "fastswim1_modifier"), 0.4, EntityAttributeModifier.Operation.ADD_VALUE);
        public static final Identifier TEXTURE = Identifier.of(GeneticChimerism.MOD_ID,"textures/body_part/shark_tail1.png");

        public static TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

            ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(22, 22).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 2.0F, 0.2618F, 0.0F, 0.0F));

            ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(22, 13).cuboid(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F))
                    .uv(0, 24).cuboid(-1.0F, -1.5F, -1.5F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, 14.5F, 0.3491F, 0.0F, 0.0F));

            ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 13).cuboid(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 9.0F, -0.48F, 0.0F, 0.0F));

            ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 2.0F, -0.2618F, 0.0F, 0.0F));
            return TexturedModelData.of(modelData, 64, 64);
        }

        public SharkTail1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, SHARKTAIL1_MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
            player.setAttached(MutationAttachments.TAIL_MUTATION, MutationTrees.mutationToCodec(sharktail1));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }


}
