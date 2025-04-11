package com.genetic_chimerism.mixin;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.genetic_chimerism.mutation_setup.TuskedTree;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ConsumableComponent.class)
public class ConsumableComponentMixin {
    @Inject(at = @At("RETURN"), method = "finishConsumption")
    private void noSickness(World world, LivingEntity user, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if(user instanceof PlayerEntity) {
            if(MutationAttachments.getMutationsAttached(user).contains(MutationTrees.mutationToCodec(TuskedTree.noSickness))){
                if (!world.isClient && user.hasStatusEffect(StatusEffects.HUNGER)) {
                   user.removeStatusEffect(StatusEffects.HUNGER);
                }
            }
        }
    }

//    @WrapOperation(method = {"finishConsumption"}, at = @At(value = "INVOKE", target = ""))
//    private void longPotion(){
//
//    }
}
