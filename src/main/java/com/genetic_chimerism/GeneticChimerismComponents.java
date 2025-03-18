package com.genetic_chimerism;

import com.genetic_chimerism.mutation_setup.MutationInfo;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class GeneticChimerismComponents {
    protected static void initialize() {
        GeneticChimerism.LOGGER.info("Registering {} components", GeneticChimerism.MOD_ID);
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }

    public static final ComponentType<String> TISSUE_TYPE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(GeneticChimerism.MOD_ID, "tissue_type"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static final ComponentType<MutationInfo> MUTATION_STORED = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(GeneticChimerism.MOD_ID, "mutation_stored"),
            ComponentType.<MutationInfo>builder().codec(MutationInfo.MUTATION_CODEC).build()
    );

}