package com.genetic_chimerism;

import com.genetic_chimerism.InfusionBlock.InfusionStationEntity;
import com.genetic_chimerism.SynthBlock.BasicSynthEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static void initialize() {
    }

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(GeneticChimerism.MOD_ID, path), blockEntityType);
    }


    public static final BlockEntityType<BasicSynthEntity> MUTAGEN_SYNTHESIZER_BLOCK_ENTITY = register(
            "mutagen_synthesizer",
            FabricBlockEntityTypeBuilder.create(BasicSynthEntity::new, ModBlocks.MUTAGEN_SYNTHESIZER).build());

    public static final BlockEntityType<InfusionStationEntity> INFUSION_STATION_BLOCK_ENTITY = register(
            "infusion_station",
            FabricBlockEntityTypeBuilder.create(InfusionStationEntity::new, ModBlocks.INFUSION_STATION).build());

}
