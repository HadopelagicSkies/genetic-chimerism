package com.genetic_chimerism;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;


public class GeneticChimerismClient implements ClientModInitializer {


	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		HandledScreens.register(GeneticChimerism.SYNTH_SCREEN_HANDLER, SynthScreen::new);
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MUTAGEN_SYNTHESIZER,RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFUSION_STATION,RenderLayer.getTranslucent());
		/*ClientPlayNetworking.registerGlobalReceiver(GeneticChimerism.INITIAL_SYNC, (client, handler, buf, responseSender) -> {
			playerData.mutationList = buf.readNbt();
		});*/
	}
}