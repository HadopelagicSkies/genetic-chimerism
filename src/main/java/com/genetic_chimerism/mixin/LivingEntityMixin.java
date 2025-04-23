package com.genetic_chimerism.mixin;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.infusionblock.InfusionStation;
import com.genetic_chimerism.mutation_setup.*;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Shadow public abstract void remove(Entity.RemovalReason reason);

	@WrapOperation(method = {"sleep", "method_18404", "method_18405"}, constant = @Constant(classValue = BedBlock.class))
	private boolean allowInfusionStation(Object block, Operation<Boolean> original) {
		return block instanceof InfusionStation || original.call(block);
	}

	@WrapOperation(method = {"baseTick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isSubmergedIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
	private boolean swapWhereBreathing(LivingEntity instance, TagKey<Fluid> tagKey, Operation<Boolean> original) {
		if (instance instanceof PlayerEntity) {
			List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(instance);
			if (mutations != null && (mutations.contains(MutationTrees.mutationToCodec(AquaticTree.gills3)) ||
					mutations.contains(MutationTrees.mutationToCodec(AquaticTree.gills4)))) {
				if(mutations.contains(MutationTrees.mutationToCodec(AmphibiousTree.amphiGills))){
					return false;
				}
				return instance.getWorld().getBlockState(BlockPos.ofFloored(instance.getX(), instance.getEyeY(), instance.getZ())).isOf(Blocks.AIR);
			}
		}
		return original.call(instance, tagKey);
	}

	@WrapOperation(method = {"tickMovement"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;canFreeze()Z"))
	private boolean noFreeze(LivingEntity instance, Operation<Boolean> original) {
		if (instance instanceof PlayerEntity) {
			List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(instance);
			if (mutations != null && (mutations.contains(MutationTrees.mutationToCodec(WoolenTree.coldResist))))
				return false;
		}
		return original.call(instance);
	}

	@Inject(method = {"handleFallDamage"}, at = @At(value = "HEAD"))
	private void bouncyLanding(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir){
		LivingEntity entity = (LivingEntity) (Object) this;
		if(entity instanceof PlayerEntity && !entity.isSneaking() && MutationAttachments.getMutationsAttached(entity) != null){
			if(fallDistance >= 3 && MutationAttachments.getMutationsAttached(entity).contains(MutationTrees.mutationToCodec(WoolenTree.bouncyLanding))){
				entity.setOnGround(false);
				entity.setVelocity(entity.getVelocity().x, entity.getVelocity().y*-0.9, entity.getVelocity().z);
				entity.velocityModified=true;
			}
		}
	}

	@Inject(method = {"canGlide"}, at = @At(value = "RETURN"), cancellable = true)
	private void glideWithWings(CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity instanceof PlayerEntity && !entity.isOnGround() && !entity.hasVehicle() && !entity.hasStatusEffect(StatusEffects.LEVITATION)) {
			List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(entity);
			if(mutations.contains(MutationTrees.mutationToCodec(WingedTree.harpyWings)) && MutationAttachments.getPartAttached(entity, MutatableParts.ARM).growth() >= WingedTree.harpyWings.getMaxGrowth()){
				cir.setReturnValue(true);
			}
			if(mutations.contains(MutationTrees.mutationToCodec(WingedTree.backWings2)) && MutationAttachments.getPartAttached(entity, MutatableParts.TORSO).growth() >= WingedTree.backWings2.getMaxGrowth()){
				cir.setReturnValue(true);
			}
		}
	}
	
	@ModifyVariable(method = {"tickGliding"}, at = @At(value = "STORE"), name = "j")
	private int glideEquipDamageFix(int j) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity instanceof PlayerEntity) {
			List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(entity);
			if(mutations.contains(MutationTrees.mutationToCodec(WingedTree.backWings2)) || mutations.contains(MutationTrees.mutationToCodec(WingedTree.harpyWings))){
				if(j % 2 == 0){
					return j+1;
				}
			}
		}
        return j;
    }
}
