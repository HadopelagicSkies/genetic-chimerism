package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationBodyInfo;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;

public class ChromaScreen extends Screen {
    private static final int COLOR_SCROLLER_WIDTH = 100;
    private static final int COLOR_SCROLLER_HEIGHT = 20;

    //private final PlayerEntity copyPlayer;
    private static final Identifier BACKGROUND_TEXTURE = Identifier.of(GeneticChimerism.MOD_ID, "textures/gui/chroma_screen.png");
    private final int titleX;
    private final int titleY;

    private int red1;
    private int green1;
    private int blue1;
    private int red2;
    private int green2;
    private int blue2;

    private final ArrayList<ColorSlider> sliders = new ArrayList<ColorSlider>(6);

    private final MutationBodyInfo oldHead;
    private final MutationBodyInfo oldTorso;
    private final MutationBodyInfo oldArm;
    private final MutationBodyInfo oldLeg;
    private final MutationBodyInfo oldTail;

    private MutationBodyInfo newHead;
    private MutationBodyInfo newTorso;
    private MutationBodyInfo newArm;
    private MutationBodyInfo newLeg;
    private MutationBodyInfo newTail;

    private final int backgroundWidth;
    private final int backgroundHeight;
    private float mouseX;
    private float mouseY;
    private int playerRotateIndex=0;
    private final PlayerEntity player;
    private MutatableParts selectedPart=MutatableParts.HEAD;
    private int patternIndex;


