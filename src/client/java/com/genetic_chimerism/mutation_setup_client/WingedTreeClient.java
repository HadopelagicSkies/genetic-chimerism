package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.AnimationTransformHelper;
import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.mutation_setup.WingedTree;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.model.ModelTransformer;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class WingedTreeClient {
    public static final MutationTreesClient winged = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "winged");

    public static void initialize() {
    }

    public static final MutationClient backWings1 = winged.addToTree(new BackWings1Mutation("backWings1", "winged"));
    public static final MutationClient backWings2 = winged.addToTree(new BackWings2Mutation("backWings2", "winged"));
    public static final MutationClient harpyWings = winged.addToTree(new HarpyWingsMutation("harpyWings", "winged"));

    public static class HarpyWingsMutation extends MutationClient {

        public HarpyWingsMutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/harpy_wings1.png");
        }

        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/harpy_wings2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, -1.0F, -1.0F));

            ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(0, 9).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

            ModelPartData bone4 = bone.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData cube_r2 = bone4.addChild("cube_r2", ModelPartBuilder.create().uv(14, -7).cuboid(-0.01F, 0.0F, 0.5F, 0.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

            ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, 3.75F));

            ModelPartData cube_r3 = bone2.addChild("cube_r3", ModelPartBuilder.create().uv(14, 6).cuboid(1.9F, -1.25F, -4.5F, 0.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

            ModelPartData bone3 = bone.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, 3.75F));

            ModelPartData cube_r4 = bone3.addChild("cube_r4", ModelPartBuilder.create().uv(0, 21).cuboid(-1.0F, -0.75F, -6.75F, 2.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

            ModelPartData bone5 = bone3.addChild("bone5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData cube_r5 = bone5.addChild("cube_r5", ModelPartBuilder.create().uv(0, -7).cuboid(2.0F, 1.25F, -6.75F, 0.0F, 9.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
            return TexturedModelData.of(modelData, 32, 32);
        }

        @Override
        public Animation getGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation getPartAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 90.0F, 120.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(1.5F, -0.25F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(35.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(80.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation getActionAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 90.0F, 120.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.3333F, AnimationHelper.createRotationalVector(80.0F, 57.0F, 196.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 90.0F, 120.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(35.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.3333F, AnimationHelper.createRotationalVector(35.0F, 5.0F, -6.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.6667F, AnimationHelper.createRotationalVector(35.0F, 5.0F, -6.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(35.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(80.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.3333F, AnimationHelper.createRotationalVector(82.0F, 10.0F, -17.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.6667F, AnimationHelper.createRotationalVector(80.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(80.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .build();
        }

        @Override
        public Animation getMirrorAnimation() {
            return Animation.Builder.create(0.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(1F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .build();
        }
    }

    public static class BackWings1Mutation extends MutationClient {
        public static float scale = 0.5f;
        public BackWings1Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/back_wings1.png");
        }

        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/back_wings2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            return backWings2.getTexturedModelData();
        }

        @Override
        public Animation getPartAnimation() {
            return AnimationTransformHelper.scaleAnimation(backWings2.getPartAnimation(), scale);
        }

        @Override
        public Animation getGrowthAnimation() {
            Animation growth = Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone6", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone7", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone8", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone9", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone10", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone11", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone12", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone13", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone14", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
            return AnimationTransformHelper.scaleAnimation(growth, scale);
        }

        @Override
        public Animation getActionAnimation() {
            return AnimationTransformHelper.scaleAnimation(backWings2.getActionAnimation(), scale);
        }
    }

    public static class BackWings2Mutation extends MutationClient {
        public BackWings2Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/back_wings1.png");
        }

        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/back_wings2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(-1.25F, 2.0F, 0.25F));

            ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(24, 7).cuboid(-1.0F, -0.75F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6545F, -0.5672F, 0.0F));

            ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(-1.25F, 3.0F, 2.0F));

            ModelPartData cube_r2 = bone2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 14).cuboid(-1.0F, -9.5F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6545F, -0.5672F, 0.0F));

            ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData cube_r3 = bone3.addChild("cube_r3", ModelPartBuilder.create().uv(12, 13).cuboid(0.0F, -8.5F, -1.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6545F, -0.5672F, 0.0F));

            ModelPartData bone4 = bone2.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(-2.95F, -6.5F, 4.6F));

            ModelPartData cube_r4 = bone4.addChild("cube_r4", ModelPartBuilder.create().uv(20, 15).cuboid(-1.0F, -0.5F, -1.1F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5672F, -0.5672F, 0.0F));

            ModelPartData bone5 = bone4.addChild("bone5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -0.1F));

            ModelPartData cube_r5 = bone5.addChild("cube_r5", ModelPartBuilder.create().uv(14, 1).cuboid(0.0F, -0.5F, -6.0F, 0.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, -0.5672F, 0.0F));

            ModelPartData bone6 = bone4.addChild("bone6", ModelPartBuilder.create(), ModelTransform.pivot(-1.75F, 6.5F, 3.15F));

            ModelPartData cube_r6 = bone6.addChild("cube_r6", ModelPartBuilder.create().uv(8, 14).cuboid(-0.75F, -0.5F, -0.5F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1745F, -0.5672F, 0.0F));

            ModelPartData bone7 = bone6.addChild("bone7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData cube_r7 = bone7.addChild("cube_r7", ModelPartBuilder.create().uv(0, -1).cuboid(-0.25F, -0.5F, -7.5F, 0.0F, 8.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1745F, -0.5672F, 0.0F));

            ModelPartData bone8 = modelPartData.addChild("bone8", ModelPartBuilder.create(), ModelTransform.pivot(1.25F, 2.0F, 0.25F));

            ModelPartData cube_r8 = bone8.addChild("cube_r8", ModelPartBuilder.create().uv(24, 7).mirrored().cuboid(-1.0F, -0.75F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6545F, 0.5672F, 0.0F));

            ModelPartData bone9 = bone8.addChild("bone9", ModelPartBuilder.create(), ModelTransform.pivot(1.25F, 3.0F, 2.0F));

            ModelPartData cube_r9 = bone9.addChild("cube_r9", ModelPartBuilder.create().uv(0, 14).mirrored().cuboid(-1.0F, -9.5F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6545F, 0.5672F, 0.0F));

            ModelPartData bone10 = bone9.addChild("bone10", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData cube_r10 = bone10.addChild("cube_r10", ModelPartBuilder.create().uv(12, 13).mirrored().cuboid(0.0F, -8.5F, -1.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6545F, 0.5672F, 0.0F));

            ModelPartData bone11 = bone9.addChild("bone11", ModelPartBuilder.create(), ModelTransform.pivot(2.95F, -6.5F, 4.6F));

            ModelPartData cube_r11 = bone11.addChild("cube_r11", ModelPartBuilder.create().uv(20, 15).mirrored().cuboid(-1.0F, -0.5F, -1.1F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5672F, 0.5672F, 0.0F));

            ModelPartData bone12 = bone11.addChild("bone12", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -0.1F));

            ModelPartData cube_r12 = bone12.addChild("cube_r12", ModelPartBuilder.create().uv(14, 1).mirrored().cuboid(0.0F, -0.5F, -6.0F, 0.0F, 8.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.5672F, 0.0F));

            ModelPartData bone13 = bone11.addChild("bone13", ModelPartBuilder.create(), ModelTransform.pivot(1.75F, 6.5F, 3.15F));

            ModelPartData cube_r13 = bone13.addChild("cube_r13", ModelPartBuilder.create().uv(8, 14).mirrored().cuboid(-0.25F, -0.5F, -0.5F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1745F, 0.5672F, 0.0F));

            ModelPartData bone14 = bone13.addChild("bone14", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData cube_r14 = bone14.addChild("cube_r14", ModelPartBuilder.create().uv(0, -1).mirrored().cuboid(0.25F, -0.5F, -7.5F, 0.0F, 8.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1745F, 0.5672F, 0.0F));
            return TexturedModelData.of(modelData, 32, 32);
        }

        @Override
        public Animation getPartAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -5.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-3.0F, -30.0F, -3.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.25F, 0.0F, 0.75F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.4F, -0.35F, 0.35F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(42.0F, -24.0F, 33.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.25F, 0.75F, 0.3F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-11.0F, 1.0F, -7.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone6", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(33.0F, -5.0F, 20.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone6", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.3F, -0.2F, 0.7F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone7", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone7", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone8", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone8", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone9", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-3.0F, 30.0F, 3.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone9", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.25F, 0.0F, 0.75F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone10", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone10", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.4F, -0.35F, 0.35F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone11", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(42.0F, 24.0F, -33.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone11", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.25F, 0.75F, 0.3F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone12", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-11.0F, -1.0F, 7.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone12", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone13", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(33.0F, 5.0F, -20.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone13", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.3F, -0.2F, 0.7F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone14", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone14", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation getGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone6", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone7", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone8", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone9", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone10", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone11", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone12", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone13", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone14", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625F, AnimationHelper.createScalingVector(BackWings1Mutation.scale, BackWings1Mutation.scale, BackWings1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation getActionAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -5.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-3.0F, -30.0F, -3.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-4.0F, -40.0F, -2.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-3.0F, -30.0F, -3.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.25F, 0.0F, 0.75F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.25F, 0.0F, 0.75F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.4F, -0.35F, 0.35F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.4F, -0.35F, 0.35F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(42.0F, -24.0F, 33.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(36.0F, -63.0F, 29.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(42.0F, -24.0F, 33.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.25F, 0.75F, 0.3F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.25F, 0.75F, 0.3F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-11.0F, 1.0F, -7.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-11.0F, 1.0F, -7.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone6", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(33.0F, -5.0F, 20.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(19.0F, -10.0F, 43.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(33.0F, -5.0F, 20.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone6", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.3F, -0.2F, 0.7F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.3F, -0.2F, 0.7F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone8", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone9", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-3.0F, 30.0F, 3.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-4.0F, 40.0F, 2.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-3.0F, 30.0F, 3.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone9", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.25F, 0.0F, 0.75F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.25F, 0.0F, 0.75F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone10", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.4F, -0.35F, 0.35F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.4F, -0.35F, 0.35F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone11", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(42.0F, 24.0F, -33.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(36.0F, 63.0F, -29.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(42.0F, 24.0F, -33.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone11", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.25F, 0.75F, 0.3F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.25F, 0.75F, 0.3F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone12", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-11.0F, -1.0F, 7.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-11.0F, -1.0F, 7.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone13", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(33.0F, 5.0F, -20.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(19.0F, 10.0F, -43.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(33.0F, 5.0F, -20.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone13", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.3F, -0.2F, 0.7F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.3F, -0.2F, 0.7F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }
    }
}
