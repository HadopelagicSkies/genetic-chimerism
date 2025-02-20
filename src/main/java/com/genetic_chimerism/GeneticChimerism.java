package com.genetic_chimerism;

import com.genetic_chimerism.mutationsetup.Mutation;
import com.genetic_chimerism.mutationsetup.MutationAttachments;
import com.genetic_chimerism.mutationsetup.MutationTrees;
import com.genetic_chimerism.synthblock.SynthRecipe;
import com.genetic_chimerism.synthblock.SynthRecipeSerializer;
import com.genetic_chimerism.synthblock.SynthScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneticChimerism implements ModInitializer {
	public static final String MOD_ID = "genetic_chimerism";

	public static final Identifier INITIAL_SYNC = Identifier.of(MOD_ID, "initial_sync");
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ScreenHandlerType<SynthScreenHandler> SYNTH_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(GeneticChimerism.MOD_ID, "mutagen_synthesizer"), new ScreenHandlerType<>(SynthScreenHandler::new, FeatureSet.empty()));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.initialize();
		ModBlocks.initialize();
		ModComponents.initialize();
		ModBlockEntities.initialize();
		MutationTrees.initialize();
		MutationAttachments.initialize();
		MobInfoReloadListener.initialize();

		Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(GeneticChimerism.MOD_ID, SynthRecipeSerializer.ID), SynthRecipeSerializer.INSTANCE);
		Registry.register(Registries.RECIPE_TYPE, Identifier.of(GeneticChimerism.MOD_ID, SynthRecipe.Type.ID), SynthRecipe.Type.INSTANCE);

		//StateSaverLoader.initialize();

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