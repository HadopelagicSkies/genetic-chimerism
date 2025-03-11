package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;

import java.util.Map;

public interface PlayerRenderStateAccess {
    Map<MutatableParts, MutationBodyInfo> genetic_chimerism$getMutInfo();
    void genetic_chimerism$setMutInfo(Map<MutatableParts, MutationBodyInfo> info);

}
