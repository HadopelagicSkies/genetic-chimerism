package com.genetic_chimerism;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class MutagenVialItem extends Item {
        public MutagenVialItem(Settings settings) {
            super(settings);
        }

        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (stack.contains(GeneticChimerismComponents.MUTATION_STORED)) {
                String mutationID = stack.get(GeneticChimerismComponents.MUTATION_STORED).mutID();
                String mutationName = Text.translatable("mutations.mutation."+mutationID).getString();
                tooltip.add(Text.translatable("item.genetic_chimerism.vial_mutagen.info", mutationName).formatted(Formatting.GOLD));
            }
        }
}
