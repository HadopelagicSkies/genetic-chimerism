package com.genetic_chimerism.tooltip;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class MutationIngredientTooltipComponent implements TooltipComponent {
    private static final Identifier BUNDLE_SLOT_BACKGROUND_TEXTURE = Identifier.ofVanilla("container/bundle/slot_background");
    private final List<ItemStack> tooltipData;

    public MutationIngredientTooltipComponent(List<ItemStack> tooltipData) {
        this.tooltipData = tooltipData;
    }

    @Override
    public int getHeight(TextRenderer textRenderer) {
        return this.getRowsHeight() + 13 + 8;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 96;
    }

    @Override
    public boolean isSticky() {
        return true;
    }

    private int getRowsHeight() {
        return this.getRows() * 24;
    }

    private int getXMargin(int width) {
        return (width - 96) / 2;
    }

    private int getRows() {
        return MathHelper.ceilDiv(this.tooltipData.size(), 4);
    }

    private int getNumVisibleSlots() {
        return Math.min(12, this.tooltipData.size());
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, int width, int height, DrawContext context) {
        int startX = x ;
        int startY = y ;

        for (int index = 0; index < this.tooltipData.size(); index++){
            int row = index / 4;
            int col = index % 4;
            int xPos = startX - col * 24 + 24;
            int yPos = startY - row * 24 + 24;
            this.drawItem(this.tooltipData.get(index), xPos, yPos, index, textRenderer, context);
        }
    }

    private void drawItem(ItemStack itemStack, int x, int y, int seed, TextRenderer textRenderer, DrawContext drawContext) {

        drawContext.drawGuiTexture(RenderLayer::getGuiTextured, BUNDLE_SLOT_BACKGROUND_TEXTURE, x, y, 24, 24);
        drawContext.drawItem(itemStack, x + 4, y + 4, seed);
        drawContext.drawStackOverlay(textRenderer, itemStack, x + 4, y + 4);
    }
}

