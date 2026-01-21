package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.*;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "updateRenderState(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;F)V", at = @At("RETURN"))
    public void setupMutationRenderState(AbstractClientPlayerEntity player, PlayerEntityRenderState playerEntityRenderState, float f, CallbackInfo ci) {
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) playerEntityRenderState;
        accessedState.genetic_chimerism$setMutInfo(Util.mapEnum(MutatableParts.class, part -> MutationAttachments.getPartAttached(player, part)));
        accessedState.genetic_chimerism$setCentaurSaddled(MutationAttachments.getCentaurSaddled(player));
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addPartComponents(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        PlayerEntityRenderer playerEntityRenderer = (PlayerEntityRenderer) ((Object) this);
        playerEntityRenderer.addFeature(new HeadMutationFeatureRenderer(playerEntityRenderer));
        playerEntityRenderer.addFeature(new TorsoMutationFeatureRenderer(playerEntityRenderer));
        playerEntityRenderer.addFeature(new ArmMutationFeatureRenderer(playerEntityRenderer));
        playerEntityRenderer.addFeature(new LegMutationFeatureRenderer(playerEntityRenderer));
        playerEntityRenderer.addFeature(new TailMutationFeatureRenderer(playerEntityRenderer));
        playerEntityRenderer.addFeature(new MiscMutationFeatureRenderer(playerEntityRenderer));
    }

    @WrapOperation(method = "renderRightArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;renderArm(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/util/Identifier;Lnet/minecraft/client/model/ModelPart;Z)V"))
    public void renderRightArmMutation(PlayerEntityRenderer instance, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Identifier skinTexture, ModelPart arm, boolean sleeveVisible, Operation<Void> original) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        MutationBodyInfo armMutationInfo = MutationAttachments.getPartAttached(player, MutatableParts.ARM);
        if (armMutationInfo != null) {
            MutationClient armMutation = MutationTreesClient.mutationFromCodec(armMutationInfo);

            ModelPart modelR = armMutation.getModelData().createModel();
            MutationEntityModel armModel = new MutationEntityModel(modelR, MutatableParts.ARM, false);

            armModel.resetTransforms();
            PlayerEntityModel playerEntityModel = instance.getModel();
            playerEntityModel.leftSleeve.visible = sleeveVisible;
            playerEntityModel.rightSleeve.visible = sleeveVisible;
            playerEntityModel.leftArm.roll = -0.1F;
            playerEntityModel.rightArm.roll = 0.1F;
            AnimationHelper.animate(armModel,MutationEntityModel.firstPersonArmOffset,0,1,new Vector3f(0, 0, 0));
            armModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(armMutation.getTexture1())), light, OverlayTexture.DEFAULT_UV,armMutationInfo.color1());
            armModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(armMutation.getTexture2())), light, OverlayTexture.DEFAULT_UV,armMutationInfo.color2());
        } else original.call(instance, matrices, vertexConsumers, light, skinTexture, arm, sleeveVisible);
    }

    @WrapOperation(method = "renderLeftArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;renderArm(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/util/Identifier;Lnet/minecraft/client/model/ModelPart;Z)V"))
    public void renderLeftArmMutation(PlayerEntityRenderer instance, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Identifier skinTexture, ModelPart arm, boolean sleeveVisible, Operation<Void> original) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        MutationBodyInfo armMutationInfo = MutationAttachments.getPartAttached(player, MutatableParts.ARM);
        if (armMutationInfo != null) {
            MutationClient armMutation = MutationTreesClient.mutationFromCodec(armMutationInfo);

            ModelPart modelL = armMutation.getModelData().createModel();
            modelL.scale(new Vector3f(-1f,0f,0f));
            MutationEntityModel armModel = new MutationEntityModel(modelL, MutatableParts.ARM, true);

            armModel.resetTransforms();
            PlayerEntityModel playerEntityModel = instance.getModel();
            playerEntityModel.leftSleeve.visible = sleeveVisible;
            playerEntityModel.rightSleeve.visible = sleeveVisible;

            playerEntityModel.leftArm.roll = -0.1F;
            playerEntityModel.rightArm.roll = 0.1F;
            AnimationHelper.animate(armModel,CustomAnimationHelper.mirrorAnimationX(MutationEntityModel.firstPersonArmOffset),0,1,new Vector3f(0, 0, 0));
            armModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(armMutation.getTexture1())), light, OverlayTexture.DEFAULT_UV,armMutationInfo.color1());
            armModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(armMutation.getTexture2())), light, OverlayTexture.DEFAULT_UV,armMutationInfo.color2());
        }else original.call(instance,matrices,vertexConsumers,light,skinTexture,arm,sleeveVisible);
    }

    @ModifyReturnValue(method = "getPositionOffset(Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;)Lnet/minecraft/util/math/Vec3d;", at = @At("RETURN"))
    public Vec3d centaurBodyOffset(Vec3d original, @Local(argsOnly = true) PlayerEntityRenderState playerEntityRenderState){
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) playerEntityRenderState;
        MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(MutatableParts.LEG);
        if(mutInfo != null && mutInfo.mutID().equals("centaur")){
            float forwardShift = 0.5f;
            return original.add(new Vec3d(forwardShift*MathHelper.sin(-playerEntityRenderState.bodyYaw * (float) (Math.PI / 180.0)),0.5f,forwardShift*MathHelper.cos(-playerEntityRenderState.bodyYaw * (float) (Math.PI / 180.0))));
        }
        return original;
    }
}








