package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationInfo;

public interface PlayerRenderStateAccess {
    MutationInfo genetic_chimerism$getHeadInfo();
    void genetic_chimerism$setHeadInfo(MutationInfo info);

    MutationInfo genetic_chimerism$getTorsoInfo();
    void genetic_chimerism$setTorsoInfo(MutationInfo info);

    MutationInfo genetic_chimerism$getArmInfo();
    void genetic_chimerism$setArmInfo(MutationInfo info);

    MutationInfo genetic_chimerism$getLegInfo();
    void genetic_chimerism$setLegInfo(MutationInfo info);

    MutationInfo genetic_chimerism$getTailInfo();
    void genetic_chimerism$setTailInfo(MutationInfo info);

}
