package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.Mutation;
import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import com.genetic_chimerism.packet_payloads.MutActionPayload;
import com.genetic_chimerism.packet_payloads.PartRecolorPayload;
import com.genetic_chimerism.packet_payloads.SetAnimPayload;
import com.genetic_chimerism.synthblock.SynthRecipe;
import com.genetic_chimerism.synthblock.SynthRecipeSerializer;
import com.genetic_chimerism.synthblock.SynthScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneticChimerism implements ModInitializer {
	public static final String MOD_ID = "genetic_chimerism";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ScreenHandlerType<SynthScreenHandler> SYNTH_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(GeneticChimerism.MOD_ID, "mutagen_synthesizer"), new ScreenHandlerType<>(SynthScreenHandler::new, FeatureSet.empty()));

	@Override
	public void onInitialize() {
		GeneticChimerismItems.initialize();
		GeneticChimerismBlocks.initialize();
		GeneticChimerismComponents.initialize();
		GeneticChimerismBlockEntities.initialize();
		MutationTrees.initialize();
		MutationAttachments.initialize();
		MobInfoReloadListener.initialize();
		GeneticChimerismEntities.initialize();


		Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(GeneticChimerism.MOD_ID, SynthRecipeSerializer.ID), SynthRecipeSerializer.INSTANCE);
		Registry.register(Registries.RECIPE_TYPE, Identifier.of(GeneticChimerism.MOD_ID, SynthRecipe.Type.ID), SynthRecipe.Type.INSTANCE);

		PayloadTypeRegistry.playC2S().register(MutActionPayload.ID, MutActionPayload.MUT_ACTION_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(MutActionPayload.ID, (payload, context) -> {
			if (payload.isPressed()) {
				MutationBodyInfo mutationInfo = MutationAttachments.getPartAttached(context.player(), payload.keyPressed());
				if (context.player() instanceof ServerPlayerEntity && mutationInfo != null) {
					Mutation mutation = MutationTrees.mutationFromCodec(mutationInfo);
					if (mutation != null && mutationInfo.growth() >= mutation.getMaxGrowth()) {
						mutation.mutationAction(context.player());
					}
				}
			}
		});

		PayloadTypeRegistry.playC2S().register(PartRecolorPayload.ID, PartRecolorPayload.PART_RECOLOR_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(PartRecolorPayload.ID, (payload, context) -> {
			MutationBodyInfo mutationInfo = MutationAttachments.getPartAttached(context.player(), payload.part());
			if (mutationInfo != null) {
				Mutation mutation = MutationTrees.mutationFromCodec(mutationInfo);
				if (mutation != null) {
					MutationAttachments.setPartVisuals(context.player(), payload.part(),payload.patternIndex(), payload.color1(), payload.color2());
				}
			}
		});

		PayloadTypeRegistry.playC2S().register(SetAnimPayload.ID, SetAnimPayload.SET_ANIM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(SetAnimPayload.ID, (payload, context) -> {
			MutationBodyInfo mutationInfo = MutationAttachments.getPartAttached(context.player(), payload.part());
			if (mutationInfo != null) {
				Mutation mutation = MutationTrees.mutationFromCodec(mutationInfo);
				if (mutation != null) {
					MutationAttachments.setPartAnimating(context.player(), payload.part(),payload.isAnimating(),context.player().age);
				}
			}
		});

		ServerWorldEvents.LOAD.register((server, serverWorld) -> {
			GeneticChimerism.LOGGER.info("Assigning Synth Recipes");
			MutationTrees.assignRecipes(serverWorld);
			GeneticChimerism.LOGGER.info("Mapping Mob Tissue Data");
			MobInfoReloadListener.remapResults();
		});

		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
			if (!success) return;
			GeneticChimerism.LOGGER.info("Assigning Synth Recipes");
			MutationTrees.assignRecipes(server.getOverworld());
			GeneticChimerism.LOGGER.info("Mapping Mob Tissue Data");
			MobInfoReloadListener.remapResults();
		});
		LOGGER.info("Genetic Chimerism Loaded");
	}
}
