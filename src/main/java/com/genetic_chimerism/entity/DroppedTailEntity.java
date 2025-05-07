package com.genetic_chimerism.entity;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;

public class DroppedTailEntity extends MobEntity {

    private static final TrackedData<Integer> PATTERN_INDEX = DataTracker.registerData(DroppedTailEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> COLOR1 = DataTracker.registerData(DroppedTailEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> COLOR2 = DataTracker.registerData(DroppedTailEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public final AnimationState wiggling = new AnimationState();
    public int yawRot = this.random.nextBetween(0,360);
    public int lifetime =0;

    
    public DroppedTailEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
        this.setCustomNameVisible(false);
        wiggling.startIfNotRunning(this.age);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(PATTERN_INDEX,0);
        builder.add(COLOR1,ColorHelper.getArgb(229,222,191));
        builder.add(COLOR2,ColorHelper.getArgb(103,96,65));
    }

    public void setPatternIndex(int patternIndex){
        this.dataTracker.set(PATTERN_INDEX,patternIndex);
    }
    public void setColor1(int color1){
        this.dataTracker.set(COLOR1,color1);
    }
    public void setColor2(int color2){
        this.dataTracker.set(COLOR2,color2);
    }

    public int getPatternIndex() {
        return this.dataTracker.get(PATTERN_INDEX);
    }

    public int getColor1() {
        return this.dataTracker.get(COLOR1);
    }

    public int getColor2() {
        return this.dataTracker.get(COLOR2);
    }


    public static DefaultAttributeContainer createDroppedTailAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, 20.0F).add(EntityAttributes.FOLLOW_RANGE, 24.0).build();
    }

    @Override
    public void tick() {
        super.tick();
        this.lifetime++;
        if (!this.getWorld().isClient && lifetime > 3600){
            this.kill((ServerWorld) this.getWorld());
        }
    }
}
