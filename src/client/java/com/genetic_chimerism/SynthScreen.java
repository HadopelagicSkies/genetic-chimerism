package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.*;
import com.genetic_chimerism.synthblock.SynthScreenHandler;
import com.genetic_chimerism.mixin.client.DrawContextAccessor;
import com.genetic_chimerism.tooltip.MutationIngredientTooltipComponent;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.HoveredTooltipPositioner;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.util.math.MathHelper;

import java.util.*;
import java.util.List;


public class SynthScreen extends HandledScreen<SynthScreenHandler> {

    private static final Identifier TEXTURE = Identifier.of(GeneticChimerism.MOD_ID, "textures/gui/synth_screen.png");
    private static final Identifier SCROLLER_TEXTURE = Identifier.ofVanilla("container/villager/scroller");
    private static final Identifier SCROLLER_DISABLED_TEXTURE = Identifier.ofVanilla("container/villager/scroller_disabled");
    private static final Identifier SMALL_CHECKMARK = Identifier.of(GeneticChimerism.MOD_ID, "textures/gui/small_check.png");
    private static final Identifier SMALL_CROSS = Identifier.of(GeneticChimerism.MOD_ID, "textures/gui/small_cross.png");
    private static final Identifier GREEN_BORDER = Identifier.of(GeneticChimerism.MOD_ID, "textures/gui/green_border.png");
    private static final Identifier LINE = Identifier.of(GeneticChimerism.MOD_ID, "textures/gui/line.png");
    private static final int TREE_BUTTON_HEIGHT = 24;
    private static final int TREE_BUTTON_WIDTH = 88;
    private static final int SCROLLBAR_HEIGHT = 27;
    private static final int SCROLLBAR_WIDTH = 6;
    private static final int SCROLLBAR_AREA_HEIGHT = 241;
    private static final int SCROLLBAR_OFFSET_Y = 18;
    private static final int SCROLLBAR_OFFSET_X = 99;
    private static final int MUTATION_CHART_HEIGHT = 163;
    private static final int MUTATION_CHART_WIDTH = 154;
    private static final int MUTATION_BUTTON_SIZE = 16;

    private final int treeNum = MutationTrees.listTrees().size();

    private static final int CONFIRM_BUTTON_INDEX = 0;
    private static final int TREE_BUTTON_START_INDEX = 1;
    private final int MUTATION_BUTTON_START_INDEX = treeNum+1;

    private final int maxViewable = 10;
    private final MutationPageButton[] treeButtons = new MutationPageButton[treeNum];
    private final MutationSelectButton[] mutationButtons = new MutationSelectButton[32];
    int indexStartOffset;
    private boolean scrolling;
    private Map<Mutation, int[]> mutationCoords;

    public SynthScreen(SynthScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.titleX = 6;
        this.titleY = 6;
        this.playerInventoryTitleX = 1000 ;
        this.playerInventoryTitleY = 1000 ;
        this.backgroundWidth = 306;
        this.backgroundHeight = 268;
    }

    @Override
    protected void drawBackground(DrawContext drawContext, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - backgroundWidth) /2;
        int y = (height - backgroundHeight) /2;

