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
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class DroppedTailEntityRenderer extends LivingEntityRenderer<DroppedTailEntity, DroppedTailEntityRenderState, DroppedTailEntityModel> {

    public DroppedTailEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new DroppedTailEntityModel(ctx.getPart(GeneticChimerismClient.DROPPED_TAIL_MODEL_LAYER)), 0.25F);
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
        tailModel.setAngles(livingEntityRenderState);

        matrixStack.push();
        matrixStack.scale(1.5f,1.5f,1.5f);
        matrixStack.translate(0,-.5,-0.25);
        //matrixStack.multiply(new Quaternionf(0,1,1,0),0,0.5f,0);
        VertexConsumer vertexConsumer1 = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(getTexture(livingEntityRenderState)));
        tailModel.render(matrixStack, vertexConsumer1, i, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,livingEntityRenderState.color1));
        VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture2()));
        tailModel.render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,livingEntityRenderState.color2));
        VertexConsumer vertexConsumer3 = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture3()));
        tailModel.render(matrixStack, vertexConsumer3, i, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();
        

    }

    @Override
    public void updateRenderState(DroppedTailEntity livingEntity, DroppedTailEntityRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.patternIndex = livingEntity.patternIndex;
        livingEntityRenderState.color1 = livingEntity.color1;
        livingEntityRenderState.color2 = livingEntity.color2;
    }

    public static final Animation onFloor = Animation.Builder.create(0.0F)
            .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-142.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("body", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -11.0F, 5.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("body", new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0.0F, AnimationHelper.createScalingVector(1.5F, 1.5F, 1.5F), Transformation.Interpolations.LINEAR)
            ))
            .build();

}
