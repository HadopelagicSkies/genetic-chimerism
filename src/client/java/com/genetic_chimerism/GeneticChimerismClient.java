package com.genetic_chimerism;

import com.genetic_chimerism.entity.DiplocaulusEntityModel;
import com.genetic_chimerism.entity.DiplocaulusEntityRenderer;
import com.genetic_chimerism.mutation_setup.*;
import com.genetic_chimerism.mutation_setup_client.MutationClient;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;


public class GeneticChimerismClient implements ClientModInitializer {
	private static KeyBinding headActionKeybindings;
	private static KeyBinding torsoActionKeybindings;
	private static KeyBinding armActionKeybindings;
	private static KeyBinding legActionKeybindings;
	private static KeyBinding tailActionKeybindings;
	private static KeyBinding chromaMenuKeybindings;
	public static final EntityModelLayer DIPLOCAULUS_MODEL_LAYER = new EntityModelLayer(Identifier.of(GeneticChimerism.MOD_ID,"diplocaulus"), "diplocaulus");
	public static final EntityModelLayer DIPLOCAULUS_BABY_MODEL_LAYER = new EntityModelLayer(Identifier.of(GeneticChimerism.MOD_ID,"diplocaulus"), "diplocaulus_baby");

	@Override
	public void onInitializeClient() {
		HandledScreens.register(GeneticChimerism.SYNTH_SCREEN_HANDLER, SynthScreen::new);
		BlockRenderLayerMap.INSTANCE.putBlock(GeneticChimerismBlocks.MUTAGEN_SYNTHESIZER,RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(GeneticChimerismBlocks.INFUSION_STATION,RenderLayer.getTranslucent());
		EntityRendererRegistry.register(GeneticChimerismEntities.DIPLOCAULUS,(context) -> new DiplocaulusEntityRenderer(context) {});
		EntityModelLayerRegistry.registerModelLayer(DIPLOCAULUS_MODEL_LAYER, DiplocaulusEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(DIPLOCAULUS_BABY_MODEL_LAYER, DiplocaulusEntityModel::getBabyTexturedModelData);

		MutationTreesClient.initialize();
		initKeybindings();
	}

	public void initKeybindings(){
		headActionKeybindings = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.genetic_chimerism.head_action",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_Z,
				"category.genetic_chimerism.keybindings"));

		torsoActionKeybindings = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.genetic_chimerism.torso_action",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_X,
				"category.genetic_chimerism.keybindings"));

		armActionKeybindings = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.genetic_chimerism.arm_action",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_C,
				"category.genetic_chimerism.keybindings"));

		legActionKeybindings = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.genetic_chimerism.leg_action",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_V,
				"category.genetic_chimerism.keybindings"));

		tailActionKeybindings = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.genetic_chimerism.tail_action",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_B,
				"category.genetic_chimerism.keybindings"));

		chromaMenuKeybindings = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.genetic_chimerism.chroma_menu",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_O,
				"category.genetic_chimerism.keybindings"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null) {
				processActionKeybind(client.player, headActionKeybindings, MutatableParts.HEAD);
				processActionKeybind(client.player, torsoActionKeybindings, MutatableParts.TORSO);
				processActionKeybind(client.player, armActionKeybindings, MutatableParts.ARM);
				processActionKeybind(client.player, legActionKeybindings, MutatableParts.LEG);
				processActionKeybind(client.player, tailActionKeybindings, MutatableParts.TAIL);

				while (chromaMenuKeybindings.wasPressed()) {
					if (MutationAttachments.getMutationsAttached(client.player).contains(new MutationInfo("chroma", "tentacled")))
						client.setScreen(new ChromaScreen(client.player));
				}
			}
		});
	}

	private static void processActionKeybind(ClientPlayerEntity player, KeyBinding keyBinding, MutatableParts part) {
		while (keyBinding.wasPressed()) {
			MutationBodyInfo mut = MutationAttachments.getPartAttached(player, part);
			if (mut != null) {
				MutationClient clientMut = MutationTreesClient.mutationFromCodec(mut);
				if (clientMut != null) {
					clientMut.mutationAction(player);
					ClientPlayNetworking.send(new MutActionPayload(true, part));
				}
			}
		}

	}
}
