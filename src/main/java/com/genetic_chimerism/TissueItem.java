package com.genetic_chimerism;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


import java.util.List;

public class TissueItem extends Item {
    public TissueItem(Settings settings) {
        super(settings);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.contains(ModComponents.TISSUE_TYPE)) {
            String tissueType = stack.get(ModComponents.TISSUE_TYPE);
            tooltip.add(Text.translatable("item.tissue_type.info", tissueType).formatted(Formatting.GOLD));
        }
    }

    public static String checkTissueType(String mob){
        String mobType = "";
        String[] mobList = {
                "entity.minecraft.allay", "winged",
                "entity.minecraft.armadillo", "shelled",
                "entity.minecraft.axolotl", "amphibious",
                "entity.minecraft.bat", "winged",
                "entity.minecraft.camel", "equine",
                "entity.minecraft.cat", "small_mammal",
                "entity.minecraft.chicken", "winged",
                "entity.minecraft.cod", "aquatic",
                "entity.minecraft.cow", "horned",
                "entity.minecraft.donkey", "equine",
                "entity.minecraft.frog", "amphibious",
                "entity.minecraft.glow_squid", "aquatic",
                "entity.minecraft.horse", "equine",
                "entity.minecraft.mooshroom", "shroomby",
                "entity.minecraft.mule", "equine",
                "entity.minecraft.ocelot", "small_mammal",
                "entity.minecraft.parrot", "winged",
                "entity.minecraft.pufferfish", "spined",
                "entity.minecraft.rabbit", "small_mammal",
                "entity.minecraft.salmon", "aquatic",
                "entity.minecraft.sheep", "woolen",
                "entity.minecraft.sniffer", "tusked",
                "entity.minecraft.squid", "aquatic",
                "entity.minecraft.turtle", "shelled",
                "entity.minecraft.tropical_fish", "aquatic",
                "entity.minecraft.bee", "bug",
                "entity.minecraft.cave_spider", "bug",
                "entity.minecraft.dolphin", "aquatic",
                "entity.minecraft.fox", "small_mammal",
                "entity.minecraft.goat", "horned",
                "entity.minecraft.llama", "woolen",
                "entity.minecraft.panda", "woolen",
                "entity.minecraft.pig", "tusked",
                "entity.minecraft.polar_bear", "woolen",
                "entity.minecraft.spider", "bug",
                "entity.minecraft.trader_lama", "woolen",
                "entity.minecraft.wolf", "small_mammal",
                "entity.minecraft.elder_guardian", "spined",
                "entity.minecraft.ender_dragon", "winged",
                "entity.minecraft.guardian", "spined",
                "entity.minecraft.hoglin", "tusked",
                "entity.minecraft.phantom", "winged",
                "entity.minecraft.ravager", "horned",
                "entity.minecraft.shulker", "shelled",
                "entity.minecraft.silverfish", "bug",
                "entity.minecraft.warden", "horned",
        };
        for(int i=0;i<mobList.length; i++ ){
            if (mobList[i].equals(mob) ) {
                mobType = mobList[i+1];
                break;
            }
        }
        GeneticChimerism.LOGGER.info("running type check:" + mobType);
        return mobType;
    }
    public static String checkTissueTier(String mobType){
        String mobTier = "";
        String[] mobList = {
                "entity.minecraft.allay", "crude",
                "entity.minecraft.armadillo", "crude",
                "entity.minecraft.axolotl", "crude",
                "entity.minecraft.bat", "crude",
                "entity.minecraft.camel", "crude",
                "entity.minecraft.cat", "crude",
                "entity.minecraft.chicken", "crude",
                "entity.minecraft.cod", "crude",
                "entity.minecraft.cow", "crude",
                "entity.minecraft.donkey", "crude",
                "entity.minecraft.frog", "crude",
                "entity.minecraft.glow_squid", "crude",
                "entity.minecraft.horse", "crude",
                "entity.minecraft.mooshroom", "crude",
                "entity.minecraft.mule", "crude",
                "entity.minecraft.ocelot", "crude",
                "entity.minecraft.parrot", "crude",
                "entity.minecraft.pufferfish", "crude",
                "entity.minecraft.rabbit", "crude",
                "entity.minecraft.salmon", "crude",
                "entity.minecraft.sheep", "crude",
                "entity.minecraft.sniffer", "crude",
                "entity.minecraft.squid", "crude",
                "entity.minecraft.turtle", "crude",
                "entity.minecraft.tropical_fish", "crude",
                "entity.minecraft.bee", "crude",
                "entity.minecraft.cave_spider", "crude",
                "entity.minecraft.dolphin", "crude",
                "entity.minecraft.fox", "crude",
                "entity.minecraft.goat", "fresh",
                "entity.minecraft.llama", "crude",
                "entity.minecraft.panda", "crude",
                "entity.minecraft.pig", "crude",
                "entity.minecraft.polar_bear", "crude",
                "entity.minecraft.spider", "crude",
                "entity.minecraft.trader_lama", "crude",
                "entity.minecraft.wolf", "crude",
                "entity.minecraft.elder_guardian", "crude",
                "entity.minecraft.ender_dragon", "crude",
                "entity.minecraft.guardian", "crude",
                "entity.minecraft.hoglin", "crude",
                "entity.minecraft.phantom", "crude",
                "entity.minecraft.ravager", "ensouled",
                "entity.minecraft.shulker", "crude",
                "entity.minecraft.silverfish", "crude",
                "entity.minecraft.warden", "crude",
        };
        for(int i=0;i<mobList.length; i++ ){
            if (mobList[i].equals(mobType) ) {
                mobTier = mobList[i+1];
                break;
            }
        }
        GeneticChimerism.LOGGER.info("running tier check:" + mobTier);
        return mobTier;
    }
}