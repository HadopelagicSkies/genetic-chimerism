package com.genetic_chimerism.mixin;

import com.genetic_chimerism.infusionblock.InfusionStation;
import com.genetic_chimerism.mutation_setup.AquaticTree;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@WrapOperation(method = {"sleep","method_18404","method_18405"}, constant = @Constant(classValue = BedBlock.class))
	private boolean allowInfusionStation(Object block, Operation<Boolean> original) {
		return block instanceof InfusionStation|| original.call(block);
	}

	@WrapOperation(method = {"baseTick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isSubmergedIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
	private boolean swapWhereBreathing(LivingEntity instance, TagKey tagKey, Operation<Boolean> original) {
		if(instance instanceof PlayerEntity && instance.getAttached(MutationAttachments.PLAYER_MUTATION_LIST) != null){
			if(instance.getAttached(MutationAttachments.PLAYER_MUTATION_LIST).contains(MutationTrees.mutationToCodec(AquaticTree.gills3)) ||
					instance.getAttached(MutationAttachments.PLAYER_MUTATION_LIST).contains(MutationTrees.mutationToCodec(AquaticTree.gills4))){
				return instance.getWorld().getBlockState(BlockPos.ofFloored(instance.getX(), instance.getEyeY(), instance.getZ())).isOf(Blocks.AIR);
			}
		}
		return original.call(instance, tagKey);
	}
}