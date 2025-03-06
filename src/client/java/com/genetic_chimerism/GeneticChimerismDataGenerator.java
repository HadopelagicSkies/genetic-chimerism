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
			addDrop(GeneticChimerismBlocks.MUTAGEN_SYNTHESIZER, drops(GeneticChimerismBlocks.MUTAGEN_SYNTHESIZER.asItem()));
		}
	}


	private static class MyModelGenerator extends FabricModelProvider {
		private MyModelGenerator(FabricDataOutput generator) {
			super(generator);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			blockStateModelGenerator.registerGeneric(GeneticChimerismBlocks.MUTAGEN_SYNTHESIZER);
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			itemModelGenerator.register(GeneticChimerismItems.CRUDE_TISSUE_SAMPLE, Models.GENERATED);
			itemModelGenerator.register(GeneticChimerismItems.FRESH_TISSUE_SAMPLE, Models.GENERATED);
			itemModelGenerator.register(GeneticChimerismItems.ENSOULED_TISSUE_SAMPLE, Models.GENERATED);
			itemModelGenerator.register(GeneticChimerismItems.MUTAGEN_VIAL, Models.GENERATED);

			itemModelGenerator.register(GeneticChimerismItems.IRON_SCALPEL, Models.HANDHELD);
			itemModelGenerator.register(GeneticChimerismItems.DIAMOND_SCALPEL, Models.HANDHELD);
			itemModelGenerator.register(GeneticChimerismItems.SOUL_SCALPEL, Models.HANDHELD);
			itemModelGenerator.register(GeneticChimerismItems.IRON_SCALPEL_HEAD, Models.GENERATED);
			itemModelGenerator.register(GeneticChimerismItems.DIAMOND_SCALPEL_HEAD, Models.GENERATED);
			itemModelGenerator.register(GeneticChimerismItems.SOUL_SCALPEL_HEAD, Models.GENERATED);
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

					createShaped(RecipeCategory.MISC, GeneticChimerismItems.IRON_SCALPEL_HEAD, 1)
							.pattern("  i")
							.pattern(" ii")
							.pattern("ii ")
							.input('i', Items.IRON_INGOT)
							.criterion(hasItem(GeneticChimerismItems.IRON_SCALPEL_HEAD), conditionsFromItem(GeneticChimerismItems.IRON_SCALPEL_HEAD))
							.offerTo(exporter);
					createShaped(RecipeCategory.MISC, GeneticChimerismItems.DIAMOND_SCALPEL_HEAD, 1)
							.pattern("  d")
							.pattern(" dd")
							.pattern("dd ")
							.input('d', Items.DIAMOND)
							.criterion(hasItem(GeneticChimerismItems.DIAMOND_SCALPEL_HEAD), conditionsFromItem(GeneticChimerismItems.DIAMOND_SCALPEL_HEAD))
							.offerTo(exporter);
					createShaped(RecipeCategory.MISC, GeneticChimerismItems.SOUL_SCALPEL_HEAD, 1)
							.pattern("  n")
							.pattern(" sn")
							.pattern("nn ")
							.input('n', Items.NETHERITE_INGOT)
							.input('s', Items.NETHER_STAR)
							.criterion(hasItem(GeneticChimerismItems.SOUL_SCALPEL_HEAD), conditionsFromItem(GeneticChimerismItems.SOUL_SCALPEL_HEAD))
							.offerTo(exporter);

					createShaped(RecipeCategory.MISC, GeneticChimerismItems.IRON_SCALPEL, 1)
							.pattern("  i")
							.pattern(" s ")
							.pattern("s  ")
							.input('i', GeneticChimerismItems.IRON_SCALPEL_HEAD)
							.input('s', Items.STICK)
							.criterion(hasItem(GeneticChimerismItems.IRON_SCALPEL), conditionsFromItem(GeneticChimerismItems.IRON_SCALPEL))
							.offerTo(exporter);
					createShaped(RecipeCategory.MISC, GeneticChimerismItems.DIAMOND_SCALPEL, 1)
							.pattern("  d")
							.pattern(" s ")
							.pattern("s  ")
							.input('d', GeneticChimerismItems.DIAMOND_SCALPEL_HEAD)
							.input('s', Items.STICK)
							.criterion(hasItem(GeneticChimerismItems.DIAMOND_SCALPEL), conditionsFromItem(GeneticChimerismItems.DIAMOND_SCALPEL))
							.offerTo(exporter);
					createShaped(RecipeCategory.MISC, GeneticChimerismItems.SOUL_SCALPEL, 1)
							.pattern("  n")
							.pattern(" s ")
							.pattern("s  ")
							.input('n', GeneticChimerismItems.SOUL_SCALPEL_HEAD)
							.input('s', Items.STICK)
							.criterion(hasItem(GeneticChimerismItems.SOUL_SCALPEL), conditionsFromItem(GeneticChimerismItems.SOUL_SCALPEL))
							.offerTo(exporter);

					createShaped(RecipeCategory.MISC, GeneticChimerismBlocks.MUTAGEN_SYNTHESIZER, 1)
							.pattern("bsb")
							.pattern("igi")
							.pattern("iri")
							.input('i', Items.IRON_INGOT)
							.input('g', Items.GLASS)
							.input('b', Items.GLASS_BOTTLE)
							.input('s', ItemTags.WOODEN_SLABS)
							.input('r', Items.REDSTONE_BLOCK)
							.criterion(hasItem(GeneticChimerismBlocks.MUTAGEN_SYNTHESIZER), conditionsFromItem(GeneticChimerismBlocks.MUTAGEN_SYNTHESIZER))
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
