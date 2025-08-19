package com.genetic_chimerism;

import com.genetic_chimerism.entity.DiplocaulusEntity;
import com.genetic_chimerism.entity.DroppedTailEntity;
import com.genetic_chimerism.entity.projectile.QuillProjectileEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;


public class GeneticChimerismEntities {
    public static void initialize(){
        FabricDefaultAttributeRegistry.register(register(DIPLOCAULUS,"diplocaulus",true),DiplocaulusEntity.createDiplocaulusAttributes());
        addSpawning(DIPLOCAULUS, BiomeKeys.MANGROVE_SWAMP,10,1,3);

        FabricDefaultAttributeRegistry.register(register(DROPPED_TAIL,"dropped_tail",false),DroppedTailEntity.createDroppedTailAttributes());

        register(QUILL_PROJECTILE,"quill_projectile",false);


    }

    public static final EntityType<DiplocaulusEntity> DIPLOCAULUS = EntityType.Builder.create(DiplocaulusEntity::new, SpawnGroup.AXOLOTLS)
            .dimensions(2.0F,0.75F)
            .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(GeneticChimerism.MOD_ID, "diplocaulus")));

    public static final EntityType<DroppedTailEntity> DROPPED_TAIL = EntityType.Builder.create(DroppedTailEntity::new, SpawnGroup.MISC)
            .dimensions(0.5F,0.5F)
            .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(GeneticChimerism.MOD_ID, "dropped_tail")));

    public static final EntityType<QuillProjectileEntity> QUILL_PROJECTILE = EntityType.Builder.create(QuillProjectileEntity::new, SpawnGroup.MISC)
            .dropsNothing()
            .dimensions(0.25F,0.25F)
            .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(GeneticChimerism.MOD_ID, "quill_projectile")));

    public static <T extends Entity> EntityType<T> register(EntityType<T> entityType, String name, boolean shouldEgg) {
        Identifier id = Identifier.of(GeneticChimerism.MOD_ID, name);
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
        EntityType<T> entity = Registry.register(Registries.ENTITY_TYPE,key,entityType);

        if(shouldEgg) {
            Identifier eggId = Identifier.of(GeneticChimerism.MOD_ID, name + "_spawn_egg");
            RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, eggId);
            Item spawnEggItem = new SpawnEggItem((EntityType<? extends MobEntity>) entityType, new Item.Settings().registryKey(itemKey));
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register((itemGroup) -> {
                itemGroup.add(spawnEggItem);
            });
            Registry.register(Registries.ITEM, itemKey, spawnEggItem);
        }
        return entity;
    }

    public static void addSpawning(EntityType<?> entityType, RegistryKey<Biome> biomeKey, int weight, int minGroupSize, int maxGroupSize){
        BiomeModifications.addSpawn(context -> context.getBiomeKey().equals(biomeKey),entityType.getSpawnGroup(),entityType,weight,minGroupSize,maxGroupSize);
    }
}
