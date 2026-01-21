package com.genetic_chimerism;



import com.genetic_chimerism.items.BardingItem;
import com.genetic_chimerism.items.MutagenVialItem;
import com.genetic_chimerism.items.ScalpelItem;
import com.genetic_chimerism.items.TissueItem;
import com.genetic_chimerism.mutation_setup.MutationInfo;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
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

                    itemGroup.add(GeneticChimerismItems.LEATHER_CENTAUR_BARDING);
                    //itemGroup.add(GeneticChimerismItems.COPPER_CENTAUR_BARDING);
                    itemGroup.add(GeneticChimerismItems.IRON_CENTAUR_BARDING);
                    itemGroup.add(GeneticChimerismItems.GOLDEN_CENTAUR_BARDING);
                    itemGroup.add(GeneticChimerismItems.DIAMOND_CENTAUR_BARDING);
                    //itemGroup.add(GeneticChimerismItems.NETHERITE_CENTAUR_BARDING);



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
            new Item.Settings(),
            "porcupine_quill"
    );

    public static final BardingItem LEATHER_CENTAUR_BARDING = register(settings -> new BardingItem(BardingItem.LEATHER_BARDING, EquipmentType.LEGGINGS, settings),
            new Item.Settings().maxCount(1),
            "leather_centaur_barding"
    );

//    public static final BardingItem COPPER_CENTAUR_BARDING = register(settings -> new BardingItem(BardingItem.COPPER_BARDING, EquipmentType.LEGGINGS, settings),
//            new Item.Settings().maxCount(1),
//            "copper_centaur_barding"
//    );

    public static final BardingItem IRON_CENTAUR_BARDING = register(settings -> new BardingItem(BardingItem.IRON_BARDING, EquipmentType.LEGGINGS, settings),
            new Item.Settings().maxCount(1),
            "iron_centaur_barding"
    );

    public static final BardingItem GOLDEN_CENTAUR_BARDING = register(settings -> new BardingItem(BardingItem.GOLD_BARDING, EquipmentType.LEGGINGS, settings),
            new Item.Settings().maxCount(1),
            "golden_centaur_barding"
    );

    public static final BardingItem DIAMOND_CENTAUR_BARDING = register(settings -> new BardingItem(BardingItem.DIAMOND_BARDING, EquipmentType.LEGGINGS, settings),
            new Item.Settings().maxCount(1),
            "diamond_centaur_barding"
    );

//    public static final BardingItem NETHERITE_CENTAUR_BARDING = register(settings -> new BardingItem(BardingItem.NETHERITE_BARDING, EquipmentType.LEGGINGS, settings),
//            new Item.Settings().maxCount(1),
//            "netherite_centaur_barding"
//    );



}