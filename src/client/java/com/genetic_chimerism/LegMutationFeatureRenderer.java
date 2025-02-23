package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;

public class LegMutationFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {

    public LegMutationFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
        if(accessedState.genetic_chimerism$getLegInfo() != null) {
            TexturedModelData modelData = MutationTreesClient.mutationFromCodec(accessedState.genetic_chimerism$getLegInfo()).getTexturedModelData();
            Identifier texture = MutationTreesClient.mutationFromCodec(accessedState.genetic_chimerism$getLegInfo()).getTexture();
            ModelPart modelL = modelData.createModel();
            this.getContextModel().leftLeg.copyTransform(modelL);
            MutationEntityModel entityModelL = new MutationEntityModel(modelL);
            matrices.push();
            this.getContextModel().leftLeg.hidden = true;
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));
            entityModelL.setAngles(state);
            entityModelL.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
            matrices.pop();

            ModelPart modelR = modelData.createModel();
            modelR.scale(new Vector3f(-1f,0f,0f));
            this.getContextModel().rightLeg.copyTransform(modelR);
            MutationEntityModel entityModelR = new MutationEntityModel(modelR);
            matrices.push();
            this.getContextModel().rightLeg.hidden = true;
            vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));
            entityModelR.setAngles(state);
            entityModelR.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
            matrices.pop();
        }
    }
}
