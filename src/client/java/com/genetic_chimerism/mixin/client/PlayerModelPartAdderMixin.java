package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.mutationsetup.AquaticTree;
import com.genetic_chimerism.mutationsetup.MutationAttachments;
import com.genetic_chimerism.mutationsetup.MutationTrees;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BipedEntityModel.class)
public class PlayerModelPartAdderMixin {
//  @WrapOperation(method = {"getModelData"}, at = @At(value = "INVOKE", target = ""))
//    private boolean addPartToBody(PlayerEntity instance, TagKey tagKey, Operation<Boolean> original) {
//        if (instance instanceof PlayerEntity && instance.getAttached(MutationAttachments.PLAYER_MUTATION_LIST) != null) {
//
//            if (instance.getAttached(MutationAttachments.HEAD_MUTATION) != null){
//
//            }
//            if (instance.getAttached(MutationAttachments.BACK_MUTATION) != null){
//
//            }
//            if (instance.getAttached(MutationAttachments.ARM_MUTATION) != null){
//
//            }
//            if (instance.getAttached(MutationAttachments.LEG_MUTATION) != null){
//
//            }
//            if (instance.getAttached(MutationAttachments.TAIL_MUTATION) != null){
//
//            }
//        }
//        return ;
//    }

}

