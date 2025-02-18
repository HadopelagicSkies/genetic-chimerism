package com.genetic_chimerism;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ScalpelItem extends SwordItem {
    public ScalpelItem(ToolMaterial mat, float atkDamage, float atkSpeed, Settings settings) {
        super(mat, atkDamage, atkSpeed, settings);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postDamageEntity(stack, target, attacker);

        stack.damage(1, attacker, EquipmentSlot.MAINHAND);

        if (target.isDead()) {
            Identifier mobID = EntityType.getId(target.getType());
            MobInfoReloadListener.MobInfoData mobInfo =  MobInfoReloadListener.getMappedMobData().get(mobID);
            String mobTree;
            String mobTier;

            if (mobInfo == null)
                return;
            else{
                mobTree = mobInfo.treeID();
                mobTier = mobInfo.tier();
            }

            int count =1;
            String enchants = stack.getEnchantments().toString();
            if (enchants.contains("Enchantment Looting}=>")) {
                int startIndex = enchants.indexOf("Enchantment Looting}=>") + 22;
                String lootingString = enchants.substring(startIndex,enchants.indexOf("}",startIndex));
                int lootingLevel = Integer.parseInt(lootingString);

                count += lootingLevel;
            }

            if (!mobTree.isEmpty()) {
                World world = attacker.getWorld();

                GeneticChimerism.LOGGER.info("kill logged: " + mobTier + " " + mobTree);

                double targetX = target.getX();
                double targetY = target.getY();
                double targetZ = target.getZ();

                if (stack.getItem() == ModItems.IRON_SCALPEL) {
                    ItemStack crudeStack = new ItemStack(ModItems.CRUDE_TISSUE_SAMPLE,count);
                    crudeStack.set(ModComponents.TISSUE_TYPE, mobTree);
                    ItemEntity itemDrop = new ItemEntity(world, targetX, targetY, targetZ, crudeStack);
                    world.spawnEntity(itemDrop);
                }

                if (stack.getItem() == ModItems.DIAMOND_SCALPEL) {
                    if (mobTier.equals("fresh") || mobTier.equals("ensouled")) {
                        ItemStack freshStack = new ItemStack(ModItems.FRESH_TISSUE_SAMPLE,count);
                        freshStack.set(ModComponents.TISSUE_TYPE, mobTree);
                        ItemEntity itemDrop = new ItemEntity(world, targetX, targetY, targetZ, freshStack);
                        world.spawnEntity(itemDrop);
                    }
                    else {
                        ItemStack crudeStack = new ItemStack(ModItems.CRUDE_TISSUE_SAMPLE,count);
                        crudeStack.set(ModComponents.TISSUE_TYPE, mobTree);
                        ItemEntity itemDrop = new ItemEntity(world, targetX, targetY, targetZ, crudeStack);
                        world.spawnEntity(itemDrop);
                    }
                }

                if (stack.getItem() == ModItems.SOUL_SCALPEL) {
                    if (mobTier.equals("ensouled")) {
                        ItemStack ensouledStack = new ItemStack(ModItems.ENSOULED_TISSUE_SAMPLE,count);
                        ensouledStack.set(ModComponents.TISSUE_TYPE, mobTree);
                        ItemEntity itemDrop = new ItemEntity(world, targetX, targetY, targetZ, ensouledStack);
                        world.spawnEntity(itemDrop);
                    }
                    else if (mobTier.equals("fresh")) {
                        ItemStack freshStack = new ItemStack(ModItems.FRESH_TISSUE_SAMPLE,count);
                        freshStack.set(ModComponents.TISSUE_TYPE, mobTree);
                        ItemEntity itemDrop = new ItemEntity(world, targetX, targetY, targetZ, freshStack);
                        world.spawnEntity(itemDrop);
                    }
                    else {
                        ItemStack crudeStack = new ItemStack(ModItems.CRUDE_TISSUE_SAMPLE,count);
                        crudeStack.set(ModComponents.TISSUE_TYPE, mobTree);
                        ItemEntity itemDrop = new ItemEntity(world, targetX, targetY, targetZ, crudeStack);
                        world.spawnEntity(itemDrop);
                    }
                }
            }
        }
    }
}