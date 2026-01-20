package com.genetic_chimerism.mixin.client;

import com.genetic_chimerism.MutatableParts;
import com.genetic_chimerism.PlayerRenderStateAccess;
import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(PlayerEntityRenderState.class)
public class PlayerRenderStateMixin implements PlayerRenderStateAccess
{
    private @Unique Map<MutatableParts, MutationBodyInfo> mutInfo;
    private @Unique boolean saddled;

    @Override
    public Map<MutatableParts, MutationBodyInfo> genetic_chimerism$getMutInfo() {
        return mutInfo;
    }

    @Override
    public void genetic_chimerism$setMutInfo(Map<MutatableParts, MutationBodyInfo> info) {
        mutInfo = info;
    }

    @Override
    public boolean genetic_chimerism$getSaddled() {return saddled;}

    @Override
    public void genetic_chimerism$setSaddled(boolean saddledBool) {saddled = saddledBool;}
}

