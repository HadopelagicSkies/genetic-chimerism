package com.genetic_chimerism.entity;

import com.genetic_chimerism.GeneticChimerismEntities;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.AxolotlBrain;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.DebugInfoSender;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;

public class DiplocaulusEntity extends AnimalEntity {

    public static final int PLAY_DEAD_TICKS = 200;
    private static final int field_52482 = 10;
    protected static final ImmutableList<SensorType<? extends Sensor<? super DiplocaulusEntity>>> SENSORS;
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES;
    private static final TrackedData<Integer> VARIANT;
    private static final TrackedData<Boolean> PLAYING_DEAD;

    public static final SensorType<TemptationsSensor> DIPLOCAULUS_TEMPTATIONS = SensorType.register("diplocaulus_temptations", () -> new TemptationsSensor(DiplocaulusBrain.getTemptItemPredicate()));

    static {
        SENSORS = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_ADULT, SensorType.HURT_BY, SensorType.AXOLOTL_ATTACKABLES, DIPLOCAULUS_TEMPTATIONS);
        MEMORY_MODULES = ImmutableList.of(MemoryModuleType.BREED_TARGET, MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.NEAREST_VISIBLE_ADULT, new MemoryModuleType[]{MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.PLAY_DEAD_TICKS, MemoryModuleType.NEAREST_ATTACKABLE, MemoryModuleType.TEMPTING_PLAYER, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, MemoryModuleType.HAS_HUNTING_COOLDOWN, MemoryModuleType.IS_PANICKING});
        VARIANT = DataTracker.registerData(DiplocaulusEntity.class, TrackedDataHandlerRegistry.INTEGER);
        PLAYING_DEAD = DataTracker.registerData(DiplocaulusEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    public static final double BUFF_RANGE = (double)20.0F;
    public static final int BLUE_BABY_CHANCE = 1200;
    private static final int MAX_AIR = 12000;
    public static final String VARIANT_KEY = "Variant";
    private static final int HYDRATION_BY_POTION = 1800;
    private static final int MAX_REGENERATION_BUFF_DURATION = 2400;
    public final InterpolatedFlipFlop playingDeadFf = new InterpolatedFlipFlop(10, MathHelper::easeInOutSine);
    public final InterpolatedFlipFlop inWaterFf = new InterpolatedFlipFlop(10, MathHelper::easeInOutSine);
    public final InterpolatedFlipFlop onGroundFf = new InterpolatedFlipFlop(10, MathHelper::easeInOutSine);
    public final InterpolatedFlipFlop isMovingFf = new InterpolatedFlipFlop(10, MathHelper::easeInOutSine);
    private static final int BUFF_DURATION = 100;

    public DiplocaulusEntity(EntityType<DiplocaulusEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new DiplocaulusEntity.DiplocaulusMoveControl(this);
        this.lookControl = new DiplocaulusEntity.DiplocaulusLookControl(this, 20);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        boolean bl = false;
        Random random = world.getRandom();
        if (entityData instanceof DiplocaulusData) {
            if (((DiplocaulusData) entityData).getSpawnedCount() >= 2 || spawnReason == SpawnReason.BREEDING) {
                bl = true;
            }
        } else {
            entityData = new DiplocaulusData(new Variant[]{DiplocaulusEntity.Variant.getRandomNatural(random), DiplocaulusEntity.Variant.getRandomNatural(random)});
        }

        this.setVariant(((DiplocaulusData) entityData).getRandomVariant(random));
        if (bl) {
            this.setBreedingAge(-24000);
        }

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected void mobTick(ServerWorld world) {
        Profiler profiler = Profilers.get();
        profiler.push("diplocaulusBrain");
        this.getBrain().tick(world, this);
        profiler.pop();
        profiler.push("diplocaulusActivityUpdate");
        DiplocaulusBrain.updateActivities(this);
        profiler.pop();
        if (!this.isAiDisabled()) {
            Optional<Integer> optional = this.getBrain().getOptionalRegisteredMemory(MemoryModuleType.PLAY_DEAD_TICKS);
            this.setPlayingDead(optional.isPresent() && (Integer)optional.get() > 0);
        }

    }

    @Override
    public int getMaxAir() {
        return MAX_AIR;
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
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, stack.copyWithCount(stack.getCount() -1)));
        }else {
            super.eat(player, hand, stack);
        }

    }

    @Override
    public boolean canSpawn(WorldView world) {
        return super.canSpawn(world) && world.getBlockState(this.getBlockPos().down()).isIn(BlockTags.FROGS_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, this.getBlockPos());
    }



    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        DiplocaulusEntity diplocaulusEntity = GeneticChimerismEntities.DIPLOCAULUS.create(world, SpawnReason.BREEDING);
        if (diplocaulusEntity != null) {
            Variant variant;
            if (shouldBabyBeDifferent(this.random)) {
                variant = DiplocaulusEntity.Variant.getRandomUnnatural(this.random);
            } else {
                variant = this.random.nextBoolean() ? this.getVariant() : ((DiplocaulusEntity) entity).getVariant();
            }
            diplocaulusEntity.setVariant(variant);
            diplocaulusEntity.setPersistent();
        }
        return diplocaulusEntity;
    }

    private static boolean shouldBabyBeDifferent(Random random) {
        return random.nextInt(1200) == 0;
    }


    public static DefaultAttributeContainer.Builder createDiplocaulusAttributes() {
        return AnimalEntity.createAnimalAttributes().add(EntityAttributes.MAX_HEALTH, (double) 23.0F).add(EntityAttributes.MOVEMENT_SPEED, (double) 1.2F).add(EntityAttributes.ATTACK_DAMAGE, (double) 3.0F).add(EntityAttributes.STEP_HEIGHT, (double) 1.0F);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return 0.0F;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
        builder.add(PLAYING_DEAD, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(DiplocaulusEntity.Variant.byId(nbt.getInt("Variant")));
    }

    @Override
    public void playAmbientSound() {
        if (!this.isPlayingDead()) {
            super.playAmbientSound();
        }
    }

    @Override
    public void baseTick() {
        int i = this.getAir();
        super.baseTick();
        if (!this.isAiDisabled()) {
            this.tickAir(i);
        }

        if (this.getWorld().isClient()) {
            this.tickClient();
        }

    }

    private void tickClient() {
        DiplocaulusEntity.State state;
        if (this.isPlayingDead()) {
            state = DiplocaulusEntity.State.PLAYING_DEAD;
        } else if (this.isInsideWaterOrBubbleColumn()) {
            state = DiplocaulusEntity.State.IN_WATER;
        } else if (this.isOnGround()) {
            state = DiplocaulusEntity.State.ON_GROUND;
        } else {
            state = DiplocaulusEntity.State.IN_AIR;
        }

        this.playingDeadFf.tick(state == DiplocaulusEntity.State.PLAYING_DEAD);
        this.inWaterFf.tick(state == DiplocaulusEntity.State.IN_WATER);
        this.onGroundFf.tick(state == DiplocaulusEntity.State.ON_GROUND);
        boolean bl = this.limbAnimator.isLimbMoving() || this.getPitch() != this.prevPitch || this.getYaw() != this.prevYaw;
        this.isMovingFf.tick(bl);
    }

    protected void tickAir(int air) {
        if (this.isAlive() && !this.isWet()) {
            this.setAir(air - 1);
            if (this.getAir() == -20) {
                this.setAir(0);
                this.serverDamage(this.getDamageSources().dryOut(), 2.0F);
            }
        } else {
            this.setAir(this.getMaxAir());
        }

    }


    public void hydrateFromPotion() {
        int i = this.getAir() + 1800;
        this.setAir(Math.min(i, this.getMaxAir()));
    }


    public DiplocaulusEntity.Variant getVariant() {
        return DiplocaulusEntity.Variant.byId((Integer)this.dataTracker.get(VARIANT));
    }

    public AxolotlEntity.Variant getAxolotlVariant() {
        return AxolotlEntity.Variant.byId((Integer)this.dataTracker.get(VARIANT));
    }

    public void setVariant(DiplocaulusEntity.Variant variant) {
        this.dataTracker.set(VARIANT, variant.getId());
    }

    public boolean isPushedByFluids() {
        return false;
    }

    public void setPlayingDead(boolean playingDead) {
        this.dataTracker.set(PLAYING_DEAD, playingDead);
    }

    public boolean isPlayingDead() {
        return (Boolean)this.dataTracker.get(PLAYING_DEAD);
    }

    public boolean canBeLeashed() {
        return true;
    }

    protected EntityNavigation createNavigation(World world) {
        return new AmphibiousSwimNavigation(this, world);
    }

    public void playAttackSound() {
        this.playSound(SoundEvents.ENTITY_AXOLOTL_ATTACK, 1.0F, 1.0F);
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        float f = this.getHealth();
        if (!this.isAiDisabled() && this.getWorld().random.nextInt(3) == 0 && ((float)this.getWorld().random.nextInt(3) < amount || f / this.getMaxHealth() < 0.5F) && amount < f && this.isTouchingWater() && (source.getAttacker() != null || source.getSource() != null) && !this.isPlayingDead()) {
            this.brain.remember(MemoryModuleType.PLAY_DEAD_TICKS, 200);
        }

        return super.damage(world, source, amount);
    }

    public int getMaxLookPitchChange() {
        return 1;
    }

    public int getMaxHeadRotation() {
        return 1;
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return super.interactMob(player, hand);
    }


    public boolean canTakeDamage() {
        return !this.isPlayingDead() && super.canTakeDamage();
    }

    public static void appreciatePlayer(ServerWorld world, DiplocaulusEntity diplocaulus, LivingEntity target) {
        if (target.isDead()) {
            DamageSource damageSource = target.getRecentDamageSource();
            if (damageSource != null) {
                Entity entity = damageSource.getAttacker();
                if (entity != null && entity.getType() == EntityType.PLAYER) {
                    PlayerEntity playerEntity = (PlayerEntity)entity;
                    List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, diplocaulus.getBoundingBox().expand((double)20.0F));
                    if (list.contains(playerEntity)) {
                        diplocaulus.buffPlayer(playerEntity);
                    }
                }
            }
        }

    }

    public void buffPlayer(PlayerEntity player) {
        StatusEffectInstance statusEffectInstance = player.getStatusEffect(StatusEffects.REGENERATION);
        if (statusEffectInstance == null || statusEffectInstance.isDurationBelow(2399)) {
            int i = statusEffectInstance != null ? statusEffectInstance.getDuration() : 0;
            int j = Math.min(2400, 100 + i);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, j, 0), this);
        }

        player.removeStatusEffect(StatusEffects.MINING_FATIGUE);
    }

    public boolean cannotDespawn() {
        return super.cannotDespawn();
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_AXOLOTL_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_AXOLOTL_DEATH;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return this.isTouchingWater() ? SoundEvents.ENTITY_AXOLOTL_IDLE_WATER : SoundEvents.ENTITY_AXOLOTL_IDLE_AIR;
    }

    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_AXOLOTL_SPLASH;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_AXOLOTL_SWIM;
    }

    protected Brain.Profile<DiplocaulusEntity> createBrainProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    protected Brain<DiplocaulusEntity> deserializeBrain(Dynamic<?> dynamic) {
        return DiplocaulusBrain.create(this.createBrainProfile().deserialize(dynamic));
    }

    public Brain<DiplocaulusEntity> getBrain() {
        return (Brain<DiplocaulusEntity>) super.getBrain();
    }

    protected void sendAiDebugData() {
        super.sendAiDebugData();
        DebugInfoSender.sendBrainDebugData(this);
    }

    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement() && this.isTouchingWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
        } else {
            super.travel(movementInput);
        }

    }

    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.hasCustomName();
    }

    @Nullable
    public LivingEntity getTarget() {
        return this.getTargetInBrain();
    }

    public static class DiplocaulusData extends PassiveEntity.PassiveData {
        public final DiplocaulusEntity.Variant[] variants;

        public DiplocaulusData(DiplocaulusEntity.Variant... variants) {
            super(false);
            this.variants = variants;
        }

        public DiplocaulusEntity.Variant getRandomVariant(Random random) {
            return this.variants[random.nextInt(this.variants.length)];
        }
    }

    class DiplocaulusLookControl extends YawAdjustingLookControl {
        public DiplocaulusLookControl(final DiplocaulusEntity diplocaulus, final int yawAdjustThreshold) {
            super(diplocaulus, yawAdjustThreshold);
        }

        public void tick() {
            if (!DiplocaulusEntity.this.isPlayingDead()) {
                super.tick();
            }

        }
    }

    static class DiplocaulusMoveControl extends AquaticMoveControl {
        private final DiplocaulusEntity diplocaulus;

        public DiplocaulusMoveControl(DiplocaulusEntity diplocaulus) {
            super(diplocaulus, 85, 10, 0.1F, 0.5F, false);
            this.diplocaulus = diplocaulus;
        }

        public void tick() {
            if (!this.diplocaulus.isPlayingDead()) {
                super.tick();
            }

        }
    }

    public static enum Variant implements StringIdentifiable {
        LUCY(0, "lucy", true),
        WILD(1, "wild", true),
        GOLD(2, "gold", true),
        CYAN(3, "cyan", true),
        BLUE(4, "blue", false);

        private static final IntFunction<DiplocaulusEntity.Variant> BY_ID = ValueLists.createIdToValueFunction(DiplocaulusEntity.Variant::getId, values(), ValueLists.OutOfBoundsHandling.ZERO);
        public static final Codec<DiplocaulusEntity.Variant> CODEC = StringIdentifiable.createCodec(DiplocaulusEntity.Variant::values);
        private final int id;
        private final String name;
        private final boolean natural;

        private Variant(final int id, final String name, final boolean natural) {
            this.id = id;
            this.name = name;
            this.natural = natural;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String asString() {
            return this.name;
        }

        public static DiplocaulusEntity.Variant byId(int id) {
            return (DiplocaulusEntity.Variant)BY_ID.apply(id);
        }

        public static DiplocaulusEntity.Variant getRandomNatural(Random random) {
            return getRandom(random, true);
        }

        public static DiplocaulusEntity.Variant getRandomUnnatural(Random random) {
            return getRandom(random, false);
        }

        private static DiplocaulusEntity.Variant getRandom(Random random, boolean natural) {
            DiplocaulusEntity.Variant[] variants = (DiplocaulusEntity.Variant[]) Arrays.stream(values()).filter((variant) -> variant.natural == natural).toArray(Variant[]::new);
            return (DiplocaulusEntity.Variant) Util.getRandom(variants, random);
        }
    }

    public static enum State {
        PLAYING_DEAD,
        IN_WATER,
        ON_GROUND,
        IN_AIR;

        private State() {
        }
    }
}
