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

public class HeadMutationFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {

    public HeadMutationFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
        if(accessedState.genetic_chimerism$getHeadInfo() != null) {
            TexturedModelData modelData = MutationTreesClient.mutationFromCodec(accessedState.genetic_chimerism$getHeadInfo()).getTexturedModelData();
            Identifier texture = MutationTreesClient.mutationFromCodec(accessedState.genetic_chimerism$getHeadInfo()).getTexture();
            ModelPart model = modelData.createModel();
            this.getContextModel().head.copyTransform(model);
            MutationEntityModel entityModel = new MutationEntityModel(model);
            matrices.push();
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));
            entityModel.setAngles(state);
            entityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
            matrices.pop();
        }
    }
}
