package com.genetic_chimerism.entity.projectile;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.GeneticChimerismEntities;
import com.genetic_chimerism.GeneticChimerismItems;
import com.genetic_chimerism.entity.DroppedTailEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
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
    public QuillProjectileEntity(EntityType<QuillProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COLOR1,ColorHelper.getArgb(110,90,63));
        builder.add(COLOR2,ColorHelper.getArgb(217,193,165));
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

    public static QuillProjectileEntity createQuill(PlayerEntity player){
        return new QuillProjectileEntity(GeneticChimerismEntities.QUILL_PROJECTILE,player.getWorld());
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(GeneticChimerismItems.PORCUPINE_QUILL);
    }

}
