package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup_client.AmphibiousTreeClient;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import com.genetic_chimerism.packet_payloads.SetAnimPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.joml.Vector3f;

public class MutationEntityModel extends EntityModel<PlayerEntityRenderState> {
    private final MutatableParts part;
    private final boolean needsMirroring;

    private static final float firstPersonArmScale =1.5F;
    public static final Animation firstPersonArmOffset = Animation.Builder.create(0.0F)
            .addBoneAnimation("bone", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-5.0F, -7.5F, 0.0F), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("bone", new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0.0F, AnimationHelper.createScalingVector(firstPersonArmScale, firstPersonArmScale, firstPersonArmScale), Transformation.Interpolations.LINEAR)))
            .build();

    public MutationEntityModel(ModelPart root, MutatableParts part, boolean needsMirroring) {
        super(root);
        this.part=part;
        this.needsMirroring=needsMirroring;
    }

    @Override
    public void setAngles(PlayerEntityRenderState state) {
        PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
        MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(part);
        if(mutInfo != null) {
            MutationClient mutation = MutationTreesClient.mutationFromCodec(mutInfo);
            Animation animation = mutation.getAnimation("part");
            Animation growthAnimation = mutation.getAnimation("growth");
            Animation actionAnimation = mutation.getAnimation("action");

            boolean fastTailSpeed = (state.isSwimming || state.isGliding) && part == MutatableParts.TAIL;

            if (animation != null && (!mutInfo.actionAnim().isRunning() || mutation == AmphibiousTreeClient.tadpoleTail) && (double) mutInfo.growth() /mutation.getMaxGrowth() > 0.2) {
                if(!needsMirroring)
                    this.animate(mutInfo.partAnim(),animation,state.age, fastTailSpeed ? 1:0.5f);
                else this.animate(mutInfo.partAnim(), CustomAnimationHelper.mirrorAnimationX(animation),state.age, fastTailSpeed ? 1:0.5f);
            }
            else if (actionAnimation != null && (mutInfo.actionAnim().isRunning() && mutation != AmphibiousTreeClient.tadpoleTail) && (double) mutInfo.growth() /mutation.getMaxGrowth() > 0.2){
                if(!needsMirroring)
                    this.animate(mutInfo.actionAnim(),actionAnimation,state.age,2);
                else this.animate(mutInfo.actionAnim(), CustomAnimationHelper.mirrorAnimationX(actionAnimation),state.age,2);

                if(mutInfo.actionAnim().getTimeInMilliseconds(state.age) >= actionAnimation.lengthInSeconds()/1000) {
                    mutInfo.actionAnim().stop();
                    ClientPlayNetworking.send(new SetAnimPayload(part,false));
                }
            }
            else if (actionAnimation != null && (mutInfo.actionAnim().isRunning() && needsMirroring && mutation == AmphibiousTreeClient.tadpoleTail) && (double) mutInfo.growth() /mutation.getMaxGrowth() > 0.2){
                //yes i know it doesnt actually need mirroring, im just using it for extra info to differentiate the two runs with tadpoleTail
                if(mutInfo.actionAnim().getTimeInMilliseconds(state.age) >= actionAnimation.lengthInSeconds()/1000) {
                    mutInfo.actionAnim().stop();
                    ClientPlayNetworking.send(new SetAnimPayload(part,false));
                }
            }
            if (growthAnimation != null) {
                if (part != MutatableParts.LEG) {
                    if (!needsMirroring)
                        AnimationHelper.animate(this, growthAnimation, (long) ((float) mutInfo.growth() / mutation.getMaxGrowth() * 1000F), 1, new Vector3f(0, 0, 0));
                    else
                        AnimationHelper.animate(this, CustomAnimationHelper.mirrorAnimationX(growthAnimation), (long) ((float) mutInfo.growth() / mutation.getMaxGrowth() * 1000F), 1, new Vector3f(0, 0, 0));
                }
                else {
                    if (!needsMirroring && (double) mutInfo.growth() / mutation.getMaxGrowth() <= 0.5)
                        AnimationHelper.animate(this, growthAnimation, (long) ((float) mutInfo.growth() / mutation.getMaxGrowth() * 1000F) * 2, 1, new Vector3f(0, 0, 0));
                    else if (needsMirroring && (double) mutInfo.growth() / mutation.getMaxGrowth() > 0.5)
                        AnimationHelper.animate(this, CustomAnimationHelper.mirrorAnimationX(growthAnimation), (long) ((float) mutInfo.growth() / mutation.getMaxGrowth() * 1000F), 1, new Vector3f(0, 0, 0));
                    else if (needsMirroring){
                        AnimationHelper.animate(this, CustomAnimationHelper.mirrorAnimationX(growthAnimation), 0, 1, new Vector3f(0, 0, 0));
                    }
                }
            }
        }
    }
}
