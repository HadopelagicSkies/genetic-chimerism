package com.genetic_chimerism.entity.projectile;

import com.genetic_chimerism.entity.DroppedTailEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;

public class QuillProjectileEntity extends PersistentProjectileEntity {
    private static final TrackedData<Integer> COLOR1 = DataTracker.registerData(QuillProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> COLOR2 = DataTracker.registerData(QuillProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private double damage = 4.0;
    public QuillProjectileEntity(EntityType<QuillProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COLOR1, ColorHelper.getArgb(229,222,191));
        builder.add(COLOR2,ColorHelper.getArgb(103,96,65));
    }

    public void setColor1(int color1){
        this.dataTracker.set(COLOR1,color1);
    }
    public void setColor2(int color2){
        this.dataTracker.set(COLOR2,color2);
    }

    public int getColor1() {
        return this.dataTracker.get(COLOR1);
    }

    public int getColor2() {
        return this.dataTracker.get(COLOR2);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return null;
    }
}
