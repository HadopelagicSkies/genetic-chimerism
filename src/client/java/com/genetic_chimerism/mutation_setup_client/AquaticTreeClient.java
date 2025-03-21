package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class AquaticTreeClient {
    public static final MutationTreesClient aquatic = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "aquatic");

    public static void initialize() {
    }


    public static final MutationClient sharkTail = aquatic.addToTree(new SharkTailMutation("sharktail", "aquatic"));
    public static final MutationClient thresherTail = aquatic.addToTree(new ThresherTailMutation("threshertail", "aquatic"));


    public static class SharkTailMutation extends MutationClient {
        public SharkTailMutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/shark_tail1.png");
        }
        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/shark_tail2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.0F, 2.0F));

            ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(22, 22).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

            ModelPartData cube_r2 = bone.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

            ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 13.0F, 9.0F));

            ModelPartData cube_r3 = bone2.addChild("cube_r3", ModelPartBuilder.create().uv(0, 13).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

            ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, 14.5F));

            ModelPartData cube_r4 = bone3.addChild("cube_r4", ModelPartBuilder.create().uv(22, 13).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F))
                    .uv(0, 24).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));
            return TexturedModelData.of(modelData, 64, 64);
        }

        @Override
        public Animation getPartAnimation() {
            return Animation.Builder.create(1.0F).looping()
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 10.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, -5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, -10.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -7.5F, 10.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 7.5F, -10.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, -7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -7.5F, 10.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.55F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createTranslationalVector(1.0F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createTranslationalVector(-0.55F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createTranslationalVector(-1.0F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.55F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -15.0F, 10.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 15.0F, -10.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, -7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -15.0F, 10.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.5F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createTranslationalVector(1.75F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.5F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createTranslationalVector(-1.75F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.5F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation getGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 2.0F, -7.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 5.0F, -12.5F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }
    }

    public static class ThresherTailMutation extends MutationClient {
        public ThresherTailMutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/thresher_tail1.png");
        }

        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/thresher_tail2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.0F, 2.0F));

            ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(22, 22).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

            ModelPartData cube_r2 = bone.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

            ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 13.0F, 9.0F));

            ModelPartData cube_r3 = bone2.addChild("cube_r3", ModelPartBuilder.create().uv(0, 13).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

            ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, 14.5F));

            ModelPartData cube_r4 = bone3.addChild("cube_r4", ModelPartBuilder.create().uv(29, 7).cuboid(-0.5F, 0.0F, 4.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                    .uv(22, 12).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 3.0F, 7.0F, new Dilation(0.0F))
                    .uv(0, 24).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

            ModelPartData cube_r5 = bone3.addChild("cube_r5", ModelPartBuilder.create().uv(26, 4).cuboid(-0.5F, -2.725F, 4.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));
            return TexturedModelData.of(modelData, 64, 64);
        }

        @Override
        public Animation getPartAnimation() {
            return Animation.Builder.create(1.0F).looping()
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 10.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, -5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, -10.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -7.5F, 10.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 7.5F, -10.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, -7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -7.5F, 10.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.55F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createTranslationalVector(1.0F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createTranslationalVector(-0.55F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createTranslationalVector(-1.0F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.55F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -15.0F, 10.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createRotationalVector(0.0F, 7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 15.0F, -10.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, -7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -15.0F, 10.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.5F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25F, AnimationHelper.createTranslationalVector(1.75F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.5F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75F, AnimationHelper.createTranslationalVector(-1.75F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.5F, -0.25F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation getGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 2.0F, -7.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 5.0F, -12.5F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

    }
}
