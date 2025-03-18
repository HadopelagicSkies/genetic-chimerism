package com.genetic_chimerism.mixin;

import com.genetic_chimerism.infusionblock.InfusionStation;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BedBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(BedBlock.class)
public class BedBlockMixin {
        @WrapOperation(method = {"getDirection", "isBedBelow", "findWakeUpPosition*"}, constant = @Constant(classValue = BedBlock.class))
        private static boolean allowInfusionStation(Object block, Operation<Boolean> original) {
            return block instanceof InfusionStation || original.call(block);
        }

}
