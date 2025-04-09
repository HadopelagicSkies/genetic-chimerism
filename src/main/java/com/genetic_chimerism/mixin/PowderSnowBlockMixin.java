package com.genetic_chimerism.mixin;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.genetic_chimerism.mutation_setup.WoolenTree;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Inject(at = @At("TAIL"), method = "canWalkOnPowderSnow", cancellable = true)
    private static void woolenWalkOn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if(entity instanceof PlayerEntity) {
            cir.setReturnValue(MutationAttachments.getMutationsAttached(entity).contains(MutationTrees.mutationToCodec(WoolenTree.snowWalk)));
        }
    }
}
