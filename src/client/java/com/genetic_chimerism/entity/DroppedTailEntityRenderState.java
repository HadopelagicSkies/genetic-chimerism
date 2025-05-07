package com.genetic_chimerism.entity;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class DroppedTailEntityRenderState extends LivingEntityRenderState {
    public int patternIndex;
    public int color1;
    public int color2;
    public int yawRot;
    public AnimationState animationState;

    public DroppedTailEntityRenderState() {
    }
}
