package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.mutation_setup.AquaticTree;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationInfo;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(InGameHud.class)
public class UnderwaterBreathSwapClientMixin {
    @WrapOperation(method = {"renderAirBubbles"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isSubmergedIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean swapWhereBreathing(PlayerEntity instance, TagKey<Fluid> tagKey, Operation<Boolean> original) {
        if (instance instanceof PlayerEntity) {
            List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(instance);
            if (mutations != null && (mutations.contains(MutationTrees.mutationToCodec(AquaticTree.gills3)) ||
                    mutations.contains(MutationTrees.mutationToCodec(AquaticTree.gills4)))){
                return instance.getWorld().getBlockState(BlockPos.ofFloored(instance.getX(), instance.getEyeY(), instance.getZ())).isOf(Blocks.AIR);
            }
        }
        return original.call(instance, tagKey);
    }

}
