package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.mutation_setup.HornedTree;
import com.genetic_chimerism.mutation_setup.Mutation;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class HornedTreeClient {
    public static final MutationTreesClient horned = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "horned");

    public static void initialize() {
    }

    public static final MutationClient hurtHorns1 = horned.addToTree(new MutationClient("hurtHorns1", "horned"));
    public static final MutationClient hurtHorns2 = horned.addToTree(new MutationClient("hurtHorns2", "horned"));
    public static final MutationClient ramHorns1 = horned.addToTree(new RamHorns1Mutation("ramHorns1", "horned"));
    public static final MutationClient ramHorns2 = horned.addToTree(new RamHorns1Mutation("ramHorns2", "horned"));
    public static final MutationClient ramLegs = horned.addToTree(new RamLegsMutation("ramLegs", "horned"));


    public static class RamHorns1Mutation extends MutationClient {
        public RamHorns1Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/ram_horn1.png");
        }

        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/ram_horn2.png");
        }
        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create().uv(8, 12).cuboid(0.75F, -34.9F, -3.6F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.25F, 23.9F, -1.4F));

            ModelPartData cube_r1 = bone2.addChild("cube_r1", ModelPartBuilder.create().uv(0, 7).cuboid(4.17F, -33.0F, -1.05F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0436F, -0.2182F, 0.0F));

            ModelPartData cube_r2 = bone2.addChild("cube_r2", ModelPartBuilder.create().uv(14, 0).cuboid(3.25F, -1.095F, -0.75F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, -33.8F, 1.1F, 0.0F, -0.2182F, 0.0F));

            ModelPartData cube_r3 = bone2.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.6F, -32.9F, -2.35F, 0.0F, 0.3927F, 0.0F));

            ModelPartData cube_r4 = bone2.addChild("cube_r4", ModelPartBuilder.create().uv(8, 7).cuboid(-0.55F, -2.1F, -2.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(4.75F, -26.9F, 0.4F, 0.0F, -0.2182F, 0.0F));

            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(8, 12).mirrored().cuboid(2.0F, -8.0F, -4.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-5.0F, -3.0F, -1.0F));

            ModelPartData cube_r5 = bone.addChild("cube_r5", ModelPartBuilder.create().uv(8, 7).mirrored().cuboid(-1.45F, -2.1F, -2.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.2182F, 0.0F));

            ModelPartData cube_r6 = bone.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.15F, -6.0F, -2.75F, 0.0F, -0.3927F, 0.0F));

            ModelPartData cube_r7 = bone.addChild("cube_r7", ModelPartBuilder.create().uv(14, 0).mirrored().cuboid(-6.25F, -1.095F, -0.75F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.0F, -6.9F, 0.7F, 0.0F, 0.2182F, 0.0F));

            ModelPartData cube_r8 = bone.addChild("cube_r8", ModelPartBuilder.create().uv(0, 7).mirrored().cuboid(-6.17F, -33.0F, -1.05F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.75F, 26.9F, -0.4F, -0.0436F, 0.2182F, 0.0F));

            ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create().uv(8, 12).cuboid(-4.0F, -8.0F, -4.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -3.0F, -1.0F));

            ModelPartData cube_r9 = bone3.addChild("cube_r9", ModelPartBuilder.create().uv(8, 7).cuboid(-0.55F, -2.1F, -2.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

            ModelPartData cube_r10 = bone3.addChild("cube_r10", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.15F, -6.0F, -2.75F, 0.0F, 0.3927F, 0.0F));

            ModelPartData cube_r11 = bone3.addChild("cube_r11", ModelPartBuilder.create().uv(14, 0).cuboid(3.25F, -1.095F, -0.75F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -6.9F, 0.7F, 0.0F, -0.2182F, 0.0F));

            ModelPartData cube_r12 = bone3.addChild("cube_r12", ModelPartBuilder.create().uv(0, 7).cuboid(4.17F, -33.0F, -1.05F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.75F, 26.9F, -0.4F, -0.0436F, -0.2182F, 0.0F));
            return TexturedModelData.of(modelData, 32, 32);
        }
    }

    public static class RamLegsMutation extends MutationClient {
        public RamLegsMutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/hooves1.png");
        }

        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/hooves2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

            ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -0.5F, -2.05F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

            ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.5F, -0.75F));

            ModelPartData cube_r2 = bone2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 16).cuboid(-1.5F, -0.9F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

            ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create().uv(0, 10).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 6.5F, 0.75F));
            return TexturedModelData.of(modelData, 32, 32);
        }

        @Override
        public Animation createGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation createMirrorAnimation() {
            return Animation.Builder.create(0.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(4.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }
    }

}

