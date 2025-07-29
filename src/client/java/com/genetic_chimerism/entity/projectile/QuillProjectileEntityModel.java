package com.genetic_chimerism.entity.projectile;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.math.MathHelper;

public class QuillProjectileEntityModel extends EntityModel<ProjectileEntityRenderState> {
    protected QuillProjectileEntityModel(ModelPart root)  {
        super(root, RenderLayer::getEntityCutout);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("back", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F), ModelTransform.of(-11.0F, 0.0F, 0.0F, ((float)Math.PI / 4F), 0.0F, 0.0F).withScale(0.8F));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(0, 0).cuboid(-12.0F, -2.0F, 0.0F, 16.0F, 4.0F, 0.0F, Dilation.NONE, 1.0F, 0.8F);
        modelPartData.addChild("cross_1", modelPartBuilder, ModelTransform.rotation(((float)Math.PI / 4F), 0.0F, 0.0F));
        modelPartData.addChild("cross_2", modelPartBuilder, ModelTransform.rotation(2.3561945F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData.transform((modelTransform) -> modelTransform.scaled(0.9F)), 32, 32);
    }

    public void setAngles(ProjectileEntityRenderState projectileEntityRenderState) {
        super.setAngles(projectileEntityRenderState);
        if (projectileEntityRenderState.shake > 0.0F) {
            float f = -MathHelper.sin(projectileEntityRenderState.shake * 3.0F) * projectileEntityRenderState.shake;
            this.root.roll += f * ((float)Math.PI / 180F);
        }

    }
}
