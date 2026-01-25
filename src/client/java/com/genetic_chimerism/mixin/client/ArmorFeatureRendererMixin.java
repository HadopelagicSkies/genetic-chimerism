package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.LegMutationFeatureRenderer;
import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.PlayerRenderStateAccess;
import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup.MutationInfo;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.genetic_chimerism.mutation_setup.TentacledTree;
import com.genetic_chimerism.mutation_setup_client.HoovedTreeClient;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin (ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin {

    @Shadow @Final private EquipmentRenderer equipmentRenderer;

    @Inject(method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V"}, at = @At(value = "HEAD"), cancellable = true)
    private void inkInvisible(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, BipedEntityRenderState bipedEntityRenderState, float f, float g, CallbackInfo ci) {
        if (bipedEntityRenderState instanceof PlayerEntityRenderState) {
            PlayerRenderStateAccess playerState = (PlayerRenderStateAccess) bipedEntityRenderState;
            if (bipedEntityRenderState.invisibleToPlayer && playerState.genetic_chimerism$getMutInfo().get(MutatableParts.MISC) != null) {
                MutationInfo mutInfo = new MutationInfo(playerState.genetic_chimerism$getMutInfo().get(MutatableParts.MISC).mutID(), playerState.genetic_chimerism$getMutInfo().get(MutatableParts.MISC).treeID());
                if (mutInfo.equals(MutationTrees.mutationToCodec(TentacledTree.inkInvis)) || mutInfo.equals(MutationTrees.mutationToCodec(TentacledTree.inkBlind))) {
                    ci.cancel();
                }
            }
        }
    }

    @WrapOperation(method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/ArmorFeatureRenderer;renderArmor(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EquipmentSlot;ILnet/minecraft/client/render/entity/model/BipedEntityModel;)V",ordinal = 1))
    private void centaurNoLeggings(ArmorFeatureRenderer<BipedEntityRenderState, ArmorEntityModel<BipedEntityRenderState>,ArmorEntityModel<BipedEntityRenderState>> instance, MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, EquipmentSlot slot, int light, BipedEntityModel<BipedEntityRenderState> armorModel, Operation<Void> original, @Local(argsOnly = true) BipedEntityRenderState bipedEntityRenderState) {
        if (bipedEntityRenderState instanceof PlayerEntityRenderState) {
            PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) bipedEntityRenderState;
            MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(MutatableParts.LEG);
            if (mutInfo != null) {
                MutationClient mutation = MutationTreesClient.mutationFromCodec(mutInfo);
                if (!(mutation instanceof HoovedTreeClient.CentaurMutation)){
                    original.call(instance,matrices,vertexConsumers,stack,slot,light,armorModel);
                }
                else {
                    LegMutationFeatureRenderer.renderCentaurBarding(matrices,vertexConsumers,stack,slot,light,armorModel, instance.getContextModel(),equipmentRenderer);
                }
            }else
                original.call(instance,matrices,vertexConsumers,stack,slot,light,armorModel);
        }else
            original.call(instance,matrices,vertexConsumers,stack,slot,light,armorModel);
    }

    @WrapOperation(method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/ArmorFeatureRenderer;renderArmor(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EquipmentSlot;ILnet/minecraft/client/render/entity/model/BipedEntityModel;)V",ordinal = 2))
    private void centaurMoveBoots(ArmorFeatureRenderer<BipedEntityRenderState, ArmorEntityModel<BipedEntityRenderState>,ArmorEntityModel<BipedEntityRenderState>> instance, MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, EquipmentSlot slot, int light, BipedEntityModel<BipedEntityRenderState> armorModel, Operation<Void> original, @Local(argsOnly = true) BipedEntityRenderState bipedEntityRenderState) {
        if (bipedEntityRenderState instanceof PlayerEntityRenderState) {
            PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) bipedEntityRenderState;
            MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(MutatableParts.LEG);
            if (mutInfo != null) {
                MutationClient mutation = MutationTreesClient.mutationFromCodec(mutInfo);
                if (!(mutation instanceof HoovedTreeClient.CentaurMutation)){
                    original.call(instance,matrices,vertexConsumers,stack,slot,light,armorModel);
                }
                else {
                    LegMutationFeatureRenderer.renderCentaurBoots(matrices,vertexConsumers,stack,slot,light,armorModel, instance.getContextModel(),equipmentRenderer);
                }
            }else
                original.call(instance,matrices,vertexConsumers,stack,slot,light,armorModel);
        }else
            original.call(instance,matrices,vertexConsumers,stack,slot,light,armorModel);
    }
}
