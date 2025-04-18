package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class TentacledTreeClient {
    public static final MutationTreesClient tentacled = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "tentacled");

    public static void initialize() {
    }

    public static final MutationClient tentacleArm1 = tentacled.addToTree(new MutationClient("tentacleArm1", "tentacled"));
    public static final MutationClient tentacleArm2 = tentacled.addToTree(new MutationClient("tentacleArm2", "tentacled"));

    public static final MutationClient inkInvis = tentacled.addToTree(new MutationClient("inkInvis", "tentacled"));
    public static final MutationClient inkBlind = tentacled.addToTree(new MutationClient("inkBlind", "tentacled"));
    public static final MutationClient siphonJet = tentacled.addToTree(new SiphonJetMutationClient("siphonJet", "tentacled"));

    public static final MutationClient inkGlow = tentacled.addToTree(new MutationClient("inkGlow", "tentacled"));
    public static final MutationClient inkFirey = tentacled.addToTree(new MutationClient("inkFirey", "tentacled"));
    public static final MutationClient fireyJet = tentacled.addToTree(new MutationClient("fireyJet", "tentacled"));


    public static class SiphonJetMutationClient extends MutationClient {
        public SiphonJetMutationClient(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/siphon_jets1.png");
        }

        @Override
        public Identifier getTexture2() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/siphon_jets2.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, 0.0F, 0.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, 0.5F, 2.0F, -0.829F, -0.2182F, 0.0F));

            ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create().uv(14, 5).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, 0.5F, 4.0F, -0.3491F, 0.0F, 0.0F));

            ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create().uv(0, 7).cuboid(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.75F, 0.5F, 2.0F, -0.829F, 0.2182F, 0.0F));

            ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create().uv(14, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 0.5F, 4.0F, -0.3491F, 0.0F, 0.0F));
            return TexturedModelData.of(modelData, 32, 32);
        }

        @Override
        public Animation getGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation getPartAnimation() {
            return Animation.Builder.create(1.0F).looping()
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.01F, 1.01F, 1.01F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.01F, 1.01F, 1.01F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.01F, 1.01F, 1.01F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5F, AnimationHelper.createScalingVector(1.01F, 1.01F, 1.01F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation getActionAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.3333F, AnimationHelper.createScalingVector(1.25F, 1.25F, 1.25F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5833F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.3333F, AnimationHelper.createScalingVector(0.75F, 0.75F, 0.75F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5833F, AnimationHelper.createScalingVector(1.25F, 1.25F, 1.25F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.3333F, AnimationHelper.createScalingVector(1.25F, 1.25F, 1.25F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5833F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.3333F, AnimationHelper.createScalingVector(0.75F, 0.75F, 0.75F), Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5833F, AnimationHelper.createScalingVector(1.25F, 1.25F, 1.25F), Transformation.Interpolations.CUBIC),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
                    ))
                    .build();
        }
    }


}
