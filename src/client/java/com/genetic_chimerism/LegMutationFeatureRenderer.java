package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.joml.Vector3f;

public class LegMutationFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private long runningTime = 0;
    private long actionRunningTime = 0;

    public LegMutationFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
        MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(MutatableParts.LEG);
        if(mutInfo != null) {
            MutationClient mutation = MutationTreesClient.mutationFromCodec(mutInfo);
            TexturedModelData modelData = mutation.getTexturedModelData();
            Identifier texture1 = mutation.getTexture1();
            Identifier texture2 = mutation.getTexture2();
            Animation animationL = mutation.getPartAnimationL();
            Animation animationR = mutation.getPartAnimationR();
            Animation growthAnimationL = mutation.getGrowthAnimation();
            Animation growthAnimationR = mutation.getGrowthAnimation();
            Animation actionAnimationL = mutation.getActionAnimation();
            Animation actionAnimationR = mutation.getActionAnimation();
            ModelPart modelL = modelData.createModel();
            modelL.copyTransform(this.getContextModel().leftLeg);
            int growth = mutInfo.growth();
            int color1 = mutInfo.color1();
            int color2 = mutInfo.color2();

            MutationEntityModel entityModelL = new MutationEntityModel(modelL);
            int animationSpeed = 3;
            matrices.push();
            if (animationL != null && !mutInfo.isAnimating() && (double) growth /mutation.getNotClient().getMaxGrowth() > 0.2) {
                AnimationHelper.animate(entityModelL, animationL, this.runningTime, 1, new Vector3f(0, 0, 0));
            }
            else if (actionAnimationL != null && mutInfo.isAnimating() && (double) growth /mutation.getNotClient().getMaxGrowth() > 0.2){
                AnimationHelper.animate(entityModelL, actionAnimationL, this.actionRunningTime, 1, new Vector3f(0, 0, 0));
            }
            if (growthAnimationL != null) {
                AnimationHelper.animate(entityModelL, growthAnimationL, growth/mutation.getNotClient().getMaxGrowth() * 1000L, 1, new Vector3f(0, 0, 0));
            }
            this.getContextModel().leftLeg.hidden = true;
            VertexConsumer vertexConsumerL1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture1));
            entityModelL.render(matrices, vertexConsumerL1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color1));

            VertexConsumer vertexConsumerL2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture2));
            entityModelL.render(matrices, vertexConsumerL2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color2));
            matrices.pop();

            ModelPart modelR = modelData.createModel();
            modelR.scale(new Vector3f(-1f,0f,0f));
            modelR.copyTransform(this.getContextModel().rightLeg);

            MutationEntityModel entityModelR = new MutationEntityModel(modelR);
            matrices.push();
            if (animationR != null && !mutInfo.isAnimating() && (double) growth /mutation.getNotClient().getMaxGrowth() > 0.2) {
                AnimationHelper.animate(entityModelR, animationR, this.runningTime, 1, new Vector3f(0, 0, 0));
            }
            else if (actionAnimationR != null && mutInfo.isAnimating() && (double) growth /mutation.getNotClient().getMaxGrowth() > 0.2){
                AnimationHelper.animate(entityModelR, actionAnimationR, this.actionRunningTime, 1, new Vector3f(0, 0, 0));
            }
            if (growthAnimationR != null ) {
                AnimationHelper.animate(entityModelR, growthAnimationR, growth/mutation.getNotClient().getMaxGrowth() * 1000L, 1, new Vector3f(0, 0, 0));
            }
            this.getContextModel().rightLeg.hidden = true;
            VertexConsumer vertexConsumerR1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture1));
            entityModelR.render(matrices, vertexConsumerR1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color1));

            VertexConsumer vertexConsumerR2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture2));
            entityModelR.render(matrices, vertexConsumerR2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color2));
            matrices.pop();
            if (animationL != null) {
                if ((float) this.runningTime / 1000.0F > animationL.lengthInSeconds() && animationL.looping()) {
                    this.runningTime = 0;
                } else if ((float) this.runningTime / 1000.0F <= animationL.lengthInSeconds()) {
                    this.runningTime += animationSpeed;
                }
            }
            else if (animationR != null) {
                if ((float) this.runningTime / 1000.0F > animationR.lengthInSeconds() && animationR.looping()) {
                    this.runningTime = 0;
                } else if ((float) this.runningTime / 1000.0F <= animationR.lengthInSeconds()) {
                    this.runningTime += animationSpeed;
                }
            }
            if (actionAnimationL != null) {
                if ((float) this.actionRunningTime / 1000.0F > actionAnimationL.lengthInSeconds() && actionAnimationL.looping()) {
                    this.actionRunningTime = 0;
                } else if ((float) this.actionRunningTime / 1000.0F > actionAnimationL.lengthInSeconds() && !actionAnimationL.looping()) {
                    this.actionRunningTime = 0;
                    ClientPlayNetworking.send(new SetAnimPayload(MutatableParts.LEG,false));
                } else if (mutInfo.isAnimating() && (float) this.actionRunningTime / 1000.0F <= actionAnimationL.lengthInSeconds()) {
                    this.actionRunningTime += animationSpeed;
                }
            }
            else if (actionAnimationR != null) {
                if ((float) this.actionRunningTime / 1000.0F > actionAnimationR.lengthInSeconds() && actionAnimationR.looping()) {
                    this.actionRunningTime = 0;
                } else if ((float) this.actionRunningTime / 1000.0F > actionAnimationR.lengthInSeconds() && !actionAnimationR.looping()) {
                    this.actionRunningTime = 0;
                    ClientPlayNetworking.send(new SetAnimPayload(MutatableParts.LEG,false));
                } else if (mutInfo.isAnimating() && (float) this.actionRunningTime / 1000.0F <= actionAnimationR.lengthInSeconds()) {
                    this.actionRunningTime += 2*animationSpeed;
                }
            }
        }
    }
}
