package com.genetic_chimerism.mixin;

import com.genetic_chimerism.InfusionBlock.InfusionStation;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BedBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerInfusionMixin {

	@WrapWithCondition(method = "trySleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setSpawnPoint(Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/util/math/BlockPos;FZZ)V") )
	private boolean infusionNotSetSpawn(ServerPlayerEntity instance, RegistryKey<World> dimension, BlockPos pos, float angle, boolean forced, boolean sendMessage) {
		return !(instance.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation);
	}

	@ModifyExpressionValue(method = "trySleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isDay()Z"))
	private boolean infusionRestWhenDay(boolean original, BlockPos pos ){
		PlayerEntity player = (PlayerEntity) (Object) this;
		return original && !( player.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation);
	}

	// deal with achievement for sleep here
//	@ModifyExpressionValue(method = "trySleep", at = @At(value = "INVOKE", target = "" ) )
//	private boolean infusionNoSleepAdvancement() {
//		return !(instance.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation);
//	}
}