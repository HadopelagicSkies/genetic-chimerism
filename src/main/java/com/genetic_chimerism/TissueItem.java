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

}