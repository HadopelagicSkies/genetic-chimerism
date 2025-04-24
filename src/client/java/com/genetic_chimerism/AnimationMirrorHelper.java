package com.genetic_chimerism;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;

public class AnimationMirrorHelper {
    public static Animation mirrorAnimationX(Animation originalAnimation){
        Animation.Builder newAnimation = Animation.Builder.create(originalAnimation.lengthInSeconds());
        Map<String, List<Transformation>> boneTransformMap = originalAnimation.boneAnimations();

        for(String keyName :boneTransformMap.keySet()){
            for(Transformation transform: boneTransformMap.get(keyName)){
                Keyframe[] oldKeyframes = transform.keyframes();
                Keyframe[] newKeyframes = new Keyframe[oldKeyframes.length];
                for (int i = 0; i < oldKeyframes.length; i++) {
                    Keyframe oldKF = oldKeyframes[i];

                    float oldX = oldKF.target().x;
                    float oldY = oldKF.target().y;
                    float oldZ = oldKF.target().z;

                    if(transform.target() == Transformation.Targets.ROTATE) {
                        newKeyframes[i] = new Keyframe(oldKF.timestamp(), new Vector3f(oldX,-1*oldY,-1*oldZ), oldKF.interpolation());
                    }else if(transform.target() == Transformation.Targets.TRANSLATE) {
                        newKeyframes[i] = new Keyframe(oldKF.timestamp(), new Vector3f(-1*oldX,oldY,oldZ), oldKF.interpolation());
                    }else if(transform.target() == Transformation.Targets.SCALE) {
                        newKeyframes[i] = new Keyframe(oldKF.timestamp(), new Vector3f(-1*oldX,oldY,oldZ), oldKF.interpolation());
                    }
                }
                newAnimation.addBoneAnimation(keyName, new Transformation(transform.target(),newKeyframes));
            }
        }
        return newAnimation.build();
    }
}
