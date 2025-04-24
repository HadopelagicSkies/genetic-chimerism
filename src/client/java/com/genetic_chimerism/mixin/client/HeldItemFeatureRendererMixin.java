package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.PlayerRenderStateAccess;
import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import com.genetic_chimerism.mutation_setup_client.WingedTreeClient;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemFeatureRenderer.class)
public class HeldItemFeatureRendererMixin {

    @Inject(method = {"renderItem"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V", shift = At.Shift.AFTER))
    public void flyingItemOffset(ArmedEntityRenderState entityState, ItemRenderState itemState, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci){
        if(entityState instanceof PlayerEntityRenderState){
            PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) entityState;
            MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(MutatableParts.ARM);
            if(mutInfo != null){
            MutationClient mutation = MutationTreesClient.mutationFromCodec(mutInfo);
            if (mutation == WingedTreeClient.harpyWings) {
                if (((PlayerEntityRenderState) entityState).isGliding) {
                    boolean bl = arm == Arm.LEFT;
                    matrices.translate((float)(bl ? -1 : 1)/1.75, -0.3, 0.65);
                }
            }
            }
        }
    }
}
