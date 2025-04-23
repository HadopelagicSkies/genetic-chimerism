package com.genetic_chimerism.mutation_setup;

import com.genetic_chimerism.GeneticChimerism;
import com.genetic_chimerism.MutatableParts;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.types.Type;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class WoolenTree {
    public static final MutationTrees woolen = MutationTrees.addTree(new ArrayList<Mutation>(), "woolen", Identifier.ofVanilla("textures/mob_effect/levitation.png"));

    public static void initialize() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (player.getStackInHand(hand).isOf(Items.SHEARS) && player.isSneaking())
                if (MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(woolCoat))) {
                    MutationBodyInfo miscMutation = MutationAttachments.getPartAttached(player, MutatableParts.MISC);
                    if (miscMutation.growth() >= woolCoat.getMaxGrowth()) {
                        int woolCount = player.getRandom().nextBetween(1, 3);
                        DyeColor closestDyeColor = getDyeColor(miscMutation);
                        Item woolItem = Registries.ITEM.get(Identifier.ofVanilla(closestDyeColor.getName() + "_wool"));
                        ItemStack itemStack = new ItemStack(woolItem == null ? Items.WHITE_WOOL : woolItem, woolCount);
                        world.spawnEntity(new ItemEntity(world, player.getX(), player.getY(), player.getZ(), itemStack));

                        MutationAttachments.setPartGrowth(player, MutatableParts.MISC,0);
                    }
                }
            return ActionResult.PASS;
        });

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (player.getStackInHand(hand).isOf(Items.SHEARS) && entity instanceof PlayerEntity)
                if (MutationAttachments.getMutationsAttached(entity).contains(MutationTrees.mutationToCodec(woolCoat))) {
                    MutationBodyInfo miscMutation = MutationAttachments.getPartAttached(entity, MutatableParts.MISC);
                    if (miscMutation.growth() >= woolCoat.getMaxGrowth()) {
                        int woolCount = player.getRandom().nextBetween(1, 3);
                        DyeColor closestDyeColor = getDyeColor(miscMutation);
                        Item woolItem = Registries.ITEM.get(Identifier.ofVanilla(closestDyeColor.getName() + "_WOOL"));
                        ItemStack itemStack = new ItemStack(woolItem == null ? Items.WHITE_WOOL : woolItem, woolCount);
                        world.spawnEntity(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), itemStack));

                        MutationAttachments.setPartGrowth(player, MutatableParts.TAIL,0);
                    }
                }
            return ActionResult.PASS;
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (player.getStackInHand(hand).isOf(Items.AIR) && player.isSneaking()) {
                List<MutationInfo> mutList = MutationAttachments.getMutationsAttached(player);
                if (mutList.contains(MutationTrees.mutationToCodec(eatGrass))) {
                    BlockState block = player.getWorld().getBlockState(hitResult.getBlockPos());
                    boolean ateGrass = false;
                    if (block.isOf(Blocks.GRASS_BLOCK) || block.isOf(Blocks.SHORT_GRASS) || block.isOf(Blocks.TALL_GRASS)) {
                        if(player.getHungerManager().isNotFull()||player.isCreative()) {
                            ateGrass = true;
                        }
                    }
                    if (ateGrass) {
                        if (block.isOf(Blocks.GRASS_BLOCK)) {
                            world.setBlockState(hitResult.getBlockPos(), Blocks.DIRT.getDefaultState(), Block.NOTIFY_LISTENERS);
                        } else if (block.isOf(Blocks.SHORT_GRASS)||block.isOf(Blocks.TALL_GRASS)) {
                            world.breakBlock(hitResult.getBlockPos(), false);
                        }
                        new FoodComponent(2, 3.6F, true).onConsume(world,player,null, ConsumableComponent.builder().sound(SoundEvents.ENTITY_GENERIC_EAT).build());
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, MathHelper.nextBetween(player.getRandom(), 0.9F, 1.0F));

                        if (MutationAttachments.getMutationsAttached(player).contains(MutationTrees.mutationToCodec(woolCoat))) {
                            MutationBodyInfo miscPart = MutationAttachments.getPartAttached(player, MutatableParts.MISC);
                            if(miscPart.growth()<woolCoat.getMaxGrowth()) {
                                MutationAttachments.setPartGrowth(player, MutatableParts.MISC, miscPart.growth() + 100);
                                if(miscPart.growth()>woolCoat.getMaxGrowth()){
                                    MutationAttachments.setPartGrowth(player, MutatableParts.MISC, woolCoat.getMaxGrowth());
                                }
                            }
                        }
                    }
                }
            }
            return ActionResult.PASS;
        });
    }

    private static DyeColor getDyeColor(MutationBodyInfo miscMutation) {
        Vector3f woolVector = ColorHelper.toVector(miscMutation.color1());
        float minDistance = Float.MAX_VALUE;
        DyeColor closestDyeColor = DyeColor.WHITE;
        for (DyeColor dyeColor : DyeColor.values()) {
            Vector3f dyeVector = ColorHelper.toVector(dyeColor.getEntityColor());
            float distance = woolVector.distance(dyeVector);
            if (distance < minDistance) {
                minDistance = distance;
                closestDyeColor = dyeColor;
            }
        }
        return closestDyeColor;
    }

    public static final Mutation expResist1 = woolen.addToTree(new ExpResist1Mutation("expResist1", "woolen", null));
    public static final Mutation expResist2 = woolen.addToTree(new ExpResist2Mutation("expResist2", "woolen", expResist1));
    public static final Mutation expResist3 = woolen.addToTree(new ExpResist3Mutation("expResist3", "woolen", expResist2));
    public static final Mutation expResist4 = woolen.addToTree(new ExpResist4Mutation("expResist4", "woolen", expResist3));

    public static final Mutation lanolinRegen = woolen.addToTree(new LanolinRegenMutation("lanolinRegen", "woolen", expResist3));
    public static final Mutation coldResist = woolen.addToTree(new Mutation("coldResist", "woolen", expResist3));
    public static final Mutation snowWalk = woolen.addToTree(new Mutation("snowWalk", "woolen", coldResist));

    public static final Mutation eatGrass = woolen.addToTree(new Mutation("eatGrass", "woolen", expResist1));
    public static final Mutation woolCoat = woolen.addToTree(new WoolCoatMutation("woolCoat", "woolen", eatGrass, MutatableParts.MISC));
    public static final Mutation bouncyLanding = woolen.addToTree(new Mutation("bouncyLanding", "woolen", eatGrass));

    public static class ExpResist1Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "expresist1_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public ExpResist1Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class ExpResist2Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "expresist2_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public ExpResist2Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class ExpResist3Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "expresist3_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public ExpResist3Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class ExpResist4Mutation extends Mutation {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier(Identifier.of(GeneticChimerism.MOD_ID, "expresist4_modifier"), 0.25, EntityAttributeModifier.Operation.ADD_VALUE);

        public ExpResist4Mutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
            modifierMultimap.put(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, MODIFIER);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(modifierMultimap);
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            player.getAttributes().removeModifiers(modifierMultimap);
        }
    }

    public static class WoolCoatMutation extends Mutation {

        public WoolCoatMutation(String mutID, String treeID, Mutation prereq, MutatableParts parts) {
            super(mutID, treeID, prereq, parts);
        }

        @Override
        public void onApplied(PlayerEntity player) {
            MutationAttachments.removePartAttached(player, MutatableParts.MISC);
            MutationAttachments.setPartAttached(player, MutatableParts.MISC, MutationTrees.mutationToCodec(woolCoat, 0,
                    ColorHelper.getArgb(255, 255, 255), ColorHelper.getArgb(255, 255, 255), 0, false, false));
        }

        @Override
        public void onRemoved(PlayerEntity player) {
            MutationAttachments.setPartReceding(player, MutatableParts.MISC,true);
        }

        @Override
        public int getMaxGrowth() {
            return 400;
        }
    }

    public static class LanolinRegenMutation extends Mutation{
        public LanolinRegenMutation(String mutID, String treeID, Mutation prereq) {
            super(mutID, treeID, prereq);
        }

        @Override
        public void tick(PlayerEntity player) {
            if (player.getHealth() < player.getMaxHealth()){
                if (player.getRandom().nextBetween(0,600) < 1)
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 150,1));
            }
        }
    }
}
