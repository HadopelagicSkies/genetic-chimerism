package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelTransform;
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

public class TailMutationFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private long runningTime = 0;
    private long actionRunningTime = 0;

    public TailMutationFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
        MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(MutatableParts.TAIL);
        if (mutInfo != null) {
            MutationClient mutation = MutationTreesClient.mutationFromCodec(mutInfo);
            TexturedModelData modelData = mutation.getTexturedModelData();
            Identifier texture1 = mutation.getTexture1();
            Identifier texture2 = mutation.getTexture2();
            Animation animation = mutation.getPartAnimation();
            Animation growthAnimation = mutation.getGrowthAnimation();
            Animation actionAnimation = mutation.getActionAnimation();
            ModelPart model = modelData.createModel();
            model.copyTransform(this.getContextModel().body);
            int growth = mutInfo.growth();
            int color1 = mutInfo.color1();
            int color2 = mutInfo.color2();

            int animationSpeed = 3;
            if (state.isSwimming || state.isGliding){
                model.setTransform(ModelTransform.of(0,0,5F,
                        -30F * ((float)Math.PI / 180F), 0 * ((float)Math.PI / 180F), 0 * ((float)Math.PI / 180F)));
                animationSpeed += 6;
            }
            MutationEntityModel entityModel = new MutationEntityModel(model);
            matrices.push();
            if (animation != null && !mutInfo.isAnimating() && (double) growth /mutation.getNotClient().getMaxGrowth() > 0.2) {
                AnimationHelper.animate(entityModel, animation, this.runningTime, 1, new Vector3f(0, 0, 0));
            }
            else if (actionAnimation != null && mutInfo.isAnimating() && (double) growth /mutation.getNotClient().getMaxGrowth() > 0.2){
                AnimationHelper.animate(entityModel, actionAnimation, this.actionRunningTime, 1, new Vector3f(0, 0, 0));
            }
            if (growthAnimation != null) {
                AnimationHelper.animate(entityModel, growthAnimation, growth/mutation.getNotClient().getMaxGrowth() * 1000L, 1, new Vector3f(0, 0, 0));
            }
            VertexConsumer vertexConsumer1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture1));
            entityModel.render(matrices, vertexConsumer1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color1));

            VertexConsumer vertexConsumer2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture2));
            entityModel.render(matrices, vertexConsumer2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color2));
            matrices.pop();
            if (animation != null) {
                if ((float) this.runningTime / 1000.0F > animation.lengthInSeconds() && animation.looping()) {
                    this.runningTime = 0;
                } else if ((float) this.runningTime / 1000.0F <= animation.lengthInSeconds()) {
                    this.runningTime += animationSpeed;
                }
            }
            if (actionAnimation != null) {
                if ((float) this.actionRunningTime / 1000.0F > actionAnimation.lengthInSeconds() && actionAnimation.looping()) {
                    this.actionRunningTime = 0;
                } else if ((float) this.actionRunningTime / 1000.0F > actionAnimation.lengthInSeconds() && !actionAnimation.looping()) {
                    this.actionRunningTime = 0;
                    ClientPlayNetworking.send(new SetAnimPayload(MutatableParts.TAIL,false));
                } else if (mutInfo.isAnimating() && (float) this.actionRunningTime / 1000.0F <= actionAnimation.lengthInSeconds()) {
                    this.actionRunningTime += 2*animationSpeed;
                }
            }
        }
    }
}
