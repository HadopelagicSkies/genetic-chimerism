package com.genetic_chimerism;

import net.minecraft.text.Text;
import net.minecraft.util.StringIdentifiable;

public enum MutatableParts implements StringIdentifiable {
    HEAD("head"),
    TORSO("torso"),
    ARM("arm"),
    LEG("leg"),
    TAIL("tail");

    private final String name;
    private final Text translatableName;

    MutatableParts(String name) {
        this.name = name;
        this.translatableName = Text.translatable(GeneticChimerism.MOD_ID+"."+name+"_part");
    }

    public Text getTranslatableName() {
        return translatableName;
    }

    @Override
    public String asString() {
        return name;
    }
}
