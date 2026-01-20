package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.MutatableParts;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.genetic_chimerism.GeneticChimerism.MOD_ID;

@SuppressWarnings("UnstableApiUsage")
public class MutationAttachments {
    public static void initialize() {
    }

    public static final AttachmentType<List<MutationInfo>> PLAYER_MUTATION_LIST = AttachmentRegistry.create(Identifier.of(MOD_ID, "player_mutation_list"), listBuilder ->
            listBuilder.initializer(ArrayList::new)
                    .persistent(Codec.list(MutationInfo.MUTATION_CODEC))
                    .copyOnDeath()
                    .syncWith(MutationInfo.MUTATION_PACKET_CODEC.collect(PacketCodecs.toList()), AttachmentSyncPredicate.targetOnly()));

    public static final Map<MutatableParts, AttachmentType<MutationBodyInfo>> PART_MUTATIONS = Util.mapEnum(MutatableParts.class,
        part -> AttachmentRegistry.create(Identifier.of(MOD_ID, part.asString() + "_mutation"), infoBuilder ->
        infoBuilder.initializer(() -> null)
            .persistent(MutationBodyInfo.MUTATION_BODY_CODEC)
            .copyOnDeath()
            .syncWith(MutationBodyInfo.MUTATION_BODY_PACKET_CODEC, AttachmentSyncPredicate.targetOnly())));

    public static final AttachmentType<Boolean> PLAYER_CENTAUR_SADDLED = AttachmentRegistry.create(Identifier.of(MOD_ID, "player_centaur_saddled"), booleanBuilder ->
            booleanBuilder.initializer(() -> false)
                    .persistent(Codec.BOOL)
                    .copyOnDeath()
                    .syncWith(PacketCodecs.BOOLEAN, AttachmentSyncPredicate.targetOnly()));

    public static final List<ArmorMaterial> horseArmors = List.of(ArmorMaterials.LEATHER,/*ArmorMaterials.COPPER,*/ArmorMaterials.IRON,ArmorMaterials.GOLD,ArmorMaterials.DIAMOND,ArmorMaterials.NETHERITE);

    public static final AttachmentType<Integer> PLAYER_CENTAUR_ARMOR = AttachmentRegistry.create(Identifier.of(MOD_ID, "player_centaur_armor"), integerBuilder ->
            integerBuilder.initializer(() -> -1)
                    .persistent(Codec.INT)
                    .copyOnDeath()
                    .syncWith(PacketCodecs.INTEGER, AttachmentSyncPredicate.targetOnly()));

    public static List<MutationInfo> getMutationsAttached(AttachmentTarget target) {
        return target.getAttached(PLAYER_MUTATION_LIST);
    }

    public static void setMutationsAttached(AttachmentTarget target, List<MutationInfo> info) {
        target.setAttached(PLAYER_MUTATION_LIST, info);
    }

    public static MutationBodyInfo getPartAttached(AttachmentTarget target, MutatableParts part) {
        return target.getAttached(PART_MUTATIONS.get(part));
    }

    public static void setPartAttached(AttachmentTarget target, MutatableParts part, MutationBodyInfo info) {
        target.setAttached(PART_MUTATIONS.get(part), info);
    }

    public static void removePartAttached(AttachmentTarget target, MutatableParts part) {
        target.removeAttached(PART_MUTATIONS.get(part));
    }

    public static void setPartVisuals(AttachmentTarget target, MutatableParts part, int patternIndex, int color1, int color2){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), patternIndex,
                color1, color2, partInfo.growth(), partInfo.isReceding(),partInfo.partAnim(),partInfo.actionAnim()));
    }

    public static void setPartGrowth(AttachmentTarget target, MutatableParts part, int growth){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), partInfo.patternIndex(),
                partInfo.color1(), partInfo.color2(), growth, partInfo.isReceding(),partInfo.partAnim(),partInfo.actionAnim()));
    }

    public static void setPartReceding(AttachmentTarget target, MutatableParts part, boolean isReceding){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        if (partInfo != null)
            MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), partInfo.patternIndex(),
                    partInfo.color1(), partInfo.color2(), partInfo.growth(), isReceding,partInfo.partAnim(),partInfo.actionAnim()));
    }

    public static void setPartAnimating(AttachmentTarget target, MutatableParts part, boolean isAnimating, int startTick){
        MutationBodyInfo partInfo = MutationAttachments.getPartAttached(target, part);
        MutationAttachments.setPartAttached(target, part , new MutationBodyInfo(partInfo.mutID(), partInfo.treeID(), partInfo.patternIndex(),
                partInfo.color1(), partInfo.color2(), partInfo.growth(), partInfo.isReceding(),
                MutationBodyInfo.animationStateFromInts(1,startTick),
                MutationBodyInfo.animationStateFromInts(isAnimating?1:0,startTick)));
    }

    public static boolean getCentaurSaddled(AttachmentTarget target){
        return Boolean.TRUE.equals(target.getAttached(PLAYER_CENTAUR_SADDLED));
    }

    public static void setCentaurSaddled(AttachmentTarget target, boolean saddled){
        target.setAttached(PLAYER_CENTAUR_SADDLED,saddled);
    }

    public static Item horseArmorFromMaterial(ArmorMaterial material){
        if(material.equals(ArmorMaterials.LEATHER))
            return Items.LEATHER_HORSE_ARMOR;
        /*else if(material.equals(ArmorMaterials.COPPER))
            return Items.COPPER_HORSE_ARMOR;*/
        else if(material.equals(ArmorMaterials.IRON))
            return Items.IRON_HORSE_ARMOR;
        else if(material.equals(ArmorMaterials.GOLD))
            return Items.GOLDEN_HORSE_ARMOR;
        else if(material.equals(ArmorMaterials.DIAMOND))
            return Items.DIAMOND_HORSE_ARMOR;
        /*else if(material.equals(ArmorMaterials.NETHERITE))
            return Items.NETHERITE_HORSE_ARMOR;*/
        else
            return Items.AIR;
    }

    public static ArmorMaterial materialFromHorseArmor(Item horseArmor){
        if(horseArmor.equals(Items.LEATHER_HORSE_ARMOR))
            return ArmorMaterials.LEATHER;
        /*else if(horseArmor.equals(Items.COPPER_HORSE_ARMOR))
            return ArmorMaterials.COPPER;*/
        else if(horseArmor.equals(Items.IRON_HORSE_ARMOR))
            return ArmorMaterials.IRON;
        else if(horseArmor.equals(Items.GOLDEN_HORSE_ARMOR))
            return ArmorMaterials.GOLD;
        else if(horseArmor.equals(Items.DIAMOND_HORSE_ARMOR))
            return ArmorMaterials.DIAMOND;
        /*else if(horseArmor.equals(Items.NETHERITE_HORSE_ARMOR))
            return ArmorMaterials.NETHERITE;*/
        else
            return ArmorMaterials.CHAIN;
    }

    public static Item getCentaurArmor(AttachmentTarget target){
        int indexValue = target.getAttached(PLAYER_CENTAUR_ARMOR) != null ? target.getAttached(PLAYER_CENTAUR_ARMOR):-1;
        if(indexValue  > -1) {
            return horseArmorFromMaterial(horseArmors.get(indexValue));
        }
        return Items.AIR;
    }

    public static void setCentaurArmor(AttachmentTarget target, Item item){
        target.setAttached(PLAYER_CENTAUR_ARMOR, horseArmors.indexOf(materialFromHorseArmor(item)));
    }

}
