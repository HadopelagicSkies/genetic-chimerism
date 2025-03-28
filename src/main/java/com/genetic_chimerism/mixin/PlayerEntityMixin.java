package com.genetic_chimerism.mixin;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.infusionblock.InfusionStation;
import com.genetic_chimerism.mutation_setup.*;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
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
		if (mutList != null) {
			for (MutationInfo mutationInfo : mutList) {
				Mutation mutation = MutationTrees.mutationFromCodec(mutationInfo);
				if (mutation != null) mutation.tick(player);
			}
		}
	}

	@Inject(at = @At("RETURN"), method = "tick")
	private void updatePartGrowth(CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		List<MutationInfo> mutList = MutationAttachments.getMutationsAttached(player);

		for (MutatableParts part : MutatableParts.values()) {
			MutationBodyInfo partMut = MutationAttachments.getPartAttached(player, part);
			if (partMut != null && !partMut.isReceding() && partMut.growth() < MutationTrees.mutationFromCodec(partMut).getMaxGrowth()) {
				if (mutList.contains(MutationTrees.mutationToCodec(AmphibiousTree.growth_speed))) {
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
				if (mutList.contains(MutationTrees.mutationToCodec(AmphibiousTree.growth_speed))) {
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
