package com.genetic_chimerism.mixin;


import com.genetic_chimerism.mutation_setup.InvertebrateTree;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import net.minecraft.entity.passive.BeeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(BeeEntity.class)
public class BeeEntityMixin {
    @Inject(at = @At("HEAD"), method = "setAngryAt", cancellable = true)
    private void ignoreAngry(UUID uuid, CallbackInfo info) {
        BeeEntity bee = (BeeEntity) (Object) this;
        if(bee.getWorld().getPlayerByUuid(uuid) != null && MutationAttachments.getMutationsAttached(bee.getWorld().getPlayerByUuid(uuid)).contains(MutationTrees.mutationToCodec(InvertebrateTree.hivePheromones))){
            info.cancel();
        }
    }
}
