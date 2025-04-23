package com.genetic_chimerism.mixin;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.genetic_chimerism.mutation_setup.WoolenTree;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @WrapOperation(method = {"getCollisionShape"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/PowderSnowBlock;canWalkOnPowderSnow(Lnet/minecraft/entity/Entity;)Z"))
    private boolean woolenWalkOn(Entity entity, Operation<Boolean> original) {
        if(entity instanceof PlayerEntity && MutationAttachments.getMutationsAttached(entity) != null) {
            return MutationAttachments.getMutationsAttached(entity).contains(MutationTrees.mutationToCodec(WoolenTree.snowWalk)) || original.call(entity);
        }
        return original.call(entity);
    }
}
