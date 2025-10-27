package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class MiscMutationFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {

    public MiscMutationFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        if (this.getContextModel().body.visible) {
            PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
            MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(MutatableParts.MISC);
            if (mutInfo != null) {
                MutationClient mutation = MutationTreesClient.mutationFromCodec(mutInfo);
                TexturedModelData modelData = mutation.getModelData();
                if (modelData == null) return;
                Identifier texture1 = mutation.getTexture1();
                Identifier texture2 = mutation.getTexture2();

                ModelPart model = modelData.createModel();
                model.copyTransform(this.getContextModel().body);
                int color1 = mutInfo.color1();
                int color2 = mutInfo.color2();
                MutationEntityModel entityModel = new MutationEntityModel(model, MutatableParts.MISC, false);
                entityModel.setAngles(state);

                matrices.push();
                VertexConsumer vertexConsumer1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture1));
                entityModel.render(matrices, vertexConsumer1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255, color1));
                if (texture2 != null) {
                    VertexConsumer vertexConsumer2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture2));
                    entityModel.render(matrices, vertexConsumer2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255, color2));
                }
                matrices.pop();
            }
        }
    }
}
