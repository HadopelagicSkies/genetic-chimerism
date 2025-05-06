package com.genetic_chimerism.entity;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.math.ColorHelper;

public class DroppedTailEntityRenderState extends LivingEntityRenderState {
    public long runningTime;
    public int patternIndex;
    public int color1;
    public int color2;
    public DroppedTailEntityRenderState() {
        this.patternIndex = 0;
        this.color1 = ColorHelper.getArgb(229,222,191);
        this.color2 = ColorHelper.getArgb(103,96,65);
        this.runningTime=0;
    }
}
