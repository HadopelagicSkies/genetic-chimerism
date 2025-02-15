package com.genetic_chimerism;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.ArrayList;
import java.util.List;

public class ShapeRotationUtil {
    public static VoxelShape rotate90CCW(VoxelShape shape) {
        List<VoxelShape> shapes = new ArrayList<>();
        shape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            shapes.add(VoxelShapes.cuboid(minZ, minY, 1 - maxX, maxZ, maxY, 1 - minX));
        });
        return shapes.stream().reduce(VoxelShapes.empty(), VoxelShapes::union);
    }
}
