package com.genetic_chimerism.mixin;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.blocks.infusionblock.InfusionStation;
import com.genetic_chimerism.mutation_setup.*;
import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.*;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

	@Unique
	private static final float centaurEyeHeight = 2.13f;

	@Unique
	private static final Map<EntityPose, EntityDimensions> CENTAUR_POSE_DIMENSIONS = ImmutableMap.<EntityPose, EntityDimensions>builder()
			.put(
					EntityPose.STANDING,
					EntityDimensions.changing(1.3F, centaurEyeHeight + 0.2F)
							.withEyeHeight(centaurEyeHeight)
							.withAttachments(EntityAttachments.builder().add(EntityAttachmentType.VEHICLE, new Vec3d(0.0, 0.6F, 0.0))))
			.put(EntityPose.SLEEPING, EntityDimensions.changing(1.3F, centaurEyeHeight + 0.2F).withEyeHeight(centaurEyeHeight))
			.put(EntityPose.GLIDING, EntityDimensions.changing(1.3F, centaurEyeHeight + 0.2F).withEyeHeight(centaurEyeHeight))
			.put(EntityPose.SWIMMING, EntityDimensions.changing(1.3F, centaurEyeHeight + 0.2F).withEyeHeight(centaurEyeHeight))
			.put(EntityPose.SPIN_ATTACK, EntityDimensions.changing(1.3F, centaurEyeHeight + 0.2F).withEyeHeight(centaurEyeHeight))
			.put(
					EntityPose.CROUCHING,
					EntityDimensions.changing(1.3F, centaurEyeHeight - 0.1F)
							.withEyeHeight(centaurEyeHeight - 0.3F)
							.withAttachments(EntityAttachments.builder().add(EntityAttachmentType.VEHICLE, new Vec3d(0.0, 0.6F - 0.3F, 0.0)))
			)
			.put(EntityPose.DYING, EntityDimensions.fixed(0.2F, 0.2F).withEyeHeight(1.62F))
			.build();

	@Shadow
	private int sleepTimer;

	@Shadow @Final public PlayerScreenHandler playerScreenHandler;

	@Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

	@ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isDay()Z"))
	private boolean overrideDay(boolean isDay) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if (player.getSleepingPosition().map((pos) -> player.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation).orElse(false)) {
			return sleepTimer >= 100;
		} else return isDay;
	}

	@Inject(at = @At("HEAD"), method = "wakeUp(ZZ)V")
	private void infuseOnWakeUp(CallbackInfo info) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if (player.getSleepTimer() >= 100 && player.getSleepingPosition().map((pos) -> player.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation).orElse(false)) {
			InfusionStation.infusePlayer(player.getSleepingPosition().get(), player);
		}
	}

	@WrapOperation(method = {"addExhaustion"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;addExhaustion(F)V"))
	private void noExhaustion(HungerManager instance, float exhaustion, Operation<Void> original) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(player);
		boolean exhausting = true;
		if (mutations != null && (mutations.contains(MutationTrees.mutationToCodec(TuskedTree.slowHunger1)))) {
			double noExhaustChance = 0.2;
			if (mutations.contains(MutationTrees.mutationToCodec(TuskedTree.slowHunger2))) {
				noExhaustChance += 0.2;
			}
			if (Math.random() >= noExhaustChance) {
				exhausting = false;
			}
		}
		original.call(instance, exhausting ? exhaustion : 0);
	}

	@Inject(at = @At("RETURN"), method = "tick")
	private void updatePartGrowth(CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		List<MutationInfo> mutList = MutationAttachments.getMutationsAttached(player);

		for (MutatableParts part : MutatableParts.values()) {
			MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, part);
			if (partMut == null) {
				continue;
			}
			if (partMut != null && !partMut.isReceding() && partMut.growth() < MutationTrees.mutationFromCodec(partMut).getMaxGrowth()) {
				if (mutList.contains(MutationTrees.mutationToCodec(AmphibiousTree.growthSpeed))) {
					MutationAttachments.setPartGrowth(player, part, partMut.growth() + 2);
				} else {
					MutationAttachments.setPartGrowth(player, part, partMut.growth() + 1);
				}
			} else if (partMut != null && !partMut.isReceding() && (partMut.growth() == MutationTrees.mutationFromCodec(partMut).getMaxGrowth() || partMut.growth() == MutationTrees.mutationFromCodec(partMut).getMaxGrowth() + 1)){
				player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.fully_grown", part.getTranslatableName()), true);
				MutationAttachments.setPartGrowth(player, part, partMut.growth() + 2);
			} else if (partMut != null && partMut.isReceding() && partMut.growth() > 0) {
				if (mutList.contains(MutationTrees.mutationToCodec(AmphibiousTree.growthSpeed))) {
					MutationAttachments.setPartGrowth(player, part, partMut.growth() - 2);
				} else {
					MutationAttachments.setPartGrowth(player, part, partMut.growth() - 1);
				}
			} else if (partMut != null && partMut.isReceding() && partMut.growth() <= 0) {
				MutationAttachments.removePartAttached(player, part);
				player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.fully_receded", part.getTranslatableName()), true);
			}
		}
	}

	@WrapMethod(method = {"getBaseDimensions"})
	private EntityDimensions centaurPoses(EntityPose pose, Operation<EntityDimensions> original) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if(MutationAttachments.getMutationsAttached(player) != null && MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(HoovedTree.centaur))){
			return CENTAUR_POSE_DIMENSIONS.getOrDefault(pose, EntityDimensions.changing(0.6F, 1.8F));
		}
		else
			return original.call(pose);
	}

	@WrapMethod(method = {"shouldDismount"})
	private boolean centaurDismountLiving(Operation<Boolean> original) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if(MutationAttachments.getMutationsAttached(player) != null && MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(HoovedTree.centaur))){
			return original.call() || player.getVehicle() instanceof LivingEntity;
		}
		else
			return original.call();
	}

}
