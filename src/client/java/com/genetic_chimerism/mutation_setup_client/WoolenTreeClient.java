package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class WoolenTreeClient {
    public static final MutationTreesClient woolen = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "woolen");

    public static void initialize() {
    }

    public static final MutationClient woolCoat = woolen.addToTree(new WoolCoatMutation("woolCoat", "woolen"));

    public static class WoolCoatMutation extends MutationClient {
        public WoolCoatMutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/wool_coat_whole.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -0.6F, -3.0F, 8.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create().uv(0, 20).cuboid(0.0F, -2.5F, -3.0F, 2.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 1.5F, 0.0F));

            ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create().uv(0, 9).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 10.0F, 0.0F));

            ModelPartData bone4 = modelPartData.addChild("bone4", ModelPartBuilder.create().uv(16, 9).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 10.0F, 0.0F));

            ModelPartData bone5 = modelPartData.addChild("bone5", ModelPartBuilder.create().uv(16, 20).cuboid(-2.0F, -2.5F, -3.0F, 2.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 1.5F, 0.0F));
            return TexturedModelData.of(modelData, 32, 32);
        }

        @Override
        public Animation createGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0F, 0F, 0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0F, 0F, 0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone3", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0F, 0F, 0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone4", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0F, 0F, 0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .addBoneAnimation("bone5", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.1F, 0.5F, 0.5F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }
    }
}
