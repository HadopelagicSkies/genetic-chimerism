package com.genetic_chimerism;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;


public class GeneticChimerismDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(MyModelGenerator::new);
		pack.addProvider(MyBlockLootTables::new);
		pack.addProvider(MyRecipeGenerator::new);
	}

	private static class MyBlockLootTables extends FabricBlockLootTableProvider {
		public MyBlockLootTables(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(dataOutput,registryLookup);
		}

		@Override
		public void generate() {
			addDrop(ModBlocks.MUTAGEN_SYNTHESIZER, drops(ModBlocks.MUTAGEN_SYNTHESIZER.asItem()));
		}
	}


	private static class MyModelGenerator extends FabricModelProvider {
		private MyModelGenerator(FabricDataOutput generator) {
			super(generator);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			blockStateModelGenerator.registerGeneric(ModBlocks.MUTAGEN_SYNTHESIZER);
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			itemModelGenerator.register(ModItems.CRUDE_TISSUE_SAMPLE, Models.GENERATED);
			itemModelGenerator.register(ModItems.FRESH_TISSUE_SAMPLE, Models.GENERATED);
			itemModelGenerator.register(ModItems.ENSOULED_TISSUE_SAMPLE, Models.GENERATED);
			itemModelGenerator.register(ModItems.MUTAGEN_VIAL, Models.GENERATED);

			itemModelGenerator.register(ModItems.IRON_SCALPEL, Models.HANDHELD);
			itemModelGenerator.register(ModItems.DIAMOND_SCALPEL, Models.HANDHELD);
			itemModelGenerator.register(ModItems.SOUL_SCALPEL, Models.HANDHELD);
			itemModelGenerator.register(ModItems.IRON_SCALPEL_HEAD, Models.GENERATED);
			itemModelGenerator.register(ModItems.DIAMOND_SCALPEL_HEAD, Models.GENERATED);
			itemModelGenerator.register(ModItems.SOUL_SCALPEL_HEAD, Models.GENERATED);
		}
	}

	private static class MyRecipeGenerator extends FabricRecipeProvider {
		public MyRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}
		@Override
		protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
			return new RecipeGenerator(registryLookup, exporter) {
				@Override
				public void generate() {
					RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

					createShaped(RecipeCategory.MISC, ModItems.IRON_SCALPEL_HEAD, 1)
							.pattern("  i")
							.pattern(" ii")
							.pattern("ii ")
							.input('i', Items.IRON_INGOT)
							.criterion(hasItem(ModItems.IRON_SCALPEL_HEAD), conditionsFromItem(ModItems.IRON_SCALPEL_HEAD))
							.offerTo(exporter);
					createShaped(RecipeCategory.MISC, ModItems.DIAMOND_SCALPEL_HEAD, 1)
							.pattern("  d")
							.pattern(" dd")
							.pattern("dd ")
							.input('d', Items.DIAMOND)
							.criterion(hasItem(ModItems.DIAMOND_SCALPEL_HEAD), conditionsFromItem(ModItems.DIAMOND_SCALPEL_HEAD))
							.offerTo(exporter);
					createShaped(RecipeCategory.MISC, ModItems.SOUL_SCALPEL_HEAD, 1)
							.pattern("  n")
							.pattern(" sn")
							.pattern("nn ")
							.input('n', Items.NETHERITE_INGOT)
							.input('s', Items.NETHER_STAR)
							.criterion(hasItem(ModItems.SOUL_SCALPEL_HEAD), conditionsFromItem(ModItems.SOUL_SCALPEL_HEAD))
							.offerTo(exporter);

					createShaped(RecipeCategory.MISC, ModItems.IRON_SCALPEL, 1)
							.pattern("  i")
							.pattern(" s ")
							.pattern("s  ")
							.input('i', ModItems.IRON_SCALPEL_HEAD)
							.input('s', Items.STICK)
							.criterion(hasItem(ModItems.IRON_SCALPEL), conditionsFromItem(ModItems.IRON_SCALPEL))
							.offerTo(exporter);
					createShaped(RecipeCategory.MISC, ModItems.DIAMOND_SCALPEL, 1)
							.pattern("  d")
							.pattern(" s ")
							.pattern("s  ")
							.input('d', ModItems.DIAMOND_SCALPEL_HEAD)
							.input('s', Items.STICK)
							.criterion(hasItem(ModItems.DIAMOND_SCALPEL), conditionsFromItem(ModItems.DIAMOND_SCALPEL))
							.offerTo(exporter);
					createShaped(RecipeCategory.MISC, ModItems.SOUL_SCALPEL, 1)
							.pattern("  n")
							.pattern(" s ")
							.pattern("s  ")
							.input('n', ModItems.SOUL_SCALPEL_HEAD)
							.input('s', Items.STICK)
							.criterion(hasItem(ModItems.SOUL_SCALPEL), conditionsFromItem(ModItems.SOUL_SCALPEL))
							.offerTo(exporter);

					createShaped(RecipeCategory.MISC, ModBlocks.MUTAGEN_SYNTHESIZER, 1)
							.pattern("bsb")
							.pattern("igi")
							.pattern("iri")
							.input('i', Items.IRON_INGOT)
							.input('g', Items.GLASS)
							.input('b', Items.GLASS_BOTTLE)
							.input('s', ItemTags.WOODEN_SLABS)
							.input('r', Items.REDSTONE_BLOCK)
							.criterion(hasItem(ModBlocks.MUTAGEN_SYNTHESIZER), conditionsFromItem(ModBlocks.MUTAGEN_SYNTHESIZER))
							.offerTo(exporter);

				}
			};
		}

		@Override
		public String getName() {
			return "MyRecipeGenerator";
		}
	}
}
