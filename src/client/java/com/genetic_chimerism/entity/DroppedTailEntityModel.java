package com.genetic_chimerism.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;

public class DroppedTailEntityModel extends EntityModel<DroppedTailEntityRenderState> {
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    protected DroppedTailEntityModel(ModelPart root) {
        super(root);
        this.bone = root.getChild("body");
        this.bone2 = this.bone.getChild("head");
        this.bone3 = this.bone2.getChild("tail");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 10.0F, 1.7F, -0.1222F, 0.0F, 0.0F));

        ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(0, 8).cuboid(-1.5F, -1.35F, -0.4F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.3F, -0.3491F, 0.0F, 0.0F));

        ModelPartData bone2 = bone.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.5F, 3.55F));

        ModelPartData cube_r2 = bone2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.45F, -0.2F, 4.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, -1.0F, -0.5672F, 0.0F, 0.0F));

        ModelPartData bone3 = bone2.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.5F, 3.75F));

        ModelPartData cube_r3 = bone3.addChild("cube_r3", ModelPartBuilder.create().uv(12, 8).cuboid(-1.5F, -1.7F, -0.2F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.25F, -1.0F, -0.7418F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(DroppedTailEntityRenderState state) {
        bone.pitch=0;
        bone2.pitch=0;
        bone3.pitch=0;
    }
}
