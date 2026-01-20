package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.GeneticChimerism;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class HoovedTreeClient {
    public static final MutationTreesClient hooved = MutationTreesClient.addTree(new ArrayList<MutationClient>(), "hooved");

    public static void initialize() {
    }

    public static final MutationClient hooves = hooved.addToTree(new HoovesMutation("hooves", "hooved"));
    public static final MutationClient camelHump = hooved.addToTree(new MutationClient("camelHump", "hooved"));
    public static final MutationClient centaur = hooved.addToTree(new CentaurMutation("centaur", "hooved"));

    public static class HoovesMutation extends MutationClient {
        public HoovesMutation(String mutID, String treeID) {
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
            ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(-0.25F, 0.0F, 0.0F));

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
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(1.5F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }
    }

    public static class CentaurMutation extends MutationClient {
        public CentaurMutation(String mutID, String treeID) {
            super(mutID, treeID);
        }

        @Override
        public Identifier getTexture1() {
            return Identifier.ofVanilla("textures/entity/horse/horse_white.png");
        }

        @Override
        public Identifier getTexture2() {
            return Identifier.ofVanilla("textures/entity/horse/horse_markings_whitedots.png");
        }

        @Override
        public TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData modelPartData2 = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 32).cuboid(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, new Dilation(0.05F)), ModelTransform.pivot(0.0F, 11.0F, 5.0F));
            modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 14.0F, 7.0F));
            modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 14.0F, 7.0F));
            modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 14.0F, -10.0F));
            modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 14.0F, -10.0F));
            modelPartData2.addChild("tail", ModelPartBuilder.create().uv(42, 36).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.0F, 2.0F, ((float) Math.PI / 6F), 0.0F, 0.0F));
            modelPartData2.addChild("saddle", ModelPartBuilder.create().uv(26, 0).cuboid(-5.0F, -8.0F, -9.0F, 10.0F, 9.0F, 9.0F, new Dilation(0.5F)), ModelTransform.NONE);
            return TexturedModelData.of(modelData, 64, 64);
        }

        @Override
        public Animation createGrowthAnimation() {
            return Animation.Builder.create(1.0F)
                    .addBoneAnimation("root", new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }

        @Override
        public Animation createPartAnimation() {
            return Animation.Builder.create(0.0F)
                    .addBoneAnimation("root", new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.0F, AnimationHelper.createScalingVector(0.0F, 1.0F, 2.0F), Transformation.Interpolations.LINEAR)
                    ))
                    .build();
        }
    }

}
