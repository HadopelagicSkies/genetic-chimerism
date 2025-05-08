package com.genetic_chimerism.mixin;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.mutation_setup.HoovedTree;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.genetic_chimerism.mutation_setup.TuskedTree;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.Consumable;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

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

    @WrapOperation(method = {"method_62848"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/component/type/Consumable;onConsume(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/component/type/ConsumableComponent;)V"))
    private void longPotionConsumable(Consumable instance, World world, LivingEntity livingEntity, ItemStack itemStack, ConsumableComponent consumableComponent, Operation<Void> original){
        if(livingEntity instanceof PlayerEntity){
            if(MutationAttachments.getMutationsAttached(livingEntity).contains(MutationTrees.mutationToCodec(HoovedTree.camelHump)) && itemStack.isOf(Items.POTION)){
                PotionContentsComponent oldPotionConsumable = itemStack.get(DataComponentTypes.POTION_CONTENTS);
                if(oldPotionConsumable != null){
                    List<StatusEffectInstance> oldPotionEffects = new ArrayList<>();
                    oldPotionConsumable.getEffects().forEach(oldPotionEffects::add);
                    List<StatusEffectInstance> newPotionEffects = new ArrayList<>();
                    for(StatusEffectInstance potionEffect : oldPotionEffects){
                        newPotionEffects.add(new StatusEffectInstance(potionEffect.getEffectType(),potionEffect.getDuration()*2,potionEffect.getAmplifier()));
                    }
                    PotionContentsComponent newPotionConsumable = new PotionContentsComponent(oldPotionConsumable.potion(),
                            oldPotionConsumable.customColor(),
                            newPotionEffects,
                            oldPotionConsumable.customName());
                    ItemStack newItemStack = itemStack.copy();
                    newItemStack.set(DataComponentTypes.POTION_CONTENTS,newPotionConsumable);
                    original.call(newPotionConsumable, world, livingEntity, newItemStack, consumableComponent);
                }
                else original.call(instance, world, livingEntity, itemStack, consumableComponent);
            }
            else original.call(instance, world, livingEntity, itemStack, consumableComponent);
        }
        else original.call(instance, world, livingEntity, itemStack, consumableComponent);
    }

    @WrapOperation(method = {"method_62849"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/consume/ConsumeEffect;onConsume(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)Z"))
    private static boolean longPotionConsumeEffect(ConsumeEffect instance, World world, ItemStack itemStack, LivingEntity livingEntity, Operation<Boolean> original){
        if(livingEntity instanceof PlayerEntity){
            if(MutationAttachments.getMutationsAttached(livingEntity).contains(MutationTrees.mutationToCodec(HoovedTree.camelHump)) && itemStack.isOf(Items.POTION)){
                PotionContentsComponent oldPotionConsumable = itemStack.get(DataComponentTypes.POTION_CONTENTS);
                if(oldPotionConsumable != null){
                    List<StatusEffectInstance> oldPotionEffects = new ArrayList<>();
                    oldPotionConsumable.getEffects().forEach(oldPotionEffects::add);
                    List<StatusEffectInstance> newPotionEffects = new ArrayList<>();
                    for(StatusEffectInstance potionEffect : oldPotionEffects){
                        newPotionEffects.add(new StatusEffectInstance(potionEffect.getEffectType(),potionEffect.getDuration()*2,potionEffect.getAmplifier()));
                    }
                    PotionContentsComponent newPotionConsumable = new PotionContentsComponent(oldPotionConsumable.potion(),
                            oldPotionConsumable.customColor(),
                            newPotionEffects,
                            oldPotionConsumable.customName());
                    ItemStack newItemStack = itemStack.copy();
                    newItemStack.set(DataComponentTypes.POTION_CONTENTS,newPotionConsumable);
                    ApplyEffectsConsumeEffect newConsumeEffect = new ApplyEffectsConsumeEffect(newPotionEffects, ((ApplyEffectsConsumeEffect) instance).probability());
                    return original.call(newConsumeEffect, world, newItemStack, livingEntity);
                }
                else return original.call(instance, world, itemStack, livingEntity);
            }
            else return original.call(instance, world, itemStack, livingEntity);
        }
        else return original.call(instance, world, itemStack, livingEntity);
    }
}
