package com.genetic_chimerism.mixin;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.infusionblock.InfusionStation;
import com.genetic_chimerism.mutation_setup.*;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Consumer;


@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

	@WrapWithCondition(method = "trySleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setSpawnPoint(Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/util/math/BlockPos;FZZ)V") )
	private boolean infusionNotSetSpawn(ServerPlayerEntity instance, RegistryKey<World> dimension, BlockPos pos, float angle, boolean forced, boolean sendMessage) {
		return !(instance.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation);
	}

	@ModifyExpressionValue(method = "trySleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isDay()Z"))
	private boolean infusionRestWhenDay(boolean original, BlockPos pos ){
		PlayerEntity player = (PlayerEntity) (Object) this;
		return original && !( player.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation);
	}

	@ModifyArg(method = "trySleep", at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/util/Either;ifRight(Ljava/util/function/Consumer;)Lcom/mojang/datafixers/util/Either;", remap = false))
	private Consumer<? super Unit> infusionNoSleepAdvancement(Consumer<? super Unit> consumer, @Local(argsOnly = true) BlockPos pos) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if(player.getWorld().getBlockState(pos).getBlock() instanceof InfusionStation) return unit ->{};
		else return consumer;
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

	@Inject(method = {"damage"},at = {@At("HEAD")})
	public void playerOnHit(ServerWorld world, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> info) {
		PlayerEntity playerEntity = (PlayerEntity) (Object) this;
		List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(playerEntity);
		if(mutations != null && !mutations.isEmpty()) {
			if(mutations.contains(MutationTrees.mutationToCodec(AmphibiousTree.regenChance1))){
				double regenChance = 10;
				boolean fromFire = damageSource.getType().equals(DamageTypes.ON_FIRE) || damageSource.getType().equals(DamageTypes.IN_FIRE) || damageSource.getType().equals(DamageTypes.CAMPFIRE);
				if (mutations.contains(MutationTrees.mutationToCodec(AmphibiousTree.regenChance2))) {
					regenChance += 10;
				}
				if (playerEntity.getRandom().nextBetween(0,100) <= regenChance) {
					playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, fromFire ? 2 : 1));
				} else if (fromFire) {
					playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1));
				}
			}

			if(damageSource.getAttacker() instanceof LivingEntity && mutations.contains(MutationTrees.mutationToCodec(SpinedTree.thornsChance1))) {
				double thornsChance = 25;
				int thornsDamage = 2;
				if(mutations.contains(MutationTrees.mutationToCodec(SpinedTree.thornsChance2))) {
					thornsChance += 25;
					if(mutations.contains(MutationTrees.mutationToCodec(SpinedTree.thornsChance3))) {
						thornsChance += 25;
					}
				}
				if(mutations.contains(MutationTrees.mutationToCodec(SpinedTree.thornsDmg1))) {
					thornsDamage += 20;
					if(mutations.contains(MutationTrees.mutationToCodec(SpinedTree.thornsDmg2))) {
						thornsDamage += 20;
						if(mutations.contains(MutationTrees.mutationToCodec(SpinedTree.thornsDmg3))) {
							thornsDamage += 20;
						}
					}
				}
				if(playerEntity.getRandom().nextBetween(0,100) <= thornsChance){
					damageSource.getAttacker().damage((ServerWorld) damageSource.getAttacker().getWorld(), new DamageSource((RegistryEntry<DamageType>) DamageTypes.CACTUS,playerEntity),thornsDamage);
				}
			}

			if (damageSource.getType().equals(DamageTypes.MAGIC) && playerEntity.hasStatusEffect(StatusEffects.POISON)){
				if(mutations.contains(MutationTrees.mutationToCodec(ScaledTree.poisonRes1))){
					double poisonResChance = 20;
					if (mutations.contains(MutationTrees.mutationToCodec(ScaledTree.poisonRes2))) {
						poisonResChance += 20;
						if (mutations.contains(MutationTrees.mutationToCodec(ScaledTree.poisonRes3))) {
							poisonResChance += 20;
						}
					}
					if (playerEntity.getRandom().nextBetween(0,100) <= poisonResChance) {
						playerEntity.removeStatusEffect(StatusEffects.POISON);
					}
				}
			}
			else if (damageSource.getType().equals(DamageTypes.MOB_PROJECTILE) || damageSource.getType().equals(DamageTypes.ARROW)){
				if(mutations.contains(MutationTrees.mutationToCodec(ShelledTree.projResist1))){
					float projReduction = 0.2F;
					if (mutations.contains(MutationTrees.mutationToCodec(ShelledTree.projResist2))) {
						projReduction += 0.2F;
						if (mutations.contains(MutationTrees.mutationToCodec(ShelledTree.projResist3))) {
							projReduction += 0.2F;
							if (mutations.contains(MutationTrees.mutationToCodec(ShelledTree.projResist4))) {
								projReduction += 0.2F;
							}
						}
					}
					amount *= projReduction;
				}
			}
			else if (damageSource.getType().equals(DamageTypes.EXPLOSION) || damageSource.getType().equals(DamageTypes.PLAYER_EXPLOSION)){
				if(mutations.contains(MutationTrees.mutationToCodec(WoolenTree.expResist1))){
					float expReduction = 0.2F;
					if (mutations.contains(MutationTrees.mutationToCodec(WoolenTree.expResist2))) {
						expReduction += 0.2F;
						if (mutations.contains(MutationTrees.mutationToCodec(WoolenTree.expResist3))) {
							expReduction += 0.2F;
							if (mutations.contains(MutationTrees.mutationToCodec(WoolenTree.expResist4))) {
								expReduction += 0.2F;
							}
						}
					}
					amount *= expReduction;
				}
			}
			else if (damageSource.getType().equals(DamageTypes.FREEZE) && mutations.contains(MutationTrees.mutationToCodec(ScaledTree.coldBlooded))){
				amount *= 3;
			}
		}
	}
}