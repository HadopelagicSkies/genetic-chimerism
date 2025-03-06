package com.genetic_chimerism.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AxolotlEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelTransformer;
import net.minecraft.client.render.entity.state.AxolotlEntityRenderState;
import net.minecraft.util.math.MathHelper;

public class DiplocaulusEntityModel extends AxolotlEntityModel {
    public static final float MOVING_IN_WATER_LEG_PITCH = 1.8849558F;
    public static final ModelTransformer BABY_TRANSFORMER = ModelTransformer.scaling(0.5F);
    private final ModelPart tail;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart topGills;
    private final ModelPart leftGills;
    private final ModelPart rightGills;
    private final ModelPart leftHead;
    private final ModelPart rightHead;
    private final ModelPart leftNeck;
    private final ModelPart rightNeck;

    public DiplocaulusEntityModel(ModelPart modelPart) {
        super(modelPart);
        this.body = modelPart.getChild("body");
        this.head = this.body.getChild("head");
        this.leftHead = this.head.getChild("left_head");
        this.rightHead = this.head.getChild("right_head");
        this.leftNeck = this.head.getChild("left_neck");
        this.rightNeck = this.head.getChild("right_neck");
        this.rightHindLeg = this.body.getChild("right_hind_leg");
        this.leftHindLeg = this.body.getChild("left_hind_leg");
        this.rightFrontLeg = this.body.getChild("right_front_leg");
        this.leftFrontLeg = this.body.getChild("left_front_leg");
        this.tail = this.body.getChild("tail");
        this.topGills = this.head.getChild("top_gills");
        this.leftGills = this.head.getChild("left_gills");
        this.rightGills = this.head.getChild("right_gills");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation = new Dilation(0.001F);

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -1.99F, -12.0F, 8.0F, 4.0F, 16.0F, dilation)
                .uv(0, 25).cuboid(0.0F, -3.0F, -6.0F, 0.0F, 5.0F, 10.0F, dilation), ModelTransform.pivot(0.0F, 20.0F, 5.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -9.0F));

        ModelPartData cube_r1 = head.addChild("left_neck", ModelPartBuilder.create().uv(0, 45).cuboid(-4.66F, -2.0F, -4.66F, 5.0F, 4.0F, 3.0F, dilation), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        ModelPartData cube_r2 = head.addChild("left_head", ModelPartBuilder.create().uv(34, 36).cuboid(-1.0F, -3.0F, -10.05F, 4.0F, 5.0F, 11.0F, dilation), ModelTransform.of(5.0F, 0.0F, -3.0F, 0.0F, 0.7854F, 0.0F));
        ModelPartData cube_r3 = head.addChild("right_head", ModelPartBuilder.create().uv(34, 20).cuboid(-3.0F, -3.0F, -10.05F, 4.0F, 5.0F, 11.0F, dilation), ModelTransform.of(-5.0F, 0.0F, -3.0F, 0.0F, -0.7854F, 0.0F));
        ModelPartData cube_r4 = head.addChild("right_neck", ModelPartBuilder.create().uv(16, 45).cuboid(-0.34F, -2.0F, -4.66F, 5.0F, 4.0F, 3.0F, dilation), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData left_front_leg = body.addChild("left_front_leg", ModelPartBuilder.create().uv(0, 27).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, dilation), ModelTransform.pivot(4.0F, 1.0F, -7.0F));
        ModelPartData right_front_leg = body.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 27).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, dilation), ModelTransform.pivot(-4.0F, 1.0F, -7.0F));
        ModelPartData right_hind_leg = body.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 27).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, dilation), ModelTransform.pivot(-4.0F, 1.0F, 1.0F));
        ModelPartData left_hind_leg = body.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 27).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, dilation), ModelTransform.pivot(4.0F, 1.0F, 1.0F));

        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, dilation);
        ModelPartBuilder modelPartBuilder2 = ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, dilation);
        ModelPartBuilder modelPartBuilder3 = ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, dilation);
        head.addChild("top_gills", modelPartBuilder, ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("left_gills", modelPartBuilder2, ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("right_gills", modelPartBuilder3, ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(0, 28).cuboid(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 12.0F, dilation), ModelTransform.pivot(0.0F, 0.0F, 4.0F));

        return TexturedModelData.of(modelData, 64, 64).transform(ModelTransformer.scaling(2.0F));
    }
    public static TexturedModelData getBabyTexturedModelData() {
    return getTexturedModelData().transform(BABY_TRANSFORMER);
    }

    public static DiplocaulusEntityModel getModel() {
        return new DiplocaulusEntityModel(getTexturedModelData().createModel());
    }

}

