package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.*;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class AquaticTreeClient {
    public static final MutationTreesClient aquatic = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "aquatic");

    public static void initialize() {
    }

    public static final MutationClient gills1 = aquatic.addToTree(new Gills1Mutation("gills1", "aquatic"));
    public static final MutationClient gills2 = aquatic.addToTree(new Gills2Mutation("gills2", "aquatic"));
    public static final MutationClient gills3 = aquatic.addToTree(new Gills3Mutation("gills3", "aquatic"));
    public static final MutationClient gills4 = aquatic.addToTree(new Gills4Mutation("gills4", "aquatic"));

    public static final MutationClient fastswim1 = aquatic.addToTree(new FastSwim1Mutation("fastswim1", "aquatic"));
    public static final MutationClient sharktail1 = aquatic.addToTree(new SharkTail1Mutation("sharktail1", "aquatic"));
    public static final MutationClient sharktail2 = aquatic.addToTree(new SharkTail2Mutation("sharktail2", "aquatic"));

    public static class Gills1Mutation extends MutationClient {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier GILLS1_MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "gills1_modifier"), 1, EntityAttributeModifier.Operation.ADD_VALUE);

        public Gills1Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }
    }

    public static class Gills2Mutation extends MutationClient {
        public Gills2Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }
    }

    public static class Gills3Mutation extends MutationClient {
        public Gills3Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }
    }

    public static class Gills4Mutation extends MutationClient {
        public Gills4Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }
    }

    public static class FastSwim1Mutation extends MutationClient {
        public FastSwim1Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }
    }

    public static class SharkTail1Mutation extends MutationClient {
        public SharkTail1Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/shark_tail1.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bb_main = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

            ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(22, 22).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 2.0F, 0.1745F, 0.0F, 0.0F));

            ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(22, 13).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F))
                    .uv(0, 24).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, 14.5F, 0.2618F, 0.0F, 0.0F));

            ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 13).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 9.0F, -0.5672F, 0.0F, 0.0F));

            ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 2.0F, -0.3491F, 0.0F, 0.0F));
            return TexturedModelData.of(modelData, 64, 64);
        }
    }

    public static class SharkTail2Mutation extends MutationClient {
        public SharkTail2Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/shark_tail2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bb_main = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

            ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(29, 7).cuboid(-0.5F, 0.0F, 4.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                    .uv(22, 12).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 3.0F, 7.0F, new Dilation(0.0F))
                    .uv(0, 24).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, 14.5F, 0.2618F, 0.0F, 0.0F));

            ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(26, 4).cuboid(-0.5F, -2.725F, 4.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, 14.5F, -0.1309F, 0.0F, 0.0F));

            ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(22, 22).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 2.0F, 0.2618F, 0.0F, 0.0F));

            ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 13).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 9.0F, -0.5672F, 0.0F, 0.0F));

            ModelPartData cube_r5 = bb_main.addChild("cube_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 2.0F, -0.3491F, 0.0F, 0.0F));
            return TexturedModelData.of(modelData, 64, 64);
        }
    }
}

