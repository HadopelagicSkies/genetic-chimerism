package com.genetic_chimerism.mixin;


import com.genetic_chimerism.WalkFaceDirectionHelper;
import com.genetic_chimerism.mutation_setup.InvertebrateTree;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationInfo;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(Entity.class)
public class EntityMixin {
    @WrapOperation(method = {"canClimb"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean spiderClimbing(BlockState instance, TagKey tagKey, Operation<Boolean> original) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof PlayerEntity) {
            List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(entity);
            if (mutations != null && (mutations.contains(MutationTrees.mutationToCodec(InvertebrateTree.wallClimb))))
                return true;
        }
        return original.call(instance, tagKey);
    }

    @WrapOperation(method = "applyGravity", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;"))
    private Vec3d wallGravity(Vec3d instance, double x, double y, double z, Operation<Vec3d> original){
        // x is default zero
        // y is default -d (the gravity)
        // z is default zero
        Entity entity = (Entity) (Object) this;
        if (entity instanceof PlayerEntity) {
            Direction walkFaceDirection = MutationAttachments.getWalkFaceDirection(entity);
            switch (walkFaceDirection) {
                case DOWN -> {
                    return original.call(instance, x, -y, z);
                }
                case UP -> {
                    return original.call(instance, x, y, z);
                }
                case NORTH -> {
                    return original.call(instance, x, z, -y);
                }
                case SOUTH -> {
                    return original.call(instance, x, z, y);
                }
                case WEST -> {
                    return original.call(instance, -y, x, z);
                }
                case EAST -> {
                    return original.call(instance, y, x, z);
                }
            }
        }
        return original.call(instance, x, y, z);
    }

    @WrapOperation(method = "updateVelocity", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;movementInputToVelocity(Lnet/minecraft/util/math/Vec3d;FF)Lnet/minecraft/util/math/Vec3d;"))
    private Vec3d rotateInputs(Vec3d movementInput, float speed, float yaw, Operation<Vec3d> original){
        Entity entity = (Entity) (Object) this;
        if (entity instanceof PlayerEntity) {
            Direction walkFaceDirection = MutationAttachments.getWalkFaceDirection(entity);
            return WalkFaceDirectionHelper.rotateVectorForFace(original.call(movementInput, speed, yaw),walkFaceDirection);
        }
        return original.call(movementInput, speed, yaw);
    }
}
