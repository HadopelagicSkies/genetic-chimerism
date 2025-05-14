package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Camera.class)
public class CameraMixin {
    @Shadow private Entity focusedEntity;

//    @WrapOperation(method = {"setRotation"}, at = @At(value = "INVOKE", target = "Lorg/joml/Quaternionf;rotationYXZ(FFF)Lorg/joml/Quaternionf;"))
//    public Quaternionf wallClimbRotate(Quaternionf instance, float angleY, float angleX, float angleZ, Operation<Quaternionf> original) {
//        if (focusedEntity instanceof PlayerEntity) {
//            Direction walkFaceDirection = MutationAttachments.getWalkFaceDirection(focusedEntity);
//            float newAngleZ = angleZ;
//            switch (walkFaceDirection) {
//                case DOWN -> {
//                    newAngleZ = angleZ+(MathHelper.RADIANS_PER_DEGREE * 180F);
//                }
//                case UP -> {
//                    newAngleZ = angleZ+(MathHelper.RADIANS_PER_DEGREE * 0F);
//                }
//                case NORTH -> {
//                    newAngleZ = angleZ+(MathHelper.RADIANS_PER_DEGREE * 90F);
//                }
//                case SOUTH -> {
//                    newAngleZ = angleZ-(MathHelper.RADIANS_PER_DEGREE * 90F);
//                }
//                case WEST -> {
//                    newAngleZ = angleZ-(MathHelper.RADIANS_PER_DEGREE * 90F);
//                }
//                case EAST -> {
//                    newAngleZ = angleZ+(MathHelper.RADIANS_PER_DEGREE * 90F);
//                }
//            }
//            return original.call(instance, angleY, angleX, newAngleZ);
//        }
//        return original.call(instance, angleY, angleX, angleZ);
//    }
}
