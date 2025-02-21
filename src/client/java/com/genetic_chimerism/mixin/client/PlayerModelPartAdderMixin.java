package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.*;
import com.genetic_chimerism.mutationsetup.MutationAttachments;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public abstract class PlayerModelPartAdderMixin {

    @Invoker("addFeature")
    public abstract boolean invokeAddFeature(FeatureRenderer<?, ?> feature);


    @Inject(method = {"<init>"}, at = @At(value = "RETURN"))
    private void addPartToBody(EntityRendererFactory.Context ctx, EntityModel model, float shadowRadius, CallbackInfo ci) {
//        this.invokeAddFeature(new HeadMutationFeatureRenderer<>((LivingEntityRenderer<?, ?>) (Object) this));
//        this.invokeAddFeature(new BodyMutationFeatureRenderer<>((LivingEntityRenderer<?, ?>) (Object) this));
//        this.invokeAddFeature(new ArmMutationFeatureRenderer<>((LivingEntityRenderer<?, ?>) (Object) this));
//        this.invokeAddFeature(new LegMutationFeatureRenderer<>((LivingEntityRenderer<?, ?>) (Object) this));
//        this.invokeAddFeature(new TailMutationFeatureRenderer<>();


    }
}

