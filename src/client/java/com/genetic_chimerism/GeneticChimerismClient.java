package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationAttachments;
import com.genetic_chimerism.mutation_setup.MutationInfo;
import com.genetic_chimerism.mutation_setup_client.MutationTreesClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;


public class GeneticChimerismClient implements ClientModInitializer {
	private static KeyBinding headActionKeybindings;
	private static KeyBinding torsoActionKeybindings;
	private static KeyBinding armActionKeybindings;
	private static KeyBinding legActionKeybindings;
	private static KeyBinding tailActionKeybindings;

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		HandledScreens.register(GeneticChimerism.SYNTH_SCREEN_HANDLER, SynthScreen::new);
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MUTAGEN_SYNTHESIZER,RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFUSION_STATION,RenderLayer.getTranslucent());


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

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (headActionKeybindings.wasPressed()) {
				MutationInfo headMut = client.player.getAttached(MutationAttachments.HEAD_MUTATION);
				if(headMut != null) {
					MutationTreesClient.mutationFromCodec(headMut).mutationAction(client.player);
					ClientPlayNetworking.send(new MutActionPayload(true,"head"));
				}
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (torsoActionKeybindings.wasPressed()) {
				MutationInfo torsoMut = client.player.getAttached(MutationAttachments.TORSO_MUTATION);
				if(torsoMut != null) {
					MutationTreesClient.mutationFromCodec(torsoMut).mutationAction(client.player);
					ClientPlayNetworking.send(new MutActionPayload(true,"torso"));
				}
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (armActionKeybindings.wasPressed()) {
				MutationInfo armMut = client.player.getAttached(MutationAttachments.ARM_MUTATION);
				if(armMut != null) {
					MutationTreesClient.mutationFromCodec(armMut).mutationAction(client.player);
					ClientPlayNetworking.send(new MutActionPayload(true,"arm"));
				}
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (legActionKeybindings.wasPressed()) {
				MutationInfo legMut = client.player.getAttached(MutationAttachments.LEG_MUTATION);
				if(legMut != null) {
					MutationTreesClient.mutationFromCodec(legMut).mutationAction(client.player);
					ClientPlayNetworking.send(new MutActionPayload(true,"leg"));
				}
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (tailActionKeybindings.wasPressed()) {
				MutationInfo tailMut = client.player.getAttached(MutationAttachments.TAIL_MUTATION);
				if(tailMut != null) {
					MutationTreesClient.mutationFromCodec(tailMut).mutationAction(client.player);
					ClientPlayNetworking.send(new MutActionPayload(true,"tail"));
				}
			}
		});
	}
}