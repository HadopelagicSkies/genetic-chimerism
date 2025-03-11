package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;

public interface PlayerRenderStateAccess {
    MutationBodyInfo genetic_chimerism$getHeadInfo();
    void genetic_chimerism$setHeadInfo(MutationBodyInfo info);

    MutationBodyInfo genetic_chimerism$getTorsoInfo();
    void genetic_chimerism$setTorsoInfo(MutationBodyInfo info);

    MutationBodyInfo genetic_chimerism$getArmInfo();
    void genetic_chimerism$setArmInfo(MutationBodyInfo info);

    MutationBodyInfo genetic_chimerism$getLegInfo();
    void genetic_chimerism$setLegInfo(MutationBodyInfo info);

    MutationBodyInfo genetic_chimerism$getTailInfo();
    void genetic_chimerism$setTailInfo(MutationBodyInfo info);

}
