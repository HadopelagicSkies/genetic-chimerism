package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.AnimationTransformHelper;
import com.genetic_chimerism.GeneticChimerism;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.model.ModelTransformer;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ScaledTreeClient {

    public static final MutationTreesClient scaled = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "scaled");

    public static void initialize() {
    }

    public static final MutationClient lizardTail2 = scaled.addToTree(new LizardTail2Mutation("lizardTail2", "scaled"));
    public static final MutationClient lizardTail1 = scaled.addToTree(new LizardTail1Mutation("lizardTail1", "scaled"));

    public static class LizardTail1Mutation extends MutationClient{
        LizardTail1Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }
        public static float scale = 0.65F;
        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/lizard_tail1.png");
        }
        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/lizard_tail2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            return lizardTail2.getTexturedModelData().transform(ModelTransformer.scaling(scale));
        }

        @Override
        public Animation createGrowthAnimation() {
            return  Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(scale, scale, scale), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.5F, 0.5F, 0.5F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.75F, 0.75F, 0.75F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation createPartAnimation() {
            return AnimationTransformHelper.scaleAnimation(lizardTail2.getAnimation("part"),scale);
        }
    }

    public static class LizardTail2Mutation extends MutationClient{
        LizardTail2Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/lizard_tail1.png");
        }
        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/lizard_tail2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.of(0.0F, 10.0F, 1.7F, -0.1222F, 0.0F, 0.0F));

            ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(0, 8).cuboid(-1.5F, -1.35F, -0.4F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.3F, -0.3491F, 0.0F, 0.0F));

            ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.5F, 3.55F));

            ModelPartData cube_r2 = bone2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.45F, -0.2F, 4.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, -1.0F, -0.5672F, 0.0F, 0.0F));

            ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.5F, 3.75F));

            ModelPartData cube_r3 = bone3.addChild("cube_r3", ModelPartBuilder.create().uv(12, 8).cuboid(-1.5F, -1.7F, -0.2F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.25F, -1.0F, -0.7418F, 0.0F, 0.0F));
            return TexturedModelData.of(modelData, 32, 32);
        }

        @Override
        public Animation createGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(LizardTail1Mutation.scale, LizardTail1Mutation.scale, LizardTail1Mutation.scale), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.5F, 0.5F, 0.5F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.75F, 0.75F, 0.75F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();

        }

        @Override
        public Animation createPartAnimation() {
            return Animation.Builder.create(1.0F).looping()
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 12.5F, 0.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, -12.5F, 0.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 12.5F, 0.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-1.2F, 5.5F, -2.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-1.2F, -5.5F, 2.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-1.2F, 5.5F, -2.5F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.0F, AnimationHelper.createRotationalVector(-0.2F, 5.0F, -2.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-0.2F, -5.0F, 2.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-0.2F, 5.0F, -2.5F), Transformation.Interpolations.CUBIC)
                    ))
                    .build();
        }
    }
}
