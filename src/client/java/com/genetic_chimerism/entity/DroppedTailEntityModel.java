package com.genetic_chimerism.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;

public class DroppedTailEntityModel extends EntityModel<DroppedTailEntityRenderState> {
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    protected DroppedTailEntityModel(ModelPart root) {
        super(root);
        this.bone = root.getChild("bone");
        this.bone2 = this.bone.getChild("bone2");
        this.bone3 = this.bone.getChild("bone3");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.5F, -2.5F, 4.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.5F, 0.0F));

        ModelPartData bone3 = bone.addChild("bone3", ModelPartBuilder.create().uv(12, 8).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 2.5F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create().uv(0, 8).cuboid(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -2.5F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(DroppedTailEntityRenderState state) {
        bone.pitch=0;
        bone2.pitch=0;
        bone3.pitch=0;
    }
}
