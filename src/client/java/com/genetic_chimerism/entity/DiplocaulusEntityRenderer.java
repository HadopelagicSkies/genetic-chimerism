package com.genetic_chimerism.entity;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.GeneticChimerismClient;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.AxolotlEntityRenderState;
import net.minecraft.util.Identifier;

public class DiplocaulusEntityRenderer extends AgeableMobEntityRenderer<DiplocaulusEntity, AxolotlEntityRenderState, DiplocaulusEntityModel> {
    public DiplocaulusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DiplocaulusEntityModel(context.getPart(GeneticChimerismClient.DIPLOCAULUS_MODEL_LAYER)), new DiplocaulusEntityModel(context.getPart(GeneticChimerismClient.DIPLOCAULUS_BABY_MODEL_LAYER)), 0.5F);
    }

    @Override
    public AxolotlEntityRenderState createRenderState() {
        return new AxolotlEntityRenderState();
    }

    @Override
    public Identifier getTexture(AxolotlEntityRenderState axolotlEntityRenderState) {
        return Identifier.of(GeneticChimerism.MOD_ID, "textures/entity/diplocaulus.png");
    }

    @Override
    public void updateRenderState(DiplocaulusEntity diplocaulusEntity, AxolotlEntityRenderState axolotlEntityRenderState, float f) {
        super.updateRenderState(diplocaulusEntity, axolotlEntityRenderState, f);
        axolotlEntityRenderState.variant = diplocaulusEntity.getVariant();
        axolotlEntityRenderState.playingDeadValue = diplocaulusEntity.playingDeadFf.getValue(f);
        axolotlEntityRenderState.inWaterValue = diplocaulusEntity.inWaterFf.getValue(f);
        axolotlEntityRenderState.onGroundValue = diplocaulusEntity.onGroundFf.getValue(f);
        axolotlEntityRenderState.isMovingValue = diplocaulusEntity.isMovingFf.getValue(f);
    }
}
