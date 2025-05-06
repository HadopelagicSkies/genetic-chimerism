package com.genetic_chimerism.entity;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.GeneticChimerismClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.joml.Vector3f;

public class DroppedTailEntityRenderer extends LivingEntityRenderer<DroppedTailEntity, DroppedTailEntityRenderState, DroppedTailEntityModel> {
    public DroppedTailEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DroppedTailEntityModel(context.getPart(GeneticChimerismClient.DROPPED_TAIL_MODEL_LAYER)), 0.25F);
    }

    @Override
    public Identifier getTexture(DroppedTailEntityRenderState state) {
        return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/lizard_tail1.png");
    }

    public Identifier getTexture2() {
        return Identifier.of(GeneticChimerism.MOD_ID, "textures/body_part/lizard_tail2.png");
    }

    public Identifier getTexture3() {
        return Identifier.of(GeneticChimerism.MOD_ID, "textures/entity/dropped_tail_end.png");
    }

    @Override
    public DroppedTailEntityRenderState createRenderState() {
        return new DroppedTailEntityRenderState();
    }

    @Override
    public void render(DroppedTailEntityRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        DroppedTailEntityModel tailModel = this.model;
        tailModel.resetTransforms();
        AnimationHelper.animate(tailModel,wiggle,livingEntityRenderState.runningTime,1,new Vector3f(0,0,0));

        matrixStack.push();
        matrixStack.scale(1.5f,1.5f,1.5f);
        matrixStack.translate(0,-1.3,0);
        VertexConsumer vertexConsumer1 = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(getTexture(livingEntityRenderState)));
        tailModel.render(matrixStack, vertexConsumer1, i, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,livingEntityRenderState.color1));
        VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture2()));
        tailModel.render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,livingEntityRenderState.color2));
        VertexConsumer vertexConsumer3 = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture3()));
        tailModel.render(matrixStack, vertexConsumer3, i, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();

        if ((float) livingEntityRenderState.runningTime / 1000.0F > wiggle.lengthInSeconds()) {
            livingEntityRenderState.runningTime = 0;
        }
        else{
            livingEntityRenderState.runningTime += 10;
        }
    }

    @Override
    public void updateRenderState(DroppedTailEntity livingEntity, DroppedTailEntityRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.patternIndex = livingEntity.getPatternIndex();
        livingEntityRenderState.color1 = livingEntity.getColor1();
        livingEntityRenderState.color2 = livingEntity.getColor2();
    }

    public static final Animation wiggle = Animation.Builder.create(1.0F).looping()
            .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(8.0F, -12.5F, -2.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(8.0F, 12.5F, 2.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(8.0F, -12.5F, -2.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone2", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.2F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.2F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.2F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-13.0F, 12.0F, -3.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-13.0F, -12.0F, 3.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-13.0F, 12.0F, -3.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("bone3", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -0.35F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -0.35F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -0.35F), Transformation.Interpolations.CUBIC)
            ))
            .build();
}
