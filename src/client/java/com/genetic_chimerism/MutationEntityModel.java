package com.genetic_chimerism;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class MutationEntityModel extends EntityModel<PlayerEntityRenderState> {

    protected MutationEntityModel(ModelPart root) {
        super(root);
    }

    protected MutationEntityModel(ModelPart modelPart, Function<Identifier, RenderLayer> function) {
        super(modelPart, function);
    }
}
