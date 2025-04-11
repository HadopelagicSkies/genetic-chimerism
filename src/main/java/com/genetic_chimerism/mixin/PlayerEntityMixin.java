package com.genetic_chimerism.mixin;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.infusionblock.InfusionStation;
import com.genetic_chimerism.mutation_setup.*;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

	@Shadow
	private int sleepTimer;

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

	@Inject(at = @At("RETURN"), method = "tick")
	private void callMutationTickers(CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		List<MutationInfo> mutList = MutationAttachments.getMutationsAttached(player);
		if (mutList != null && !mutList.isEmpty()) {
			for (MutationInfo mutationInfo : mutList) {
				Mutation mutation = MutationTrees.mutationFromCodec(mutationInfo);
				if (mutation != null) mutation.tick(player);
			}
		} else {
			for (MutatableParts part : MutatableParts.values()) {
				MutationBodyInfo mutationInfo = MutationAttachments.getPartAttached(player, part);
				Mutation mutation = MutationTrees.mutationFromCodec(mutationInfo);
				if (mutation != null) mutation.tick(player);
			}
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
			if (part != MutatableParts.MISC) {
				if (partMut != null && !partMut.isReceding() && partMut.growth() < MutationTrees.mutationFromCodec(partMut).getMaxGrowth()) {
					if (mutList.contains(MutationTrees.mutationToCodec(AmphibiousTree.growthSpeed))) {
						MutationAttachments.setPartAttached(player, part, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
								partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth() + 2, partMut.isReceding(), partMut.isAnimating()));
					} else {
						MutationAttachments.setPartAttached(player, part, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
								partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth() + 1, partMut.isReceding(), partMut.isAnimating()));
					}
				} else if (partMut != null && !partMut.isReceding() && partMut.growth() == MutationTrees.mutationFromCodec(partMut).getMaxGrowth()) {
					player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.fully_grown", part.getTranslatableName()), true);
					MutationAttachments.setPartAttached(player, part, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
							partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth() + 1, partMut.isReceding(), partMut.isAnimating()));
				} else if (partMut != null && partMut.isReceding() && partMut.growth() > 0) {
					if (mutList.contains(MutationTrees.mutationToCodec(AmphibiousTree.growthSpeed))) {
						MutationAttachments.setPartAttached(player, part, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
								partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth() - 2, partMut.isReceding(), partMut.isAnimating()));
					} else {
						MutationAttachments.setPartAttached(player, part, new MutationBodyInfo(partMut.mutID(), partMut.treeID(),
								partMut.patternIndex(), partMut.color1(), partMut.color2(), partMut.growth() - 1, partMut.isReceding(), partMut.isAnimating()));
					}
				} else if (partMut != null && partMut.isReceding() && partMut.growth() <= 0) {
					MutationAttachments.removePartAttached(player, part);
					player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.fully_receded", part.getTranslatableName()), true);
				}
			}
		}
	}
}
