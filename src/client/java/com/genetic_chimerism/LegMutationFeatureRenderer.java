package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup_client.HoovedTreeClient;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.joml.Vector3f;

public class LegMutationFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {

    public LegMutationFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        if (this.getContextModel().body.visible) {
            PlayerRenderStateAccess accessedState = (PlayerRenderStateAccess) state;
            MutationBodyInfo mutInfo = accessedState.genetic_chimerism$getMutInfo().get(MutatableParts.LEG);
            if (mutInfo != null) {
                MutationClient mutation = MutationTreesClient.mutationFromCodec(mutInfo);
                if (!(mutation instanceof HoovedTreeClient.CentaurMutation)) {
                    Animation mirrorAnimation = mutation.getAnimation("mirror");

                    this.getContextModel().rightLeg.hidden = true;
                    this.getContextModel().rightPants.hidden = true;
                    if ((double) mutInfo.growth() / mutation.getMaxGrowth() <= 0.5) {
                        this.getContextModel().leftLeg.hidden = false;
                        this.getContextModel().leftPants.hidden = false;
                    } else {
                        this.getContextModel().leftLeg.hidden = true;
                        this.getContextModel().leftPants.hidden = true;
                    }

                    ModelPart modelR = mutation.getModelData().createModel();
                    modelR.copyTransform(this.getContextModel().rightLeg);
                    MutationEntityModel entityModelR = new MutationEntityModel(modelR, MutatableParts.LEG, false);
                    entityModelR.setAngles(state);

                    ModelPart modelL = mutation.getModelData().createModel();
                    modelL.copyTransform(this.getContextModel().leftLeg);
                    MutationEntityModel entityModelL = new MutationEntityModel(modelL, MutatableParts.LEG, true);
                    entityModelL.setAngles(state);

                    if (mirrorAnimation != null) {
                        AnimationHelper.animate(entityModelL, mirrorAnimation, 0, 1, new Vector3f(0, 0, 0));
                    }

                    matrices.push();
                    VertexConsumer vertexConsumerL1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(mutation.getTexture1()));
                    entityModelL.render(matrices, vertexConsumerL1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255, mutInfo.color1()));
                    VertexConsumer vertexConsumerL2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(mutation.getTexture2()));
                    entityModelL.render(matrices, vertexConsumerL2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255, mutInfo.color2()));
                    VertexConsumer vertexConsumerR1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(mutation.getTexture1()));
                    entityModelR.render(matrices, vertexConsumerR1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255, mutInfo.color1()));
                    VertexConsumer vertexConsumerR2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(mutation.getTexture2()));
                    entityModelR.render(matrices, vertexConsumerR2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255, mutInfo.color2()));
                    matrices.pop();

                } else {
                    this.getContextModel().rightLeg.hidden = true;
                    this.getContextModel().rightPants.hidden = true;
                    this.getContextModel().leftLeg.hidden = true;
                    this.getContextModel().leftPants.hidden = true;

                    ModelPart centaur = mutation.getModelData().createModel();
                    centaur.translate(new Vector3f(0f,9f,10f));
                    centaur.getChild("left_front_leg").setAngles(this.getContextModel().leftLeg.pitch,this.getContextModel().leftLeg.yaw,this.getContextModel().leftLeg.roll);
                    centaur.getChild("right_front_leg").setAngles(this.getContextModel().rightLeg.pitch,this.getContextModel().rightLeg.yaw,this.getContextModel().rightLeg.roll);
                    centaur.getChild("right_hind_leg").setAngles(this.getContextModel().leftLeg.pitch,this.getContextModel().leftLeg.yaw,this.getContextModel().leftLeg.roll);
                    centaur.getChild("left_hind_leg").setAngles(this.getContextModel().rightLeg.pitch,this.getContextModel().rightLeg.yaw,this.getContextModel().rightLeg.roll);
                    centaur.getChild("body").getChild("saddle").hidden = !accessedState.genetic_chimerism$getCentaurSaddled();

                    ArmorMaterial armor = MutationAttachments.materialFromHorseArmor(accessedState.genetic_chimerism$getCentaurArmor());

                    matrices.push();
                    VertexConsumer vertexConsumer1 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(mutation.getTexture1()));
                    centaur.render(matrices, vertexConsumer1, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255, mutInfo.color1()));
                    VertexConsumer vertexConsumer2 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(mutation.getTexture2()));
                    centaur.render(matrices, vertexConsumer2, light, OverlayTexture.DEFAULT_UV, ColorHelper.withAlpha(255, mutInfo.color2()));
                    if(armor != ArmorMaterials.CHAIN){
                        VertexConsumer vertexConsumer3 = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(Identifier.ofVanilla("textures/entity/equipment/horse_body/"+armor.assetId().getValue().toString().replace("minecraft:","")+ ".png")));
                        centaur.render(matrices, vertexConsumer3, light, OverlayTexture.DEFAULT_UV);
                    }
                    matrices.pop();
                }
            } else {
                this.getContextModel().rightLeg.hidden = false;
                this.getContextModel().rightPants.hidden = false;
                this.getContextModel().leftLeg.hidden = false;
                this.getContextModel().leftPants.hidden = false;
            }

        }
    }
}
