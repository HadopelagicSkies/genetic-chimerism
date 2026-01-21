package com.genetic_chimerism;

import com.genetic_chimerism.blocks.infusionblock.InfusionStation;
import com.genetic_chimerism.blocks.synthblock.synthblock.BasicSynth;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;


public class GeneticChimerismBlocks {

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(GeneticChimerismItems.GENETIC_CHIMERISM_GROUP_KEY).register((itemGroup) ->
        {
            itemGroup.add(GeneticChimerismBlocks.MUTAGEN_SYNTHESIZER.asItem());
            itemGroup.add(GeneticChimerismBlocks.INFUSION_STATION.asItem());
        });
    }

    public static <T extends Block> T register(Function<Block.Settings, T> constructor, Block.Settings blockSettings, String name) {
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block.Settings settings = blockSettings.registryKey(key);
        T block = constructor.apply(settings);

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings itemSettings = new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(itemKey);
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, itemSettings));

        return Registry.register(Registries.BLOCK, key, block);
    }

    private static Block register(Block.Settings blockSettings, String name) {
        return register(Block::new,blockSettings,name);
    }


    public static final Block MUTAGEN_SYNTHESIZER = register(BasicSynth::new,Block.Settings.create(), "mutagen_synthesizer");
    public static final Block INFUSION_STATION = register(InfusionStation::new,Block.Settings.create(), "infusion_station");
    public static final Block HIVE_SCAFFOLD = register(Block.Settings.create(), "hive_scaffold");
}