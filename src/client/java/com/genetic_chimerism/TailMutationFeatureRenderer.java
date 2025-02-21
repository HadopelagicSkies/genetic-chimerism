package com.genetic_chimerism;

import com.genetic_chimerism.mutationsetup.AquaticTree;
import com.genetic_chimerism.mutationsetup.Mutation;
import com.genetic_chimerism.mutationsetup.MutationAttachments;
import com.genetic_chimerism.mutationsetup.MutationTrees;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class TailMutationFeatureRenderer< T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<S>> extends FeatureRenderer<S, M> {
    private final FeatureRendererContext<S, M> context;

    public TailMutationFeatureRenderer(FeatureRendererContext<S, M> context) {
        super(context);
        this.context = context;
    }

    public Mutation getSlotMutation(LivingEntity entity){
        if(entity instanceof PlayerEntity && entity.getAttached(MutationAttachments.TAIL_MUTATION) != null){
            return MutationTrees.mutationFromCodec(entity.getAttached(MutationAttachments.TAIL_MUTATION));
        }
        return null;
    }


    public M getContextModel() {
        return this.context.getModel();
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, S state, float limbAngle, float limbDistance) {

    }

//    @Override
//    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance) {
//        if(entity instanceof PlayerEntity && entity.getAttached(MutationAttachments.TAIL_MUTATION) != null) {
//            getContextModel().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(getSlotMutation(entity).TEXTURE)), light, 0, 0);
//        }
//    }
}
