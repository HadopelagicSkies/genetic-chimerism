package com.genetic_chimerism.mixin;

import com.genetic_chimerism.InfusionBlock.InfusionStation;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Consumer;

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
	@ModifyArg(method = "trySleep", at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/util/Either;ifRight(Ljava/util/function/Consumer;)Lcom/mojang/datafixers/util/Either;", remap = false))
	private Consumer<? super Unit> infusionNoSleepAdvancement(Consumer<? super Unit> consumer, @Local(argsOnly = true) BlockPos pos) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if(player.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation) return unit ->{};
		else return consumer;
	}
}