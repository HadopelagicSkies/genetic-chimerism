package com.genetic_chimerism;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import java.util.HashMap;
import java.util.Map;

public class MobInfoReloadListener extends JsonDataLoader<MobInfoReloadListener.MobInfoData> implements IdentifiableResourceReloadListener {
    private static final Identifier ID = Identifier.of(GeneticChimerism.MOD_ID,"mob_tissue_data");
    private static final Map<Identifier,MobInfoData> results = new HashMap<>();
    private static final Map<Identifier,MobInfoData> mappedMobData = new HashMap<>();

    public static void initialize(){
        ResourceManagerHelper.get(ResourceType.SERVER_DATA)
                .registerReloadListener(new MobInfoReloadListener());
    }

    public MobInfoReloadListener() {
        super(MobInfoData.MOB_INFO_DATA_CODEC, ResourceFinder.json("genetic_chimerism/mob_tissue_data"));
    }

    public static void remapResults(){
        mappedMobData.clear();
        results.forEach((Identifier i, MobInfoData info ) -> mappedMobData.put(Identifier.of(info.mobID),info));
    }

    @Override
    protected void apply(Map<Identifier, MobInfoData> prepared, ResourceManager manager, Profiler profiler) {
        results.clear();
        results.putAll(prepared);
    }

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    public static Map<Identifier, MobInfoData> getMappedMobData() {
        return mappedMobData;
    }

    public record MobInfoData(String mobID, String treeID, String tier) {

        public static final Codec<MobInfoData> MOB_INFO_DATA_CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.STRING.fieldOf("mobID").forGetter(MobInfoData::mobID),
                Codec.STRING.fieldOf("treeID").forGetter(MobInfoData::treeID),
                Codec.STRING.fieldOf("tier").forGetter(MobInfoData::tier)
        ).apply(i, MobInfoData::new));

    }
}
