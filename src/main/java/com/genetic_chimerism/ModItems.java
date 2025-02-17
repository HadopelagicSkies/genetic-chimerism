package com.genetic_chimerism;



import com.genetic_chimerism.mutationsetup.MutationInfo;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItems {

    public static final RegistryKey<ItemGroup> GENETIC_CHIMERISM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(GeneticChimerism.MOD_ID, "item_group"));
    public static final ItemGroup GENETIC_CHIMERISM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ENSOULED_TISSUE_SAMPLE))
            .displayName(Text.translatable("itemGroup.genetic_chimerism"))
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, GENETIC_CHIMERISM_GROUP_KEY, GENETIC_CHIMERISM_GROUP);

        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our items to the ingredients group.
        ItemGroupEvents.modifyEntriesEvent(GENETIC_CHIMERISM_GROUP_KEY)
                .register((itemGroup) -> {
                    itemGroup.add(ModItems.CRUDE_TISSUE_SAMPLE);
                    itemGroup.add(ModItems.FRESH_TISSUE_SAMPLE);
                    itemGroup.add(ModItems.ENSOULED_TISSUE_SAMPLE);

                    itemGroup.add(ModItems.IRON_SCALPEL);
                    itemGroup.add(ModItems.DIAMOND_SCALPEL);
                    itemGroup.add(ModItems.SOUL_SCALPEL);
                    itemGroup.add(ModItems.IRON_SCALPEL_HEAD);
                    itemGroup.add(ModItems.DIAMOND_SCALPEL_HEAD);
                    itemGroup.add(ModItems.SOUL_SCALPEL_HEAD);

                    itemGroup.add(ModItems.MUTAGEN_VIAL);

                });
    }

    private static Item register(Item.Settings itemSettings, String name) {
        // Create the identifier for the item.
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);

        // Register the item key.
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = itemSettings.registryKey(key);

        // Return the registered item!
        return Registry.register(Registries.ITEM, key, new Item(settings));
    }

    private static Item scalpelRegister(ToolMaterial mat, float atkDamage, float atkSpeed, Item.Settings itemSettings, String name) {
        // Create the identifier for the item.
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);

        // Register the item key.
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = itemSettings.registryKey(key);

        // Return the registered item!
        return Registry.register(Registries.ITEM, key, new ScalpelItem(mat,atkDamage,atkSpeed,settings));
    }
    private static Item tissueRegister(Item.Settings itemSettings, String name) {
        // Create the identifier for the item.
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);

        // Register the item key.
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = itemSettings.registryKey(key);

        // Return the registered item!
        return Registry.register(Registries.ITEM, key, new TissueItem(settings.component(ModComponents.TISSUE_TYPE,"")));
    }

    private static Item vialRegister(Item.Settings itemSettings, String name) {
        // Create the identifier for the item.
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);

        // Register the item key.
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = itemSettings.registryKey(key);

        // Return the registered item!
        return Registry.register(Registries.ITEM, key, new MutagenVialItem(settings.component(ModComponents.MUTATION_STORED,new MutationInfo("",""))));
    }

    public static final Item CRUDE_TISSUE_SAMPLE = tissueRegister(
            new Item.Settings(),
            "crude_tissue_sample"
    );

    public static final Item FRESH_TISSUE_SAMPLE = tissueRegister(
            new Item.Settings(),
            "fresh_tissue_sample"
    );

    public static final Item ENSOULED_TISSUE_SAMPLE = tissueRegister(
            new Item.Settings(),
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

    public static final Item MUTAGEN_VIAL = vialRegister(
            new Item.Settings().maxCount(1),
            "mutagen_vial"
    );




}