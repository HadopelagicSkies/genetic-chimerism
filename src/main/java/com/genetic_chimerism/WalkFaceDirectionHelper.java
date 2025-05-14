package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class WalkFaceDirectionHelper {

    public static Direction rotatedHorizontalFacing(PlayerEntity player){
        Direction walkFace = MutationAttachments.getWalkFaceDirection(player);
        int quarterTurns = MathHelper.floor(player.getYaw() / 90.0 + 0.5) & 3;
        Direction[] rotatedHorizontals = new Direction[4];
        switch (walkFace){
            case DOWN -> {
                rotatedHorizontals = new Direction[]{Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST};
            }
            case UP -> {
                rotatedHorizontals = new Direction[]{Direction.SOUTH, Direction.WEST, Direction.NORTH, Direction.EAST};
            }
            case NORTH -> {
                rotatedHorizontals = new Direction[]{Direction.UP, Direction.EAST, Direction.DOWN, Direction.WEST};
            }
            case SOUTH -> {
                rotatedHorizontals = new Direction[]{Direction.UP, Direction.WEST, Direction.DOWN, Direction.EAST};
            }
            case WEST -> {
                rotatedHorizontals = new Direction[]{Direction.SOUTH, Direction.UP, Direction.NORTH, Direction.DOWN};
            }
            case EAST -> {
                rotatedHorizontals = new Direction[]{Direction.SOUTH, Direction.DOWN, Direction.NORTH, Direction.UP};
            }
        }
        return rotatedHorizontals[MathHelper.abs(quarterTurns % 4)];
    }

    public static Vec3d rotateVectorForFace(double x, double y, double z, Direction direction){
        switch (direction) {
            case DOWN -> {
                return new Vec3d(x,-y,z);
            }
            case UP -> {
                return new Vec3d(x,y,z);
            }
            case NORTH -> {
                return new Vec3d(x,z,y);
            }
            case SOUTH -> {
                return new Vec3d(x,z,-y);
            }
            case WEST -> {
                return new Vec3d(y,x,z);
            }
            case EAST -> {
                return new Vec3d(-y,x,z);
            }
        }
        return new Vec3d(x,y,z);
    }

    public static Vec3d rotateVectorForFace(Vec3d startVector, Direction direction){
        return rotateVectorForFace(startVector.x,startVector.y,startVector.z,direction);
    }
}
