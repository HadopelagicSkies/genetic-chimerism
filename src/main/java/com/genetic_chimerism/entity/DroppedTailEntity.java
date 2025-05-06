package com.genetic_chimerism.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;

public class DroppedTailEntity extends MobEntity {
    public int patternIndex;
    public int color1 = ColorHelper.getArgb(229,222,191);
    public int color2 = ColorHelper.getArgb(103,96,65);
    public int age=0;
    
    public DroppedTailEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
        this.setCustomNameVisible(false);
    }

    public void setVisuals(int patternIndex, int color1, int color2){
        this.patternIndex = patternIndex;
        this.color1 = color1;
        this.color2 = color2;
    }

    public static DefaultAttributeContainer createDroppedTailAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, 20.0F).add(EntityAttributes.FOLLOW_RANGE, 24.0).build();
    }

    @Override
    public void tick() {
        super.tick();
        this.age++;
        if (!this.getWorld().isClient && age > 3600){
            this.kill((ServerWorld) this.getWorld());
        }
    }
}
