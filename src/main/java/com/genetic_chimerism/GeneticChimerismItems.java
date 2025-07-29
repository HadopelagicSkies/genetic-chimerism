package com.genetic_chimerism;



import com.genetic_chimerism.mutation_setup.MutationInfo;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;


public class GeneticChimerismItems {

    public static final RegistryKey<ItemGroup> GENETIC_CHIMERISM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(GeneticChimerism.MOD_ID, "item_group"));
    public static final ItemGroup GENETIC_CHIMERISM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(GeneticChimerismItems.ENSOULED_TISSUE_SAMPLE))
            .displayName(Text.translatable("itemGroup.genetic_chimerism"))
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, GENETIC_CHIMERISM_GROUP_KEY, GENETIC_CHIMERISM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(GENETIC_CHIMERISM_GROUP_KEY)
                .register((itemGroup) -> {
                    itemGroup.add(GeneticChimerismItems.CRUDE_TISSUE_SAMPLE);
                    itemGroup.add(GeneticChimerismItems.FRESH_TISSUE_SAMPLE);
                    itemGroup.add(GeneticChimerismItems.ENSOULED_TISSUE_SAMPLE);

                    itemGroup.add(GeneticChimerismItems.IRON_SCALPEL);
                    itemGroup.add(GeneticChimerismItems.DIAMOND_SCALPEL);
                    itemGroup.add(GeneticChimerismItems.SOUL_SCALPEL);
                    itemGroup.add(GeneticChimerismItems.IRON_SCALPEL_HEAD);
                    itemGroup.add(GeneticChimerismItems.DIAMOND_SCALPEL_HEAD);
                    itemGroup.add(GeneticChimerismItems.SOUL_SCALPEL_HEAD);

                    itemGroup.add(GeneticChimerismItems.MUTAGEN_VIAL);

                    itemGroup.add(GeneticChimerismItems.PORCUPINE_QUILL);

                });
    }

    private static <T extends Item> T register(Function<Item.Settings, T> constructor, Item.Settings itemSettings, String name) {
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);

        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = itemSettings.registryKey(key);

        return Registry.register(Registries.ITEM, key, constructor.apply(settings));
    }

    private static Item register(Item.Settings itemSettings, String name) {
        return register(Item::new,itemSettings,name);
    }

    private static Item scalpelRegister(ToolMaterial mat, float atkDamage, float atkSpeed, Item.Settings itemSettings, String name) {

        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);

        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = itemSettings.registryKey(key);

        return Registry.register(Registries.ITEM, key, new ScalpelItem(mat,atkDamage,atkSpeed,settings));
    }

    public static final Item CRUDE_TISSUE_SAMPLE = register(TissueItem::new,
            new Item.Settings().component(GeneticChimerismComponents.TISSUE_TYPE,""),
            "crude_tissue_sample"
    );

    public static final Item FRESH_TISSUE_SAMPLE = register(TissueItem::new,
            new Item.Settings().component(GeneticChimerismComponents.TISSUE_TYPE,""),
            "fresh_tissue_sample"
    );

    public static final Item ENSOULED_TISSUE_SAMPLE = register(TissueItem::new,
            new Item.Settings().component(GeneticChimerismComponents.TISSUE_TYPE,""),
            "ensouled_tissue_sample"
    );

    public static final Item IRON_SCALPEL = scalpelRegister(
            ToolMaterial.IRON,0F,-2.8F,new Item.Settings(),
            "iron_scalpel"
    );

    public static final Item DIAMOND_SCALPEL = scalpelRegister(
            ToolMaterial.DIAMOND,0F,-2.8F,new Item.Settings(),
            "diamond_scalpel"
    );

    public static final Item SOUL_SCALPEL = scalpelRegister(
            ToolMaterial.NETHERITE,0F,-2.8F,new Item.Settings(),
            "soul_scalpel"
    );
    public static final Item IRON_SCALPEL_HEAD = register(
            new Item.Settings(),
            "iron_scalpel_head"
    );

    public static final Item DIAMOND_SCALPEL_HEAD = register(
            new Item.Settings(),
            "diamond_scalpel_head"
    );

    public static final Item SOUL_SCALPEL_HEAD = register(
            new Item.Settings(),
            "soul_scalpel_head"
    );

    public static final Item MUTAGEN_VIAL = register(MutagenVialItem::new,
            new Item.Settings().maxCount(1).component(GeneticChimerismComponents.MUTATION_STORED,new MutationInfo("","")),
            "mutagen_vial"
    );

    public static final Item PORCUPINE_QUILL = register(
            new Item.Settings().maxCount(1).component(GeneticChimerismComponents.MUTATION_STORED,new MutationInfo("","")),
            "porcupine_quill"
    );




}