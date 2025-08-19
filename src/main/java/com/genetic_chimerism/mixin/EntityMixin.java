package com.genetic_chimerism.mixin;


import com.genetic_chimerism.mutation_setup.InvertebrateTree;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationInfo;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(Entity.class)
public class EntityMixin {
    @WrapOperation(method = {"canClimb"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean spiderClimbing(BlockState instance, TagKey tagKey, Operation<Boolean> original) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof PlayerEntity) {
            List<MutationInfo> mutations = MutationAttachments.getMutationsAttached(entity);
            if (mutations != null && (mutations.contains(MutationTrees.mutationToCodec(InvertebrateTree.wallClimb))))
                return true;
        }
        return original.call(instance, tagKey);
    }
}
