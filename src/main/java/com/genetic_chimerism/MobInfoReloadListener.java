package com.genetic_chimerism;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MobInfoReloadListener implements SimpleResourceReloadListener {
    private static final ResourceFinder finder = new ResourceFinder("resources.data.genetic_chimerism.mob_tissue_data", ".json");
    private static final Identifier id = Identifier.of(GeneticChimerism.MOD_ID,"resources.data.genetic_chimerism.mob_tissue_data");
    private static final Map<Identifier,MobInfoData> results = new HashMap<>();
    private static final DynamicOps<JsonElement> ops;

    public void initialize(){
        ResourceManagerHelperImpl.registerReloadListener(new MobInfoReloadListener());
    }


    @Override
    public CompletableFuture load(ResourceManager resourceManager, Executor executor) {
        JsonDataLoader.load(resourceManager, finder, ops, MobInfoData.MOB_INFO_DATA_CODEC.listOf(), results);
        return null;
    }

    @Override
    public CompletableFuture<Void> apply(Object o, ResourceManager resourceManager, Executor executor) {
        JsonDataLoader.apply(o,resourceManager,executor);
        return null;
    }

    @Override
    public Identifier getFabricId() {
        return id;
    }

    public record MobInfoData(String mobID, String treeID, String tier) {
        //grabbing this list will be the Actual Big List to reference against
        private static final List<MobInfoData> mobInfo = new ArrayList<>();

        public static final Codec<MobInfoData> MOB_INFO_DATA_CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.STRING.fieldOf("mobID").forGetter(MobInfoData::mobID),
                Codec.STRING.fieldOf("treeID").forGetter(MobInfoData::treeID),
                Codec.STRING.fieldOf("tier").forGetter(MobInfoData::tier)
        ).apply(i, MobInfoData::new));

        public void addMobInfo() {
            mobInfo.add( new MobInfoData(this.mobID, this.treeID, this.tier));
        }

        public List<MobInfoData> getMobInfo() {
            return mobInfo;
        }
    }
}
