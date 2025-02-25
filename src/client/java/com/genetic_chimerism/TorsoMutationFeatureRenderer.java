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

public class TorsoMutationFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private long runningTime = 0;

    public TorsoMutationFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
        if(accessedState.genetic_chimerism$getTorsoInfo() != null) {
            MutationClient mutation = MutationTreesClient.mutationFromCodec(accessedState.genetic_chimerism$getTorsoInfo());
            TexturedModelData modelData = mutation.getTexturedModelData();
            Identifier texture = mutation.getTexture();
            Animation animation = mutation.getPartAnimation();
            ModelPart model = modelData.createModel();
            model.copyTransform(this.getContextModel().body);

            MutationEntityModel entityModel = new MutationEntityModel(model);
            int animationSpeed = 3;
            matrices.push();
            if (animation != null) {
                AnimationHelper.animate(entityModel, animation, this.runningTime, 1, new Vector3f(0, 0, 0));
            }
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));
            entityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
            matrices.pop();
            if (animation != null) {
                if ((float) this.runningTime / 1000.0F > animation.lengthInSeconds() && animation.looping()) {
                    this.runningTime = 0;
                } else if ((float) this.runningTime / 1000.0F <= animation.lengthInSeconds()) {
                    this.runningTime += animationSpeed;
                }
            }
        }
    }
}
