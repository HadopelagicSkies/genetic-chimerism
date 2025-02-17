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
                "allay", "winged",
                "armadillo", "shelled",
                "axolotl", "amphibious",
                "bat", "winged",
                "camel", "hooved",
                "cat", "small_mammal",
                "chicken", "winged",
                "cod", "aquatic",
                "cow", "horned",
                "donkey", "hooved",
                "frog", "amphibious",
                "glow_squid", "aquatic",
                "horse", "hooved",
                "mooshroom", "shroomby",
                "mule", "hooved",
                "ocelot", "small_mammal",
                "parrot", "winged",
                "pufferfish", "spined",
                "rabbit", "small_mammal",
                "salmon", "aquatic",
                "sheep", "woolen",
                "sniffer", "tusked",
                "squid", "aquatic",
                "turtle", "shelled",
                "tropical_fish", "aquatic",
                "bee", "invertebrate",
                "cave_spider", "invertebrate",
                "dolphin", "aquatic",
                "fox", "small_mammal",
                "goat", "horned",
                "llama", "woolen",
                "panda", "woolen",
                "pig", "tusked",
                "polar_bear", "woolen",
                "spider", "invertebrate",
                "trader_lama", "woolen",
                "wolf", "small_mammal",
                "elder_guardian", "spined",
                "ender_dragon", "winged",
                "guardian", "spined",
                "hoglin", "tusked",
                "phantom", "winged",
                "ravager", "horned",
                "shulker", "shelled",
                "silverfish", "invertebrate",
                "warden", "horned",
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
                "allay", "crude",
                "armadillo", "crude",
                "axolotl", "crude",
                "bat", "crude",
                "camel", "crude",
                "cat", "crude",
                "chicken", "crude",
                "cod", "crude",
                "cow", "crude",
                "donkey", "crude",
                "frog", "crude",
                "glow_squid", "crude",
                "horse", "crude",
                "mooshroom", "crude",
                "mule", "crude",
                "ocelot", "crude",
                "parrot", "crude",
                "pufferfish", "crude",
                "rabbit", "crude",
                "salmon", "crude",
                "sheep", "crude",
                "sniffer", "crude",
                "squid", "crude",
                "turtle", "crude",
                "tropical_fish", "crude",
                "bee", "crude",
                "cave_spider", "crude",
                "dolphin", "crude",
                "fox", "crude",
                "goat", "fresh",
                "llama", "crude",
                "panda", "crude",
                "pig", "crude",
                "polar_bear", "crude",
                "spider", "crude",
                "trader_lama", "crude",
                "wolf", "crude",
                "elder_guardian", "crude",
                "ender_dragon", "crude",
                "guardian", "crude",
                "hoglin", "crude",
                "phantom", "crude",
                "ravager", "ensouled",
                "shulker", "crude",
                "silverfish", "crude",
                "warden", "crude",
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