package com.genetic_chimerism.mixin;

import com.genetic_chimerism.infusionblock.InfusionStation;
import com.genetic_chimerism.mutation_setup.Mutation;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationInfo;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.player.PlayerEntity;
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
		if (player.getSleepingPosition().map((pos) -> player.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation).orElse(false)){return sleepTimer >= 100;}
		else return isDay;
	}

	@Inject(at = @At("HEAD"), method = "wakeUp(ZZ)V")
	private void infuseOnWakeUp(CallbackInfo info) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if (player.getSleepTimer() >=100 && player.getSleepingPosition().map((pos) -> player.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation).orElse(false)){InfusionStation.infusePlayer(player.getSleepingPosition().get(),player);}
	}

	@Inject(at = @At ("RETURN"), method = "tick")
	private void callMutationTickers(CallbackInfo ci){
		PlayerEntity player = (PlayerEntity) (Object) this;
		List<MutationInfo> mutList = player.getAttached(MutationAttachments.PLAYER_MUTATION_LIST);
		if(mutList != null){
			for(MutationInfo mutation : mutList) {
				MutationTrees.mutationFromCodec(mutation).tick(player);
			}
		}
	}
}
