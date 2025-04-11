package com.genetic_chimerism.mixin;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.genetic_chimerism.mutation_setup.TuskedTree;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodComponent.class)
public class FoodComponentMixin {
    @Inject(at = @At("RETURN"), method = "onConsume")
    private void playerOnConsume(World world, LivingEntity user, ItemStack stack, ConsumableComponent consumable, CallbackInfo ci) {
        if(user instanceof PlayerEntity) {
            FoodComponent foodComponent = (FoodComponent) (Object) this;

            if(MutationAttachments.getMutationsAttached(user).contains(MutationTrees.mutationToCodec(TuskedTree.bonusFood1))){
                double bonusFood = 1.25;
                if(MutationAttachments.getMutationsAttached(user).contains(MutationTrees.mutationToCodec(TuskedTree.bonusFood2))){
                    bonusFood += 0.25;
                    if(MutationAttachments.getMutationsAttached(user).contains(MutationTrees.mutationToCodec(TuskedTree.bonusFood3))){
                        bonusFood += 0.25;
                        if(MutationAttachments.getMutationsAttached(user).contains(MutationTrees.mutationToCodec(TuskedTree.bonusFood4))){
                            bonusFood += 0.25;
                        }
                    }
                }
                ((PlayerEntity)user).getHungerManager().eat(new FoodComponent((int) Math.round(foodComponent.nutrition() * bonusFood), foodComponent.saturation(), true));
            }

        }
    }
}
