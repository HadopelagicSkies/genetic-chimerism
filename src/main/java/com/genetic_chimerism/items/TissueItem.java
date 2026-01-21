package com.genetic_chimerism.items;


import com.genetic_chimerism.GeneticChimerismComponents;
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
        if (stack.contains(GeneticChimerismComponents.TISSUE_TYPE)) {
            String tissueID = stack.get(GeneticChimerismComponents.TISSUE_TYPE);
            String tissueName = Text.translatable("mutations.tree."+tissueID).getString();
            tooltip.add(Text.translatable("item.tissue_type.info", tissueName).formatted(Formatting.GOLD));
        }
    }

}