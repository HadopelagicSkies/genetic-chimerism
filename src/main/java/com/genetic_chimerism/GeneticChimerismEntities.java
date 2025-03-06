package com.genetic_chimerism;

import com.genetic_chimerism.entity.DiplocaulusEntity;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;



public class GeneticChimerismEntities {
    public static void initialize(){
        FabricDefaultAttributeRegistry.register(register(DIPLOCAULUS,"diplocaulus"),DiplocaulusEntity.createDiplocaulusAttributes());
    }

    public static final EntityType<DiplocaulusEntity> DIPLOCAULUS = EntityType.Builder.create(DiplocaulusEntity::new, SpawnGroup.AXOLOTLS)
            .dimensions(2.0F,0.75F)
            .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(GeneticChimerism.MOD_ID, "diplocaulus")));

    public static EntityType<? extends MobEntity> register(EntityType<? extends MobEntity> entityType, String name) {
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
        EntityType<? extends MobEntity> entity = Registry.register(Registries.ENTITY_TYPE,key,entityType);

        Identifier eggId = Identifier.of(GeneticChimerism.MOD_ID, name + "_spawn_egg");
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, eggId);
        Item spawnEggItem = new SpawnEggItem(entityType,new Item.Settings().registryKey(itemKey));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register((itemGroup) -> {
            itemGroup.add(spawnEggItem);
        });

        Registry.register(Registries.ITEM,itemKey, spawnEggItem);

        return entity;
    }
}
