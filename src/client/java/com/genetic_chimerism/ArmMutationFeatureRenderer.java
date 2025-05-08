package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import com.genetic_chimerism.mutation_setup_client.WingedTreeClient;
import com.genetic_chimerism.packet_payloads.SetAnimPayload;
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

public class ArmMutationFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {

    public ArmMutationFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
        MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(MutatableParts.ARM);
        if(mutInfo != null) {
            MutationClient mutation = MutationTreesClient.mutationFromCodec(mutInfo);
            TexturedModelData modelData = mutation.getTexturedModelData();
            Identifier texture1 = mutation.getTexture1();
            Identifier texture2 = mutation.getTexture2();
            Animation mirrorAnimation = mutation.getAnimation("mirror");
            int color1 = mutInfo.color1();
            int color2 = mutInfo.color2();

            this.getContextModel().rightArm.hidden = true;
            this.getContextModel().rightSleeve.hidden = true;
            this.getContextModel().leftArm.hidden = true;
            this.getContextModel().leftSleeve.hidden = true;

            ModelPart modelR = modelData.createModel();
            modelR.copyTransform(this.getContextModel().rightArm);
            MutationEntityModel entityModelR = new MutationEntityModel(modelR,MutatableParts.ARM,false);
            entityModelR.setAngles(state);

            ModelPart modelL = modelData.createModel();
            modelL.copyTransform(this.getContextModel().leftArm);
            MutationEntityModel entityModelL = new MutationEntityModel(modelL,MutatableParts.ARM,true);
            entityModelL.setAngles(state);

            if (mirrorAnimation != null) {
                AnimationHelper.animate(entityModelL, mirrorAnimation, 0, 1, new Vector3f(0, 0, 0));
            }

            matrices.push();
            VertexConsumer vertexConsumerL1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture1));
            entityModelL.render(matrices, vertexConsumerL1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color1));
            VertexConsumer vertexConsumerL2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture2));
            entityModelL.render(matrices, vertexConsumerL2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color2));
            VertexConsumer vertexConsumerR1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture1));
            entityModelR.render(matrices, vertexConsumerR1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color1));
            VertexConsumer vertexConsumerR2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture2));
            entityModelR.render(matrices, vertexConsumerR2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color2));
            matrices.pop();

        }
        else{
            this.getContextModel().rightArm.hidden = false;
            this.getContextModel().rightSleeve.hidden = false;
            this.getContextModel().leftArm.hidden = false;
            this.getContextModel().leftSleeve.hidden = false;
        }
    }
}
