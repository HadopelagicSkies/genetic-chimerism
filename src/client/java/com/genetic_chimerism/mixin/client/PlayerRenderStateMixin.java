package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.PlayerRenderStateAccess;
import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntityRenderState.class)
public class PlayerRenderStateMixin implements PlayerRenderStateAccess
{
    private @Unique MutationBodyInfo headInfo;
    private @Unique MutationBodyInfo torsoInfo;
    private @Unique MutationBodyInfo armInfo;
    private @Unique MutationBodyInfo legInfo;
    private @Unique MutationBodyInfo tailInfo;



    @Override
    public MutationBodyInfo genetic_chimerism$getHeadInfo() {
        return headInfo;
    }

    @Override
    public void genetic_chimerism$setHeadInfo(MutationBodyInfo info) {
        headInfo = info;
    }

    @Override
    public MutationBodyInfo genetic_chimerism$getTorsoInfo() {
        return torsoInfo;
    }

    @Override
    public void genetic_chimerism$setTorsoInfo(MutationBodyInfo info) {
        torsoInfo = info;
    }

    @Override
    public MutationBodyInfo genetic_chimerism$getArmInfo() {
        return armInfo;
    }

    @Override
    public void genetic_chimerism$setArmInfo(MutationBodyInfo info) {
        armInfo = info;
    }

    @Override
    public MutationBodyInfo genetic_chimerism$getLegInfo() {
        return legInfo;
    }

    @Override
    public void genetic_chimerism$setLegInfo(MutationBodyInfo info) {
        legInfo = info;
    }

    @Override
    public MutationBodyInfo genetic_chimerism$getTailInfo() {
        return tailInfo;
    }

    @Override
    public void genetic_chimerism$setTailInfo(MutationBodyInfo info) {
        tailInfo = info;
    }
}

