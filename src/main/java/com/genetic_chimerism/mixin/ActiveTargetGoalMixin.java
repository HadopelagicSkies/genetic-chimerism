package com.genetic_chimerism.mixin;

import com.genetic_chimerism.entity.DroppedTailEntity;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.function.Predicate;

@Mixin(ActiveTargetGoal.class)
public class ActiveTargetGoalMixin {

    @WrapOperation(method = {"findClosestTarget"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getEntitiesByClass(Ljava/lang/Class;Lnet/minecraft/util/math/Box;Ljava/util/function/Predicate;)Ljava/util/List;"))
    public List droppedTailAggro(World instance, Class aClass, Box box, Predicate predicate, Operation<List> original){
        return original.call(instance, DroppedTailEntity.class, box, predicate) != null ?
                original.call(instance, DroppedTailEntity.class, box, predicate) : original.call(instance, aClass, box, predicate);
    }
}
