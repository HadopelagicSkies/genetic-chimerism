package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.PlayerRenderStateAccess;
import com.genetic_chimerism.mutation_setup.MutationInfo;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntityRenderState.class)
public class PlayerRenderStateMixin implements PlayerRenderStateAccess
{
    private @Unique MutationInfo headInfo;
    private @Unique MutationInfo torsoInfo;
    private @Unique MutationInfo armInfo;
    private @Unique MutationInfo legInfo;
    private @Unique MutationInfo tailInfo;



    @Override
    public MutationInfo genetic_chimerism$getHeadInfo() {
        return headInfo;
    }

    @Override
    public void genetic_chimerism$setHeadInfo(MutationInfo info) {
        headInfo = info;
    }

    @Override
    public MutationInfo genetic_chimerism$getTorsoInfo() {
        return torsoInfo;
    }

    @Override
    public void genetic_chimerism$setTorsoInfo(MutationInfo info) {
        torsoInfo = info;
    }

    @Override
    public MutationInfo genetic_chimerism$getArmInfo() {
        return armInfo;
    }

    @Override
    public void genetic_chimerism$setArmInfo(MutationInfo info) {
        armInfo = info;
    }

    @Override
    public MutationInfo genetic_chimerism$getLegInfo() {
        return legInfo;
    }

    @Override
    public void genetic_chimerism$setLegInfo(MutationInfo info) {
        legInfo = info;
    }

    @Override
    public MutationInfo genetic_chimerism$getTailInfo() {
        return tailInfo;
    }

    @Override
    public void genetic_chimerism$setTailInfo(MutationInfo info) {
        tailInfo = info;
    }
}