    protected ChromaScreen(PlayerEntity player) {
        super(Text.translatable("gui.genetic_chimerism.chroma_menu"));
        this.player = player;
        this.titleX = 10;
        this.titleY = 10;
        this.backgroundWidth = 276;
        this.backgroundHeight = 253;
        this.oldHead = player.getAttached(MutationAttachments.HEAD_MUTATION);
        this.oldTorso = player.getAttached(MutationAttachments.TORSO_MUTATION);
        this.oldArm = player.getAttached(MutationAttachments.ARM_MUTATION);
        this.oldLeg = player.getAttached(MutationAttachments.LEG_MUTATION);
        this.oldTail = player.getAttached(MutationAttachments.TAIL_MUTATION);

        this.newHead = player.getAttached(MutationAttachments.HEAD_MUTATION);
        this.newTorso = player.getAttached(MutationAttachments.TORSO_MUTATION);
        this.newArm = player.getAttached(MutationAttachments.ARM_MUTATION);
        this.newLeg = player.getAttached(MutationAttachments.LEG_MUTATION);
        this.newTail = player.getAttached(MutationAttachments.TAIL_MUTATION);

        init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        drawBackground(context,delta,mouseX,mouseY);
        super.render(context, mouseX, mouseY, delta);
        this.mouseX = (float)mouseX;
        this.mouseY = (float)mouseY;
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;

        context.drawText(textRenderer,this.title,i + titleX, j + titleY, Colors.BLACK,false);

        context.drawText(textRenderer,"Color Channel 1:",i + titleX, j + 30, Colors.BLACK,false);
        context.drawText(textRenderer,"Color Channel 2:",i + titleX, j + 115, Colors.BLACK,false);
        drawColorSquares(context,i,j);

        for(ColorSlider sliders:this.sliders){
            if (sliders.needsUpdate){
                setNewPartColors();
                sliders.resetUpdate();
                break;
            }
        }
        applyNewPartColors();
        context.getMatrices().push();
        context.getMatrices().translate(0,0,100);
        drawEntity(context, i + 176, j + 8, i + 268, j + 158, 50, 0.0625F, this.mouseX, this.mouseY, this.playerRotateIndex, player);
        context.getMatrices().pop();
        resetPartColors();
    }


    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - backgroundWidth) /2;
        int y = (height - backgroundHeight) /2;
        context.drawTexture(RenderLayer::getGuiTextured, BACKGROUND_TEXTURE, x, y, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
    }

    protected void drawColorSquares(DrawContext context, int i, int j){
        this.red1 = sliders.get(0).getValue();
        this.green1 = sliders.get(1).getValue();
        this.blue1 = sliders.get(2).getValue();

        this.red2 = sliders.get(3).getValue();
        this.green2 = sliders.get(4).getValue();
        this.blue2 = sliders.get(5).getValue();


        context.fill(i+119,j+40,i+139,j+104,ColorHelper.getArgb(red1,green1,blue1));
        context.drawBorder(i+119,j+40,20,64,Colors.BLACK);

        context.fill(i+119,j+126,i+139,j+190,ColorHelper.getArgb(red2,green2,blue2));
        context.drawBorder(i+119,j+126,20,64,Colors.BLACK);
    }

    protected void setNewPartColors(){
        if (selectedPart == MutatableParts.HEAD && oldHead != null) {
            this.newHead = new MutationBodyInfo(oldHead.mutID(), oldHead.treeID(), this.patternIndex, ColorHelper.getArgb(this.red1, this.green1, this.blue1), ColorHelper.getArgb(this.red2, this.green2, this.blue2));
            player.setAttached(MutationAttachments.HEAD_MUTATION, this.newHead);
        }
        if (selectedPart == MutatableParts.TORSO && oldTorso != null) {
            this.newTorso = new MutationBodyInfo(oldTorso.mutID(), oldTorso.treeID(), this.patternIndex, ColorHelper.getArgb(this.red1, this.green1, this.blue1), ColorHelper.getArgb(this.red2, this.green2, this.blue2));
            player.setAttached(MutationAttachments.TORSO_MUTATION, this.newTorso);
        }
        if (selectedPart == MutatableParts.ARM && oldArm != null) {
            this.newArm = new MutationBodyInfo(oldArm.mutID(), oldArm.treeID(), this.patternIndex, ColorHelper.getArgb(this.red1, this.green1, this.blue1), ColorHelper.getArgb(this.red2, this.green2, this.blue2));
            player.setAttached(MutationAttachments.ARM_MUTATION, this.newArm);
        }
        if (selectedPart == MutatableParts.LEG && oldLeg != null) {
            this.newLeg = new MutationBodyInfo(oldLeg.mutID(), oldLeg.treeID(), this.patternIndex, ColorHelper.getArgb(this.red1, this.green1, this.blue1), ColorHelper.getArgb(this.red2, this.green2, this.blue2));
            player.setAttached(MutationAttachments.LEG_MUTATION, this.newLeg);
        }
        if (selectedPart == MutatableParts.TAIL && oldTail != null) {
            this.newTail = new MutationBodyInfo(oldTail.mutID(), oldTail.treeID(), this.patternIndex, ColorHelper.getArgb(this.red1, this.green1, this.blue1), ColorHelper.getArgb(this.red2, this.green2, this.blue2));
            player.setAttached(MutationAttachments.TAIL_MUTATION, this.newTail);
        }
    }

    private void applyNewPartColors() {
        this.player.setAttached(MutationAttachments.HEAD_MUTATION,newHead);
        this.player.setAttached(MutationAttachments.TORSO_MUTATION,newTorso);
        this.player.setAttached(MutationAttachments.ARM_MUTATION,newArm);
        this.player.setAttached(MutationAttachments.LEG_MUTATION,newLeg);
        this.player.setAttached(MutationAttachments.TAIL_MUTATION,newTail);
    }

    protected void resetPartColors(){{
        this.player.setAttached(MutationAttachments.HEAD_MUTATION,oldHead);
        this.player.setAttached(MutationAttachments.TORSO_MUTATION,oldTorso);
        this.player.setAttached(MutationAttachments.ARM_MUTATION,oldArm);
        this.player.setAttached(MutationAttachments.LEG_MUTATION,oldLeg);
        this.player.setAttached(MutationAttachments.TAIL_MUTATION,oldTail);

    }}

    protected void setInitialColors(){
        int color1=Colors.WHITE;
        int color2=Colors.WHITE;
        if (selectedPart == MutatableParts.HEAD && oldHead != null) {
            color1=oldHead.color1();
            color2=oldHead.color2();
        }else if (selectedPart == MutatableParts.TORSO && oldTorso != null) {
            color1=oldTorso.color1();
            color2=oldTorso.color2();
        }else if (selectedPart == MutatableParts.ARM && oldArm != null) {
            color1=oldArm.color1();
            color2=oldArm.color2();
        }else if (selectedPart == MutatableParts.LEG && oldLeg != null) {
            color1=oldLeg.color1();
            color2=oldLeg.color2();
        }else if (selectedPart == MutatableParts.TAIL && oldTail != null) {
            color1=oldTail.color1();
            color2=oldTail.color2();
        }
        ArrayList<Integer> rgb = new ArrayList<Integer>();
        rgb.add(ColorHelper.getRed(color1));
        rgb.add(ColorHelper.getGreen(color1));
        rgb.add(ColorHelper.getBlue(color1));
        rgb.add(ColorHelper.getRed(color2));
        rgb.add(ColorHelper.getGreen(color2));
        rgb.add(ColorHelper.getBlue(color2));

        for(int l=0;l<6;l++){
            sliders.get(l).setValue(rgb.get(l));
        }
    }

    @Override
    protected void init() {
        super.init();
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;


        if(this.width != 0){
            this.addDrawableChild(new ChromaRotateLButton(i  + 176, j + 158-14, 0, (button) -> {
                if (button instanceof ChromaRotateLButton) {
                    this.playerRotateIndex--;
                }
            }));
            this.addDrawableChild(new ChromaRotateRButton(i + 268 - 14, j + 158-14, 1, (button) -> {
                if (button instanceof ChromaRotateRButton) {
                    this.playerRotateIndex++;
                }
            }));
            this.addDrawableChild(new ChromaConfirmButton(i + 9, j + this.backgroundHeight - 29, 2, (button) -> {
                if (button instanceof ChromaConfirmButton) {
                    applyNewPartColors();
                    if (oldHead != null)
                        ClientPlayNetworking.send(new PartRecolorPayload(MutatableParts.HEAD,newHead));
                    if (oldTorso != null)
                        ClientPlayNetworking.send(new PartRecolorPayload(MutatableParts.TORSO,newTorso));
                    if (oldArm != null)
                        ClientPlayNetworking.send(new PartRecolorPayload(MutatableParts.ARM,newArm));
                    if (oldLeg != null)
                        ClientPlayNetworking.send(new PartRecolorPayload(MutatableParts.LEG,newLeg));
                    if (oldTail != null)
                        ClientPlayNetworking.send(new PartRecolorPayload(MutatableParts.TAIL,newTail));
                    this.close();


                }
            }));
            this.addDrawableChild(CyclingButtonWidget.builder(MutatableParts::getTranslatableName).values(MutatableParts.values()).build(i + 9, j + this.backgroundHeight - 52,100,20, Text.translatable("gui.genetic_chimerism.chroma_menu.part"), (button,part) -> {
                selectedPart = part;
                setInitialColors();
                for(ColorSlider slider:this.sliders){
                    slider.updateMessage();
                }
            }));

            sliders.add(this.addDrawableChild(new ColorSlider(i+9,j+40 ,COLOR_SCROLLER_WIDTH,COLOR_SCROLLER_HEIGHT,Text.translatable("gui.genetic_chimerism.chroma_menu.red"),1)));
            sliders.add(this.addDrawableChild(new ColorSlider(i+9,j+62 ,COLOR_SCROLLER_WIDTH,COLOR_SCROLLER_HEIGHT,Text.translatable("gui.genetic_chimerism.chroma_menu.green"),1)));
            sliders.add(this.addDrawableChild(new ColorSlider(i+9,j+84 ,COLOR_SCROLLER_WIDTH,COLOR_SCROLLER_HEIGHT,Text.translatable("gui.genetic_chimerism.chroma_menu.blue"),1)));

            sliders.add(this.addDrawableChild(new ColorSlider(i+9,j+126,COLOR_SCROLLER_WIDTH,COLOR_SCROLLER_HEIGHT,Text.translatable("gui.genetic_chimerism.chroma_menu.red"),1)));
            sliders.add(this.addDrawableChild(new ColorSlider(i+9,j+148,COLOR_SCROLLER_WIDTH,COLOR_SCROLLER_HEIGHT,Text.translatable("gui.genetic_chimerism.chroma_menu.green"),1)));
            sliders.add(this.addDrawableChild(new ColorSlider(i+9,j+170,COLOR_SCROLLER_WIDTH,COLOR_SCROLLER_HEIGHT,Text.translatable("gui.genetic_chimerism.chroma_menu.blue"),1)));

            setInitialColors();
            for(ColorSlider slider:this.sliders){
                slider.updateMessage();
            }
        }
    }



    public static void drawEntity(DrawContext context, int x1, int y1, int x2, int y2, int size, float f, float mouseX, float mouseY, int playerRotateIndex, LivingEntity entity) {
        float g = (float)(x1 + x2) / 2.0F;
        float h = (float)(y1 + y2) / 2.0F;
        context.enableScissor(x1, y1, x2, y2);
        float i = (float)Math.atan((double)((g - mouseX) / 40.0F));
        float j = (float)Math.atan((double)((h - mouseY) / 40.0F));
        Quaternionf quaternionf = (new Quaternionf()).rotateZ((float)Math.PI);
        Quaternionf quaternionf2 = (new Quaternionf()).rotateX(j * 20.0F * ((float)Math.PI / 180F));
        quaternionf.mul(quaternionf2);
        float k = entity.bodyYaw;
        float l = entity.getYaw();
        float m = entity.getPitch();
        float n = entity.prevHeadYaw;
        float o = entity.headYaw;
        entity.bodyYaw = 180.0F + i * 20.0F;
        entity.setYaw(180.0F + i * 40.0F);
        entity.setPitch(-j * 20.0F);
        entity.headYaw = entity.getYaw();
        entity.prevHeadYaw = entity.getYaw();
        float p = entity.getScale();
        Vector3f vector3f = new Vector3f(0.0F, entity.getHeight() / 2.0F + f * p, 0.0F);
        float q = (float)size / p;
        drawEntity(context, g, h, q, vector3f, quaternionf, quaternionf2,playerRotateIndex , entity);
        entity.bodyYaw = k;
        entity.setYaw(l);
        entity.setPitch(m);
        entity.prevHeadYaw = n;
        entity.headYaw = o;
        context.disableScissor();
    }

    public static void drawEntity(DrawContext context, float x, float y, float size, Vector3f vector3f, Quaternionf quaternionf, @Nullable Quaternionf quaternionf2, int playerRotateIndex, LivingEntity entity) {
        context.getMatrices().push();
        context.getMatrices().translate((double)x, (double)y, (double)50.0F);
        context.getMatrices().scale(size, size, -size);
        context.getMatrices().translate(vector3f.x, vector3f.y, vector3f.z);
        context.getMatrices().multiply(quaternionf);
        context.getMatrices().multiply(new Quaternionf(new AxisAngle4f(45F * playerRotateIndex,0F,1F,0F)));
        context.draw();
        DiffuseLighting.method_34742();
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        if (quaternionf2 != null) {
            entityRenderDispatcher.setRotation(quaternionf2.conjugate(new Quaternionf()).rotateY((float)Math.PI));
        }

        entityRenderDispatcher.setRenderShadows(false);
        context.draw((vertexConsumers) -> {
            entityRenderDispatcher.render(entity, (double)0.0F, (double)0.0F, (double)0.0F, 1.0F, context.getMatrices(), vertexConsumers, 15728880);
        });
        context.draw();
        entityRenderDispatcher.setRenderShadows(true);
        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();
    }


    @Environment(EnvType.CLIENT)

    public static class ChromaRotateLButton extends ButtonWidget {
        final int index;

        public ChromaRotateLButton(final int x, final int y, final int index, final ButtonWidget.PressAction onPress) {
            super(x, y, 14, 14, Text.of("<"), onPress, DEFAULT_NARRATION_SUPPLIER);
            this.index = index;
            this.visible = true;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public static class ChromaRotateRButton extends ButtonWidget {
        final int index;

        public ChromaRotateRButton(final int x, final int y, final int index, final ButtonWidget.PressAction onPress) {
            super(x, y, 14, 14, Text.of(">"), onPress, DEFAULT_NARRATION_SUPPLIER);
            this.index = index;
            this.visible = true;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public static class ChromaConfirmButton extends ButtonWidget {
        final int index;

        public ChromaConfirmButton(final int x, final int y, final int index, final ButtonWidget.PressAction onPress) {
            super(x, y, 100, 20,Text.translatable("gui.genetic_chimerism.chroma_menu.confirm"), onPress, DEFAULT_NARRATION_SUPPLIER);
            this.index = index;
            this.visible = true;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public static class ColorSlider extends SliderWidget {
        Text initMessage;
        boolean needsUpdate = false;

        public ColorSlider(int x, int y, int width, int height, Text text, double value) {
            super(x, y, width, height, text, value);
            this.initMessage = text;
            this.setMessage(initMessage.copy().append((int)(value*255)+"") );
        }

        @Override
        protected void updateMessage() {
            this.setMessage(initMessage.copy().append((int)(value*255)+"") );
        }

        @Override
        protected void applyValue() {
            needsUpdate = true;
        }

        protected void resetUpdate(){
            needsUpdate = false;
        }

        public int getValue() {
            return (int) (value*255);
        }
        public void setValue(double input) {
            value = input/255;
        }
    }
}
