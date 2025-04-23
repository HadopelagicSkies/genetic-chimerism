package com.genetic_chimerism;

import com.genetic_chimerism.infusionblock.InfusionStation;
import com.genetic_chimerism.synthblock.BasicSynth;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class GeneticChimerismBlocks {

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(GeneticChimerismItems.GENETIC_CHIMERISM_GROUP_KEY).register((itemGroup) ->
        {
            itemGroup.add(GeneticChimerismBlocks.MUTAGEN_SYNTHESIZER.asItem());
            itemGroup.add(GeneticChimerismBlocks.INFUSION_STATION.asItem());
        });
    }

    public static Block register(Block.Settings blockSettings, String name) {
        // Register the block and its item.
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block.Settings settings = blockSettings.registryKey(key);
        Block block = new Block(settings);

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings itemSettings = new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(itemKey);
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, itemSettings));

        return Registry.register(Registries.BLOCK, key, block);
    }

    public static Block synthRegister(Block.Settings blockSettings, String name) {
        // Register the block and its item.
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block.Settings settings = blockSettings.registryKey(key);


        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings itemSettings = new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(itemKey);

        if (name.contains("advanced")) {
            BasicSynth block = new BasicSynth(settings);
            Registry.register(Registries.ITEM, itemKey, new BlockItem(block, itemSettings));
            return Registry.register(Registries.BLOCK, key, block);
        } else {
            BasicSynth block = new BasicSynth(settings);
            Registry.register(Registries.ITEM, itemKey, new BlockItem(block, itemSettings));
            return Registry.register(Registries.BLOCK, key, block);
        }
    }

    public static Block infusionRegister(Block.Settings blockSettings, String name) {
        // Register the block and its item.
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block.Settings settings = blockSettings.registryKey(key);
        Block block = new InfusionStation(settings);

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings itemSettings = new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(itemKey);
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, itemSettings));

        return Registry.register(Registries.BLOCK, key, block);
    }

    public static final Block MUTAGEN_SYNTHESIZER = synthRegister(Block.Settings.create(), "mutagen_synthesizer");
    public static final Block INFUSION_STATION = infusionRegister(Block.Settings.create(), "infusion_station");
    public static final Block HIVE_SCAFFOLD = register(Block.Settings.create(), "hive_scaffold");
}