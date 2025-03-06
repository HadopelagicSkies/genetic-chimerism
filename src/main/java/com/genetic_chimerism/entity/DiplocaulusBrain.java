package com.genetic_chimerism.entity;

import com.genetic_chimerism.GeneticChimerismEntities;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.function.Predicate;

public class DiplocaulusBrain {
    private static final UniformIntProvider WALK_TOWARD_ADULT_RANGE = UniformIntProvider.create(5, 16);
    private static final float BREEDING_SPEED = 0.2F;
    private static final float ON_LAND_SPEED = 0.15F;
    private static final float IDLE_SPEED = 0.5F;
    private static final float TARGET_APPROACHING_SPEED = 0.6F;
    private static final float ADULT_FOLLOWING_SPEED = 0.6F;

    public DiplocaulusBrain() {
    }

    protected static Brain<DiplocaulusEntity> create(Brain<DiplocaulusEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        addFightActivities(brain);
        addPlayDeadActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    public static void updateActivities(DiplocaulusEntity diplocaulus) {
        Brain<DiplocaulusEntity> brain = diplocaulus.getBrain();
        Activity activity = (Activity)brain.getFirstPossibleNonCoreActivity().orElse((Activity) null);
        if (activity != Activity.PLAY_DEAD) {
            brain.resetPossibleActivities(ImmutableList.of(Activity.PLAY_DEAD, Activity.FIGHT, Activity.IDLE));
            if (activity == Activity.FIGHT && brain.getFirstPossibleNonCoreActivity().orElse((Activity) null) != Activity.FIGHT) {
                brain.remember(MemoryModuleType.HAS_HUNTING_COOLDOWN, true, 2400L);
            }
        }

    }

    private static void addIdleActivities(Brain<DiplocaulusEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(Pair.of(0, LookAtMobWithIntervalTask.follow(EntityType.PLAYER, 6.0F, UniformIntProvider.create(30, 60))),
                Pair.of(1, new BreedTask(GeneticChimerismEntities.DIPLOCAULUS, BREEDING_SPEED, 2)),
                Pair.of(2, new RandomTask(ImmutableList.of(Pair.of(new TemptTask(DiplocaulusBrain::getTemptedSpeed),1), Pair.of(WalkTowardsClosestAdultTask.create(WALK_TOWARD_ADULT_RANGE,DiplocaulusBrain::getAdultFollowingSpeed), 1)))), Pair.of(3, UpdateAttackTargetTask.create(DiplocaulusBrain::getAttackTarget)), Pair.of(3, SeekWaterTask.create(6, 0.15F)),
                Pair.of(4, new CompositeTask(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), ImmutableSet.of(), CompositeTask.Order.ORDERED, CompositeTask.RunMode.TRY_ALL, ImmutableList.of(Pair.of(StrollTask.createDynamicRadius(0.5F), 2), Pair.of(StrollTask.create(0.15F, false), 2), Pair.of(GoToLookTargetTask.create(DiplocaulusBrain::canGoToLookTarget, DiplocaulusBrain::getTemptedSpeed, 3), 3), Pair.of(TaskTriggerer.predicate(Entity::isInsideWaterOrBubbleColumn), 5), Pair.of(TaskTriggerer.predicate(Entity::isOnGround), 5))))));
    }

    private static void addCoreActivities(Brain<DiplocaulusEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(new UpdateLookControlTask(45, 90), new MoveToTargetTask(), PlayDeadTimerTask.create(), new TickCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)));
    }

    private static void addPlayDeadActivities(Brain<DiplocaulusEntity> brain) {
        brain.setTaskList(Activity.PLAY_DEAD, ImmutableList.of(
                Pair.of(0, new PlayDeadTask()),
                Pair.of(1, ForgetTask.create(TargetUtil::hasBreedTarget, MemoryModuleType.PLAY_DEAD_TICKS))),
                ImmutableSet.of(Pair.of(MemoryModuleType.PLAY_DEAD_TICKS, MemoryModuleState.VALUE_PRESENT)),
                ImmutableSet.of(MemoryModuleType.PLAY_DEAD_TICKS));
    }

    private static void addFightActivities(Brain<DiplocaulusEntity> brain) {
        brain.setTaskList(Activity.FIGHT, 0, ImmutableList.of(ForgetAttackTargetTask.create(DiplocaulusEntity::appreciatePlayer), RangedApproachTask.create(DiplocaulusBrain::getTargetApproachingSpeed), MeleeAttackTask.create(20), ForgetTask.create(TargetUtil::hasBreedTarget, MemoryModuleType.ATTACK_TARGET)), MemoryModuleType.ATTACK_TARGET);
    }
    private static float getTargetApproachingSpeed(LivingEntity entity) {
        return entity.isInsideWaterOrBubbleColumn() ? TARGET_APPROACHING_SPEED : ON_LAND_SPEED;
    }

    private static float getAdultFollowingSpeed(LivingEntity entity) {
        return entity.isInsideWaterOrBubbleColumn() ? ADULT_FOLLOWING_SPEED : ON_LAND_SPEED;
    }

    private static float getTemptedSpeed(LivingEntity entity) {
        return entity.isInsideWaterOrBubbleColumn() ? IDLE_SPEED : ON_LAND_SPEED;
    }

    private static Optional<? extends LivingEntity> getAttackTarget(ServerWorld world, DiplocaulusEntity diplocaulus) {
        return TargetUtil.hasBreedTarget(diplocaulus) ? Optional.empty() : diplocaulus.getBrain().getOptionalRegisteredMemory(MemoryModuleType.NEAREST_ATTACKABLE);
    }

    private static boolean canGoToLookTarget(LivingEntity entity) {
        World world = entity.getWorld();
        Optional<LookTarget> optional = entity.getBrain().getOptionalRegisteredMemory(MemoryModuleType.LOOK_TARGET);
        if (optional.isPresent()) {
            BlockPos blockPos = ((LookTarget)optional.get()).getBlockPos();
            return world.isWater(blockPos) == entity.isInsideWaterOrBubbleColumn();
        } else {
            return false;
        }
    }

    public static Predicate<ItemStack> getTemptItemPredicate() {
        return (stack) -> stack.isIn(ItemTags.AXOLOTL_FOOD) || stack.isOf(Items.TROPICAL_FISH);
    }

    public static class PlayDeadTask extends MultiTickTask<DiplocaulusEntity> {
        public PlayDeadTask() {
            super(ImmutableMap.of(MemoryModuleType.PLAY_DEAD_TICKS, MemoryModuleState.VALUE_PRESENT, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleState.VALUE_PRESENT), 200);
        }

        protected boolean shouldRun(ServerWorld serverWorld, AxolotlEntity axolotlEntity) {
            return axolotlEntity.isInsideWaterOrBubbleColumn();
        }

        protected boolean shouldKeepRunning(ServerWorld serverWorld, AxolotlEntity axolotlEntity, long l) {
            return axolotlEntity.isInsideWaterOrBubbleColumn() && axolotlEntity.getBrain().hasMemoryModule(MemoryModuleType.PLAY_DEAD_TICKS);
        }

        protected void run(ServerWorld serverWorld, DiplocaulusEntity diplocaulusEntity, long l) {
            Brain<DiplocaulusEntity> brain = diplocaulusEntity.getBrain();
            brain.forget(MemoryModuleType.WALK_TARGET);
            brain.forget(MemoryModuleType.LOOK_TARGET);
            diplocaulusEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0));
        }
    }

}
