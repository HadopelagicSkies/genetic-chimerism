package com.genetic_chimerism.entity;

import com.genetic_chimerism.GeneticChimerismEntities;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DiplocaulusEntity extends AxolotlEntity {
    public DiplocaulusEntity(EntityType<? extends AxolotlEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        boolean bl = false;
        Random random = world.getRandom();
        if (entityData instanceof AxolotlData) {
            if (((AxolotlData) entityData).getSpawnedCount() >= 2 || spawnReason == SpawnReason.BREEDING) {
                bl = true;
            }
        } else {
            entityData = new AxolotlData(new Variant[]{AxolotlEntity.Variant.getRandomNatural(random), AxolotlEntity.Variant.getRandomNatural(random)});
        }

        this.setVariant(((AxolotlData) entityData).getRandomVariant(random));
        if (bl) {
            this.setBreedingAge(-24000);
        }

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public int getMaxAir() {
        return 12000;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.AXOLOTL_FOOD) || stack.isOf(Items.TROPICAL_FISH);
    }

    @Override
    protected void eat(PlayerEntity player, Hand hand, ItemStack stack) {
        if (stack.isOf(Items.TROPICAL_FISH_BUCKET)) {
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.WATER_BUCKET)));
        } else if (stack.isOf(Items.TROPICAL_FISH)) {
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, ItemStack.EMPTY));
        }else {
            super.eat(player, hand, stack);
        }

    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        DiplocaulusEntity diplocaulusEntity = GeneticChimerismEntities.DIPLOCAULUS.create(world, SpawnReason.BREEDING);
        if (diplocaulusEntity != null) {
            Variant variant;
            if (shouldBabyBeDifferent(this.random)) {
                variant = AxolotlEntity.Variant.getRandomUnnatural(this.random);
            } else {
                variant = this.random.nextBoolean() ? this.getVariant() : ((AxolotlEntity) entity).getVariant();
            }
            diplocaulusEntity.setVariant(variant);
            diplocaulusEntity.setPersistent();
        }
        return diplocaulusEntity;
    }

    private static boolean shouldBabyBeDifferent(Random random) {
        return random.nextInt(1200) == 0;
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
    }

    public static DefaultAttributeContainer.Builder createDiplocaulusAttributes() {
        return AnimalEntity.createAnimalAttributes().add(EntityAttributes.MAX_HEALTH, (double) 23.0F).add(EntityAttributes.MOVEMENT_SPEED, (double) 1.2F).add(EntityAttributes.ATTACK_DAMAGE, (double) 3.0F).add(EntityAttributes.STEP_HEIGHT, (double) 1.0F);
    }
}
