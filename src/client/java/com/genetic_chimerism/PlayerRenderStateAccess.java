package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import net.minecraft.item.Item;

import java.util.Map;

public interface PlayerRenderStateAccess {
    Map<MutatableParts, MutationBodyInfo> genetic_chimerism$getMutInfo();
    void genetic_chimerism$setMutInfo(Map<MutatableParts, MutationBodyInfo> info);

    boolean genetic_chimerism$getCentaurSaddled();
    void genetic_chimerism$setCentaurSaddled(boolean saddled);

}
