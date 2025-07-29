package com.genetic_chimerism.entity.projectile;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.GeneticChimerismClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class QuillProjectileEntityRenderer extends ProjectileEntityRenderer<QuillProjectileEntity, QuillProjectileEntityRenderState> {
    private final QuillProjectileEntityModel model;
    public QuillProjectileEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new QuillProjectileEntityModel(context.getPart(GeneticChimerismClient.QUILLS_PROJECTILE_MODEL_LAYER));
    }

    @Override
    public void render(QuillProjectileEntityRenderState projectileEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(projectileEntityRenderState.yaw - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(projectileEntityRenderState.pitch));
        this.model.setAngles(projectileEntityRenderState);
        VertexConsumer vertexConsumer1 = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture(projectileEntityRenderState)));
        this.model.render(matrixStack, vertexConsumer1, i, OverlayTexture.DEFAULT_UV, projectileEntityRenderState.color1);
        VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture2(projectileEntityRenderState)));
        this.model.render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV, projectileEntityRenderState.color2);
        matrixStack.pop();
    }

    @Override
    protected Identifier getTexture(QuillProjectileEntityRenderState state) {
        return Identifier.of(GeneticChimerism.MOD_ID,"quill_projectile1");
    }

    protected Identifier getTexture2(QuillProjectileEntityRenderState state) {
        return Identifier.of(GeneticChimerism.MOD_ID,"quill_projectile2");
    }

    @Override
    public QuillProjectileEntityRenderState createRenderState() {
        return new QuillProjectileEntityRenderState();
    }

    @Override
    public void updateRenderState(QuillProjectileEntity persistentProjectileEntity, QuillProjectileEntityRenderState projectileEntityRenderState, float f) {
        super.updateRenderState(persistentProjectileEntity, projectileEntityRenderState, f);
        projectileEntityRenderState.color1 = persistentProjectileEntity.getColor1();
        projectileEntityRenderState.color2 = persistentProjectileEntity.getColor2();
    }
}