        drawContext.drawTexture(RenderLayer::getGuiTextured, TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, backgroundWidth, backgroundHeight);
        if(this.handler.treeIndex != -1 && mutationButtons.length >=1) renderTreeLines(drawContext,x+119,y+7);

    }

    private void renderScrollbar(DrawContext context, int x, int y) {
        if (treeNum > maxViewable) {
            int numHidden = treeNum - maxViewable;
            int scrollableHeight = SCROLLBAR_AREA_HEIGHT - SCROLLBAR_HEIGHT;
            int distPerScroll = scrollableHeight / numHidden;
            int scrollHeight = distPerScroll * this.indexStartOffset;

            if(this.indexStartOffset == numHidden){
                scrollHeight = scrollableHeight;
            }

            context.drawGuiTexture(RenderLayer::getGuiTextured, SCROLLER_TEXTURE, x + SCROLLBAR_OFFSET_X, y + SCROLLBAR_OFFSET_Y + scrollHeight, SCROLLBAR_WIDTH, SCROLLBAR_HEIGHT);
        } else {
            context.drawGuiTexture(RenderLayer::getGuiTextured, SCROLLER_DISABLED_TEXTURE, x + SCROLLBAR_OFFSET_X, y + SCROLLBAR_OFFSET_Y, SCROLLBAR_WIDTH, SCROLLBAR_HEIGHT);
        }

    }

    private Map<Mutation,int[]> renderMutationMenu(int x, int y){
        MutationTrees selectedTree = MutationTrees.mutationTreesList.get(handler.treeIndex-TREE_BUTTON_START_INDEX);
        int maxDepth = 0;
        for (Mutation mutation : selectedTree.mutations){
            int mutDepth = MutationTrees.treeDepth(mutation, 1);
            if (mutDepth > maxDepth) {
                maxDepth = mutDepth;
            }
        }


        Map<Mutation,int[]> mutationCoords = HashMap.newHashMap(selectedTree.mutations.size());
        for (Mutation mut : selectedTree.mutations){
            mutationCoords.put(mut, new int[]{-1, MutationTrees.treeDepth(mut, 1) - 1});
        }

        List<Mutation> treeEnds = new ArrayList<>();
        for(Mutation mutation : selectedTree.mutations){
            if(treeEnds.contains(mutation.getPrereq())){
                treeEnds.remove(mutation.getPrereq());
                treeEnds.add(mutation);
            }
            else{
                treeEnds.add(mutation);
            }
        }
        int maxCount = treeEnds.size();

        for(Mutation treeEnd:treeEnds){
            int[] coords = mutationCoords.get(treeEnd);
            coords[0] = treeEnds.indexOf(treeEnd);
            mutationCoords.put(treeEnd,coords);
            Mutation prereq = treeEnd;
            for (int i = 0; i < MutationTrees.treeDepth(treeEnd, 1); i++) {
                prereq = prereq.getPrereq();
                int[] prereqCoords = mutationCoords.get(prereq);
                if(prereqCoords != null && prereqCoords[0] == -1){
                    prereqCoords[0] = treeEnds.indexOf(treeEnd);
                }
                mutationCoords.put(prereq,prereqCoords);
            }
        }

        if(selectedTree == SmallMammalTree.small_mammal){
            for(Mutation mutation : selectedTree.mutations){
                int[] coords = new int[2];
                if(mutation == SmallMammalTree.dogTail){
                    coords[0] = mutationCoords.get(SmallMammalTree.dogEars)[0];
                    coords[1] = mutationCoords.get(SmallMammalTree.dogEars)[1]+1;
                    mutationCoords.put(mutation,coords);
                } else if(mutation == SmallMammalTree.catTail){
                    coords[0] = mutationCoords.get(SmallMammalTree.catEars)[0];
                    coords[1] = mutationCoords.get(SmallMammalTree.catTail)[1]+1;
                    mutationCoords.put(mutation,coords);
                } else if(mutation == SmallMammalTree.foxTail){
                    coords[0] = mutationCoords.get(SmallMammalTree.foxEars)[0];
                    coords[1] = mutationCoords.get(SmallMammalTree.foxEars)[1]+1;
                    mutationCoords.put(mutation,coords);
                } else if(mutation == SmallMammalTree.bunnyTail){
                    coords[0] = mutationCoords.get(SmallMammalTree.bunnyEars)[0];
                    coords[1] = mutationCoords.get(SmallMammalTree.bunnyEars)[1]+1;
                    mutationCoords.put(mutation,coords);
                }
            }
            maxCount -= 4;
        }

        //yes i know some of the x/y labeling is super off from rotating the chart
        int yInterval = Math.round((float)MUTATION_CHART_HEIGHT /((float)maxCount+1f));
        int xInterval = Math.round((float)MUTATION_CHART_WIDTH /((float)maxDepth+1f));
        for(int i = 0; i < selectedTree.mutations.size(); ++i) {
            Mutation buttonMut = selectedTree.mutations.get(i);
            int buttonX = x + (xInterval * (mutationCoords.get(buttonMut)[1]+1)) - (MUTATION_BUTTON_SIZE / 2);
            int buttonY = y + (yInterval * (mutationCoords.get(buttonMut)[0]+1)) - (MUTATION_BUTTON_SIZE / 2);

            this.mutationButtons[i] = this.addDrawableChild(new MutationSelectButton(buttonX, buttonY, MUTATION_BUTTON_START_INDEX + i, (button) -> {
                if (button instanceof MutationSelectButton) {
                    // on button click portion
                    this.handler.setMutationIndex(((MutationSelectButton) button).getIndex());
                    GeneticChimerism.LOGGER.info("mutation selected:" + (this.handler.mutationIndex - MUTATION_BUTTON_START_INDEX));
                    this.client.interactionManager.clickButton(((SynthScreenHandler) this.handler).syncId, this.handler.mutationIndex);
                }
            }));
        }
        return mutationCoords;
    }



    private void renderButtonOverlay(DrawContext context) {
        List<MutationInfo> mutList = MutationAttachments.getMutationsAttached(handler.player);
        MutationTrees selectedTree = MutationTrees.mutationTreesList.get(handler.treeIndex - TREE_BUTTON_START_INDEX);
        for (int l = 0; l < selectedTree.mutations.size(); ++l) {
            MutationSelectButton button = mutationButtons[l];
            int buttonX = button.getX();
            int buttonY = button.getY();

            int cornerOffset = 6;
            if (mutList != null && mutList.contains(new MutationInfo(selectedTree.mutations.get(l).getMutID(), selectedTree.mutations.get(l).getTreeID()))) {
                context.drawTexture(RenderLayer::getGuiTexturedOverlay, GREEN_BORDER, buttonX - 1, buttonY - 1, 0, 0,18,18,18,18);
            } else if (selectedTree.mutations.get(l).getPrereq() == null || (mutList != null && mutList.contains(new MutationInfo(selectedTree.mutations.get(l).getPrereq().getMutID(), selectedTree.mutations.get(l).getPrereq().getTreeID())))) {
                context.drawTexture(RenderLayer::getGuiTexturedOverlay, SMALL_CHECKMARK, buttonX - cornerOffset, buttonY - cornerOffset, 0, 0,24,24,24,24);
            } else {
                context.drawTexture(RenderLayer::getGuiTexturedOverlay, SMALL_CROSS, buttonX - cornerOffset, buttonY - cornerOffset, 0, 0,24,24,24,24);
            }
        }
    }

    private void renderTreeLines(DrawContext context, int x, int y ) {
        MutationTrees selectedTree = MutationTrees.mutationTreesList.get(handler.treeIndex-TREE_BUTTON_START_INDEX);
        int maxDepth = 0;
        for (Mutation mutation : selectedTree.mutations){
            int mutDepth = MutationTrees.treeDepth(mutation, 1);
            if (mutDepth > maxDepth) {
                maxDepth = mutDepth;
            }
        }
        List<Mutation> treeEnds = new ArrayList<>();
        for(Mutation mutation : selectedTree.mutations){
            if(treeEnds.contains(mutation.getPrereq())){
                treeEnds.remove(mutation.getPrereq());
                treeEnds.add(mutation);
            }
            else{
                treeEnds.add(mutation);
            }
        }
        int maxCount = treeEnds.size();

        if(selectedTree == SmallMammalTree.small_mammal){
            maxCount -= 4;
        }

        //yes i know some of the x/y labeling is super off from rotating the chart
        int yInterval = Math.round((float) (MUTATION_CHART_HEIGHT) / ((float) maxCount+1f));
        int xInterval = Math.round((float) (MUTATION_CHART_WIDTH) / ((float) maxDepth+1f));
        for (int i = 0; i < selectedTree.mutations.size(); ++i) {
            Mutation buttonMut = selectedTree.mutations.get(i);
            int buttonX = x + (xInterval * (this.mutationCoords.get(buttonMut)[1]+1)) - (MUTATION_BUTTON_SIZE / 2);
            int buttonY = y + (yInterval * (this.mutationCoords.get(buttonMut)[0]+1)) - (MUTATION_BUTTON_SIZE / 2);

            if (buttonMut.getPrereq() != null) {
                int prereqX = x + (xInterval * (this.mutationCoords.get(buttonMut.getPrereq())[1]+1)) - (MUTATION_BUTTON_SIZE / 2);
                int prereqY = y + (yInterval * (this.mutationCoords.get(buttonMut.getPrereq())[0]+1)) - (MUTATION_BUTTON_SIZE / 2);

                context.drawTexture(RenderLayer::getGuiTexturedOverlay, LINE, prereqX + (MUTATION_BUTTON_SIZE / 2), buttonY + (MUTATION_BUTTON_SIZE / 2)-1,2,2,buttonX-prereqX,2,2,2);
                context.drawTexture(RenderLayer::getGuiTexturedOverlay, LINE, prereqX + (MUTATION_BUTTON_SIZE / 2)-1, prereqY + (MUTATION_BUTTON_SIZE / 2),2,2,2,buttonY-prereqY,2,2);

            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        super.render(context, mouseX, mouseY, delta);


        List<MutationTrees>  mutationTrees = (this.handler).getTrees();
        int treesTried = 0;
        int drawn = 0;
        for( MutationTrees listTrees : mutationTrees) {
           if (this.canScroll(treeNum) && treesTried >= this.indexStartOffset && drawn < maxViewable) {
               context.drawTexture(RenderLayer::getGuiTexturedOverlay, listTrees.icon, i + 14, TREE_BUTTON_HEIGHT * drawn + j + 22, 16, 16,16,16,16,16);
               context.drawText(textRenderer,Text.translatableWithFallback("mutations.tree."+ listTrees.treeID,listTrees.treeID),i + 32, TREE_BUTTON_HEIGHT * drawn + j + 26, 0, false);
               drawn++;
           }
           treesTried++;
        }

        if(this.handler.treeIndex != -1 && mutationButtons.length >=1) renderButtonOverlay(context);
        context.drawTexture(RenderLayer::getGuiTexturedOverlay, SMALL_CHECKMARK,i+this.backgroundWidth -33 , j+(this.backgroundHeight/2) -40,24,24,24,24,24,24);

        this.renderScrollbar(context, i, j);
        for (MutationSelectButton button :this.mutationButtons){
            if(button != null) {button.renderTooltip(context,mouseX,mouseY);}
        }

        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private boolean canScroll(int listSize) {
        return listSize > maxViewable;
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (!super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount)) {
            if (this.canScroll(treeNum)) {
                int maxOffset = treeNum - maxViewable;
                this.indexStartOffset = MathHelper.clamp((int) ((double) this.indexStartOffset - verticalAmount), 0, maxOffset);
                clearAndInit();
            }

        }
        return true;
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        int i = treeNum;
        if (this.scrolling) {
            int j = this.y + 18;
            int k = j + 139;
            int l = i - maxViewable;
            float f = ((float)mouseY - (float)j - 13.5F) / ((float)(k - j) - 27.0F);
            f = f * (float)l + 0.5F;
            this.indexStartOffset = MathHelper.clamp((int)f, 0, l);
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        if (this.canScroll(treeNum) && mouseX > (double)(i + 94) && mouseX < (double)(i + 94 + 6) && mouseY > (double)(j + 18) && mouseY <= (double)(j + 18 + 139 + 1)) {
            this.scrolling = true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void init() {
        super.init();

        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        int k = j + 18;

        if (handler.treeIndex != -1){
            handler.setMutationIndex(-1);
            this.children().removeAll(Arrays.asList(this.mutationButtons));
            Arrays.fill(this.mutationButtons, null);
            mutationCoords = renderMutationMenu(i+119,j+7);
        }

        for(int l = 0; l < Math.min(treeNum,maxViewable); ++l) {
            this.treeButtons[l] = this.addDrawableChild(new MutationPageButton(i + 10, k, TREE_BUTTON_START_INDEX+l+indexStartOffset, (button) -> {
                if (button instanceof MutationPageButton) {
                    this.handler.setTreeIndex(((MutationPageButton)button).getIndex());
                    GeneticChimerism.LOGGER.info("tree selected:" + (this.handler.treeIndex-TREE_BUTTON_START_INDEX));
                    this.client.interactionManager.clickButton((this.handler).syncId, this.handler.treeIndex);
                    clearAndInit();
                }

            }));
            k += TREE_BUTTON_HEIGHT;
        }
        this.addDrawableChild(new MutationConfirmButton(i + this.backgroundWidth - 21, j + (this.backgroundHeight / 2) - 27, CONFIRM_BUTTON_INDEX, (button) -> {
            if (button instanceof MutationConfirmButton) {
                // on button click portion
                GeneticChimerism.LOGGER.info("confirm button client click");
                this.client.interactionManager.clickButton(((SynthScreenHandler) this.handler).syncId, CONFIRM_BUTTON_INDEX);
                clearAndInit();
            }

        }));

    }

    @Environment(EnvType.CLIENT)
    public static class MutationPageButton extends ButtonWidget {
        final int index;

        public MutationPageButton(final int x, final int y, final int index, final PressAction onPress) {
            super(x, y, TREE_BUTTON_WIDTH, TREE_BUTTON_HEIGHT, ScreenTexts.EMPTY, onPress, DEFAULT_NARRATION_SUPPLIER);
            this.index = index;
            this.visible = true;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public class MutationSelectButton extends ButtonWidget {
        final int index;

        public MutationSelectButton(final int x, final int y, final int index, final PressAction onPress) {
            super(x, y, MUTATION_BUTTON_SIZE, MUTATION_BUTTON_SIZE, ScreenTexts.EMPTY, onPress, DEFAULT_NARRATION_SUPPLIER);
            this.index = index;
            this.visible = true;
        }

        public int getIndex() {
            return this.index;
        }
        public void renderTooltip(DrawContext context, int x, int y) {
            if (this.hovered){
                Mutation mutation =  handler.getTrees().get(handler.treeIndex-TREE_BUTTON_START_INDEX).mutations.get(this.getIndex()-MUTATION_BUTTON_START_INDEX);

                List<TooltipComponent> text = new ArrayList<>();
                text.add(TooltipComponent.of(Text.translatableWithFallback(("mutations.mutation."+ mutation.getMutID()),mutation.getMutID()).asOrderedText()));
                text.add(TooltipComponent.of(Text.translatableWithFallback("mutations.mutation.desc." + mutation.getMutID(),mutation.getMutID()).asOrderedText()));

                if(!mutation.getParts().isEmpty()) {
                    MutableText parts = Text.literal("");
                    boolean isFirst = true;
                    for(MutatableParts part :mutation.getParts()) {
                        Text partTranslate = part.getTranslatableName();
                        if(!isFirst) parts.append(", ");
                            else isFirst = false;
                        parts.append(partTranslate);
                    }
                    text.add(TooltipComponent.of(Text.translatable("block.genetic_chimerism.mutagen_synthesizer.tooltip_parts", parts).asOrderedText()));
                }

                if(mutation.getPrereq() != null) {
                    String prereq = Text.translatableWithFallback("mutations.mutation." + mutation.getPrereq().getMutID(),mutation.getPrereq().getMutID()).getString();
                    text.add(TooltipComponent.of(Text.translatable("block.genetic_chimerism.mutagen_synthesizer.tooltip_prereqs", prereq).asOrderedText()));
                }
                else {
                    String none = Text.translatable("block.genetic_chimerism.mutagen_synthesizer.tooltip_none").getString();
                    text.add(TooltipComponent.of(Text.translatable("block.genetic_chimerism.mutagen_synthesizer.tooltip_prereqs", none).asOrderedText()));
                }

                if(handler.getTrees().get(handler.treeIndex - TREE_BUTTON_START_INDEX).mutations.get(this.getIndex() - MUTATION_BUTTON_START_INDEX).getRecipe() != null) {
                    List<ItemStack> inputs = handler.getTrees().get(handler.treeIndex - TREE_BUTTON_START_INDEX).mutations.get(this.getIndex() - MUTATION_BUTTON_START_INDEX).getRecipe().getInputs();
                    text.add(new MutationIngredientTooltipComponent(inputs));
                }
                ((DrawContextAccessor)context).callDrawTooltip(SynthScreen.this.textRenderer, text ,x,y, HoveredTooltipPositioner.INSTANCE,null);
            }
        }
    }

    public static class MutationConfirmButton extends ButtonWidget {
        final int index;

        public MutationConfirmButton(final int x, final int y, final int index, final PressAction onPress) {
            super(x, y, 14, 14, ScreenTexts.EMPTY, onPress, DEFAULT_NARRATION_SUPPLIER);
            this.index = index;
            this.visible = true;
        }

        public int getIndex() {
            return this.index;
        }
    }
}
