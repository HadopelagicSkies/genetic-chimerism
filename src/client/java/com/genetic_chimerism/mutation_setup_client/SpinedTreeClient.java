package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class SpinedTreeClient {
    public static final MutationTreesClient spined = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "spined");

    public static void initialize() {
    }

    public static final MutationClient smallSpines = spined.addToTree(new MutationClient("smallSpines", "spined"));
    public static final MutationClient shootQuills = spined.addToTree(new MutationClient("shootQuills", "spined"));
    public static final MutationClient guardianSpikes1 = spined.addToTree(new GuardianSpikes1Mutation("guardianSpikes1", "spined"));
    public static final MutationClient guardianSpikes2 = spined.addToTree(new MutationClient("guardianSpikes2", "spined"));

    public static class GuardianSpikes1Mutation extends MutationClient {
        public GuardianSpikes1Mutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/guardian_spikes1_1.png");
        }

        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/guardian_spikes1_2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.of(2.0F, 3.0F, 1.5F, -0.1745F, 0.0F, 0.0F));
            ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, 0.2618F, 0.0F));
            ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
            ModelPartData cube_r2 = bone2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 5).cuboid(0.0F, -1.5F, 2.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, 0.2618F, 0.0F));
            ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create(), ModelTransform.of(-2.0F, 3.0F, 1.5F, -0.1745F, 0.0F, 0.0F));
            ModelPartData cube_r3 = bone3.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, -0.2618F, 0.0F));
            ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
            ModelPartData cube_r4 = bone4.addChild("cube_r4", ModelPartBuilder.create().uv(0, 5).mirrored().cuboid(-1.0F, -1.5F, 2.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, -0.2618F, 0.0F));
            ModelPartData bone5 = modelPartData.addChild("bone5", ModelPartBuilder.create(), ModelTransform.of(3.0F, 6.5F, 1.5F, -0.1745F, 0.0F, 0.0F));
            ModelPartData cube_r5 = bone5.addChild("cube_r5", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, -0.2618F, 0.0F));
            ModelPartData bone6 = bone5.addChild("bone6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
            ModelPartData cube_r6 = bone6.addChild("cube_r6", ModelPartBuilder.create().uv(0, 5).mirrored().cuboid(-1.0F, -1.5F, 2.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, -0.2618F, 0.0F));
            ModelPartData bone7 = modelPartData.addChild("bone7", ModelPartBuilder.create(), ModelTransform.of(-3.0F, 6.5F, 1.5F, -0.1745F, 0.0F, 0.0F));
            ModelPartData cube_r7 = bone7.addChild("cube_r7", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, 0.2618F, 0.0F));
            ModelPartData bone8 = bone7.addChild("bone8", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 0.0F, 0.0F));
            ModelPartData cube_r8 = bone8.addChild("cube_r8", ModelPartBuilder.create().uv(0, 5).cuboid(0.0F, -1.5F, 2.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.25F)), ModelTransform.of(2.0F, 0.0F, -0.2F, -0.3927F, 0.2618F, 0.0F));
            ModelPartData bone11 = modelPartData.addChild("bone11", ModelPartBuilder.create(), ModelTransform.of(2.0F, 10.0F, 1.5F, -0.1745F, 0.0F, 0.0F));
            ModelPartData cube_r9 = bone11.addChild("cube_r9", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, 0.2618F, 0.0F));
            ModelPartData bone12 = bone11.addChild("bone12", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
            ModelPartData cube_r10 = bone12.addChild("cube_r10", ModelPartBuilder.create().uv(0, 5).cuboid(0.0F, -1.5F, 2.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, 0.2618F, 0.0F));
            ModelPartData bone9 = modelPartData.addChild("bone9", ModelPartBuilder.create(), ModelTransform.of(-2.0F, 10.0F, 1.5F, -0.1745F, 0.0F, 0.0F));
            ModelPartData cube_r11 = bone9.addChild("cube_r11", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.5F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, -0.2618F, 0.0F));
            ModelPartData bone10 = bone9.addChild("bone10", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
            ModelPartData cube_r12 = bone10.addChild("cube_r12", ModelPartBuilder.create().uv(0, 5).mirrored().cuboid(-1.0F, -1.5F, 2.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, -0.2F, -0.3927F, -0.2618F, 0.0F));
            return TexturedModelData.of(modelData, 16, 16);
        }

        @Override
        public Animation createGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone7", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone11", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone9", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation createActionAnimation() {
            return Animation.Builder.create(0.5F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone7", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone11", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone9", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.5F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .build();
        }
    }

}
