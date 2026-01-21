package com.genetic_chimerism.items;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;

public class BardingItem extends ArmorItem {

    public static final ArmorMaterial LEATHER_BARDING = new ArmorMaterial(5, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 2);
        map.put(EquipmentType.LEGGINGS, 3);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAssetKeys.LEATHER);
//    public static final ArmorMaterial COPPER_BARDING = new ArmorMaterial(15, Util.make(new EnumMap(EquipmentType.class), map -> {
//        map.put(EquipmentType.BOOTS, 3);
//        map.put(EquipmentType.LEGGINGS, 4);
//    }), 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, ItemTags.REPAIRS_CHAIN_ARMOR, EquipmentAssetKeys.COPPER);
    public static final ArmorMaterial IRON_BARDING = new ArmorMaterial(15, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 5);
        map.put(EquipmentType.LEGGINGS, 6);
    }), 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, EquipmentAssetKeys.IRON);
    public static final ArmorMaterial GOLD_BARDING = new ArmorMaterial(7, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 3);
        map.put(EquipmentType.LEGGINGS, 5);
    }), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, ItemTags.REPAIRS_GOLD_ARMOR, EquipmentAssetKeys.GOLD);
    public static final ArmorMaterial DIAMOND_BARDING = new ArmorMaterial(33, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 6);
        map.put(EquipmentType.LEGGINGS, 8);
    }), 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, ItemTags.REPAIRS_DIAMOND_ARMOR, EquipmentAssetKeys.DIAMOND);
//    public static final ArmorMaterial NETHERITE_BARDING = new ArmorMaterial(37, Util.make(new EnumMap(EquipmentType.class), map -> {
//        map.put(EquipmentType.BOOTS, 6);
//        map.put(EquipmentType.LEGGINGS, 8);
//    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, ItemTags.REPAIRS_NETHERITE_ARMOR, EquipmentAssetKeys.NETHERITE);

    public final Identifier materialAsset;

    public BardingItem(ArmorMaterial material, EquipmentType type, Settings settings) {
        super(material, type, settings);
        materialAsset = material.assetId().getValue();
    }
}
