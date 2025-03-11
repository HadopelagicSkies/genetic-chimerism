package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
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
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;

public class ArmMutationFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private long runningTime = 0;

    public ArmMutationFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
        if(accessedState.genetic_chimerism$getArmInfo() != null) {
            MutationClient mutation = MutationTreesClient.mutationFromCodec(accessedState.genetic_chimerism$getArmInfo());
            TexturedModelData modelData = mutation.getTexturedModelData();
            Identifier texture = mutation.getTexture();
            Animation animationL = mutation.getPartAnimationL();
            Animation animationR = mutation.getPartAnimationR();
            ModelPart modelL = modelData.createModel();
            modelL.copyTransform(this.getContextModel().leftArm);

            int color1 = accessedState.genetic_chimerism$getArmInfo().color1();
            int color2 = accessedState.genetic_chimerism$getArmInfo().color2();

            MutationEntityModel entityModelL = new MutationEntityModel(modelL);
            int animationSpeed = 3;
            matrices.push();
            if (animationL != null) {
                AnimationHelper.animate(entityModelL, animationL, this.runningTime, 1, new Vector3f(0, 0, 0));
            }
            this.getContextModel().leftArm.hidden = true;
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));
            entityModelL.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
            matrices.pop();

            ModelPart modelR = modelData.createModel();
            modelR.scale(new Vector3f(-1f,0f,0f));
            modelR.copyTransform(this.getContextModel().rightArm);

            MutationEntityModel entityModelR = new MutationEntityModel(modelR);
            matrices.push();
            if (animationR != null) {
                AnimationHelper.animate(entityModelR, animationR, this.runningTime, 1, new Vector3f(0, 0, 0));
            }
            this.getContextModel().rightArm.hidden = true;
            vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));
            entityModelR.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
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
        }
    }
}
