package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup_client.AmphibiousTreeClient;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import com.genetic_chimerism.packet_payloads.SetAnimPayload;
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
    private long frogRunningTime = 0;

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
            ModelPart model = modelData.createModel();
            model.copyTransform(this.getContextModel().body);
            int color1 = mutInfo.color1();
            int color2 = mutInfo.color2();

            if (state.isSwimming || state.isGliding){
                model.setTransform(ModelTransform.of(0,0,5F,
                        -30F * ((float)Math.PI / 180F), 0 * ((float)Math.PI / 180F), 0 * ((float)Math.PI / 180F)));
            }
            MutationEntityModel entityModel = new MutationEntityModel(model,MutatableParts.TAIL,false);
            entityModel.setAngles(state);

            if(mutInfo.actionAnim().isRunning() && mutation == AmphibiousTreeClient.tadpoleTail){
                TexturedModelData frogModelData = AmphibiousTreeClient.TadpoleTailMutation.getTongueModelData();
                ModelPart frogModel = frogModelData.createModel();
                frogModel.copyTransform(this.getContextModel().head);
                MutationEntityModel frogEntityModel = new MutationEntityModel(frogModel,MutatableParts.HEAD,true);
                frogEntityModel.setAngles(state);

                matrices.push();
                VertexConsumer vertexConsumerFrog = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(mutation.getTexture1()));
                frogEntityModel.render(matrices, vertexConsumerFrog, light, OverlayTexture.DEFAULT_UV, -1);
                matrices.pop();
            }

            matrices.push();
            VertexConsumer vertexConsumer1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture1));
            entityModel.render(matrices, vertexConsumer1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color1));
            VertexConsumer vertexConsumer2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(texture2));
            entityModel.render(matrices, vertexConsumer2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255,color2));
            matrices.pop();

        }
    }
}
