package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Mouse;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Mouse.class)
public class MouseMixin {
//    @WrapOperation(method = {"updateMouse"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"))
//    public void wallClimbChangeLooking(ClientPlayerEntity instance, double x, double y, Operation<Void> original) {
//        Direction walkFaceDirection = MutationAttachments.getWalkFaceDirection(instance);
//        if(walkFaceDirection != null) {
//            switch (walkFaceDirection) {
//                case DOWN -> {
//                    original.call(instance, -x, -y);
//                }
//                case UP -> {
//                    original.call(instance, x, y);
//                }
//                case NORTH -> {
//                    original.call(instance, y, -x);
//                }
//                case SOUTH -> {
//                    original.call(instance, y, -x);
//                }
//                case WEST -> {
//                    original.call(instance, -y, x);
//                }
//                case EAST -> {
//                    original.call(instance, y, -x);
//                }
//            }
//        }
//        original.call(instance, x, y);
//    }
}
