package com.genetic_chimerism.mixin;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.InfusionBlock.InfusionStation;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BedBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class InfusionSleepMixin {

	@WrapOperation(method = {"sleep","method_18404","method_18405"}, constant = @Constant(classValue = BedBlock.class))
	private boolean allowInfusionStation(Object block, Operation<Boolean> original) {
		return block instanceof InfusionStation|| original.call(block);
	}

}