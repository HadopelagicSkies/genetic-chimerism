package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.*;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "updateRenderState(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;F)V", at = @At ("RETURN"))
    public void setupMutationRenderState(AbstractClientPlayerEntity abstractClientPlayerEntity, PlayerEntityRenderState playerEntityRenderState, float f, CallbackInfo ci){
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) playerEntityRenderState;
        accessedState.genetic_chimerism$setHeadInfo(abstractClientPlayerEntity.getAttached(MutationAttachments.HEAD_MUTATION));
        accessedState.genetic_chimerism$setTorsoInfo(abstractClientPlayerEntity.getAttached(MutationAttachments.TORSO_MUTATION));
        accessedState.genetic_chimerism$setArmInfo(abstractClientPlayerEntity.getAttached(MutationAttachments.ARM_MUTATION));
        accessedState.genetic_chimerism$setLegInfo(abstractClientPlayerEntity.getAttached(MutationAttachments.LEG_MUTATION));
        accessedState.genetic_chimerism$setTailInfo(abstractClientPlayerEntity.getAttached(MutationAttachments.TAIL_MUTATION));
    }

    @Inject(method = "<init>", at = @At ("RETURN"))
    public void addPartComponents(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci){
        PlayerEntityRenderer playerEntityRenderer = (PlayerEntityRenderer) ((Object) this);
        playerEntityRenderer.addFeature(new HeadMutationFeatureRenderer(playerEntityRenderer));
        playerEntityRenderer.addFeature(new TorsoMutationFeatureRenderer(playerEntityRenderer));
        playerEntityRenderer.addFeature(new ArmMutationFeatureRenderer(playerEntityRenderer));
        playerEntityRenderer.addFeature(new LegMutationFeatureRenderer(playerEntityRenderer));
        playerEntityRenderer.addFeature(new TailMutationFeatureRenderer(playerEntityRenderer));
    }
}








