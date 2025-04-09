package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.PlayerRenderStateAccess;
import com.genetic_chimerism.mutation_setup.MutationInfo;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.genetic_chimerism.mutation_setup.TentacledTree;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin (ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin {

    @Inject(method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V"}, at = @At(value = "HEAD"),cancellable = true)
    private void inkInvisible(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, BipedEntityRenderState bipedEntityRenderState, float f, float g, CallbackInfo ci) {
        if(bipedEntityRenderState instanceof PlayerEntityRenderState)
        {
            PlayerRenderStateAccess playerState = (PlayerRenderStateAccess) bipedEntityRenderState;
            if(bipedEntityRenderState.invisibleToPlayer && playerState.genetic_chimerism$getMutInfo().get(MutatableParts.MISC) != null){
                MutationInfo mutInfo = new MutationInfo(playerState.genetic_chimerism$getMutInfo().get(MutatableParts.MISC).mutID(),playerState.genetic_chimerism$getMutInfo().get(MutatableParts.MISC).treeID());
                if (mutInfo.equals(MutationTrees.mutationToCodec(TentacledTree.inkInvis)) || mutInfo.equals(MutationTrees.mutationToCodec(TentacledTree.inkBlind))){
                    ci.cancel();
                }
            }
        }
    }
}
