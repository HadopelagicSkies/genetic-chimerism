package com.genetic_chimerism.mixin;

import com.genetic_chimerism.GeneticChimerismEntities;
import com.genetic_chimerism.entity.DiplocaulusEntity;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin (Bucketable.class)
public interface BucketableMixin {
    @Inject(at = @At ("HEAD"), method = "tryBucket", cancellable = true)
    private static <T extends LivingEntity & Bucketable> void noDiplocaulusBucket(PlayerEntity player, Hand hand, T entity, CallbackInfoReturnable<Optional<ActionResult>> cir) {
        if(entity instanceof DiplocaulusEntity) cir.setReturnValue(Optional.empty());
    }
}
