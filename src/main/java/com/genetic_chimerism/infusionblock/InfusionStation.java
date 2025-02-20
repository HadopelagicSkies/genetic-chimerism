package com.genetic_chimerism.infusionblock;

import com.genetic_chimerism.*;
import com.genetic_chimerism.mutationsetup.Mutation;
import com.genetic_chimerism.mutationsetup.MutationAttachments;
import com.genetic_chimerism.mutationsetup.MutationInfo;
import com.genetic_chimerism.mutationsetup.MutationTrees;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class InfusionStation extends HorizontalFacingBlock implements BlockEntityProvider {

    public static final BooleanProperty OCCUPIED = Properties.OCCUPIED;
    protected static final VoxelShape BASE_PLATE_SHAPE = Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 1.0F, 14.0F);
    protected static final VoxelShape HUB_SHAPE = Block.createCuboidShape(5.5F, 1.0F, 5.5F, 10.5F, 2.0F, 10.5F);
    protected static final VoxelShape POST_SHAPE = Block.createCuboidShape(7.0F, 2.0F, 7.0F, 9.0F, 12.0F, 9.0F);
    protected static final VoxelShape SEAT_BOTTOM_SHAPE = Block.createCuboidShape(4.0F, 12.0F, 0.0F, 12.0F, 14.0F, 15.0F);
    protected static final VoxelShape SEAT_CUSHION_SHAPE = Block.createCuboidShape(3.0F, 14.0F, 0.0F, 13.0F, 16.0F, 16.0F);
    protected static final VoxelShape ARMREST_BAR_SHAPE = Block.createCuboidShape(0.0F, 13.0F, 7.0F, 16.0F, 14.0F, 9.0F);
    protected static final VoxelShape L_ARMREST_BAR_SHAPE = Block.createCuboidShape(0.0F, 14.0F, 7.0F, 1.0F, 18.0F, 9.0F);
    protected static final VoxelShape R_ARMREST_BAR_SHAPE = Block.createCuboidShape(15.0F, 14.0F, 7.0F, 16.0F, 18.0F, 9.0F);
    protected static final VoxelShape L_ARMREST_SHAPE = Block.createCuboidShape(-1.0F, 18.0F, 4.0F, 2.0F, 20.0F, 12.0F);
    protected static final VoxelShape R_ARMREST_SHAPE = Block.createCuboidShape(14.0F, 18.0F, 4.0F, 17.0F, 20.0F, 12.0F);

    protected static final VoxelShape SHAPE_NORTH = VoxelShapes.union(BASE_PLATE_SHAPE,HUB_SHAPE,POST_SHAPE,SEAT_BOTTOM_SHAPE,SEAT_CUSHION_SHAPE,ARMREST_BAR_SHAPE,L_ARMREST_BAR_SHAPE,R_ARMREST_BAR_SHAPE,L_ARMREST_SHAPE,R_ARMREST_SHAPE);
    protected static final VoxelShape SHAPE_WEST = ShapeRotationUtil.rotate90CCW(SHAPE_NORTH);
    protected static final VoxelShape SHAPE_SOUTH = ShapeRotationUtil.rotate90CCW(SHAPE_WEST);
    protected static final VoxelShape SHAPE_EAST = ShapeRotationUtil.rotate90CCW(SHAPE_SOUTH);

    public InfusionStation(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING,Direction.NORTH).with(OCCUPIED, false));
    }

    public static Direction getDirection(BlockView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.getBlock() instanceof BedBlock ? blockState.get(FACING) : null;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OCCUPIED);
    }

    @Override
    protected MapCodec<? extends InfusionStation> getCodec() {
        return createCodec(InfusionStation::new);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new InfusionStationEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // Make sure to check world.isClient if you only want to tick only on serverside.
        return validateTicker(type, InfusionStationEntity::tick);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> validateTicker(BlockEntityType<A> givenType, BlockEntityTicker<? super E> ticker) {
        return ModBlockEntities.INFUSION_STATION_BLOCK_ENTITY == givenType ? (BlockEntityTicker<A>) ticker : null;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            InfusionStationEntity blockEntity = (InfusionStationEntity) world.getBlockEntity(pos);
            ItemStack blockContents = blockEntity.getItems().get(0);
            GeneticChimerism.LOGGER.info("clicking with " + player.getMainHandStack().getName() + ", sneaking: " + player.isSneaking());

            // Actually doing things to give mutation here

            if(!player.isSneaking() && player.getMainHandStack().isEmpty() && blockEntity.getItems().get(0).isOf(ModItems.MUTAGEN_VIAL)) {
                GeneticChimerism.LOGGER.info("Laying Down....");
                layDown(pos,player);

                return ActionResult.SUCCESS;
            }

            if (stack.isOf(ModItems.MUTAGEN_VIAL) && blockContents.isOf(ModItems.MUTAGEN_VIAL)) {
                player.getInventory().offerOrDrop(blockContents.copy());
                blockEntity.setStack(0, stack.copy());
                player.getStackInHand(hand).setCount(0);
                blockEntity.markDirty();
                player.getInventory().markDirty();
                player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.adding", Text.translatable(blockEntity.getItems().get(0).get(ModComponents.MUTATION_STORED).mutID()).getString()), true);
            } else if (stack.isOf(ModItems.MUTAGEN_VIAL) && blockContents.isEmpty()) {
                blockEntity.setStack(0, stack.copy());
                player.getStackInHand(hand).setCount(0);
                blockEntity.markDirty();
                player.getInventory().markDirty();
                player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.adding", Text.translatable(blockEntity.getItems().get(0).get(ModComponents.MUTATION_STORED).mutID()).getString()), true);
            } else if(player.isSneaking() && stack.isEmpty() && blockContents.isOf(ModItems.MUTAGEN_VIAL)) {
                player.getInventory().offerOrDrop(blockContents.copy());
                blockEntity.setStack(0,ItemStack.EMPTY);
                blockEntity.markDirty();
                player.getInventory().markDirty();
                player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.removing"), true);
            }else if (!stack.isOf(ModItems.MUTAGEN_VIAL) && blockContents.isOf(ModItems.MUTAGEN_VIAL)) {
                player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.contents", Text.translatable(blockEntity.getItems().get(0).get(ModComponents.MUTATION_STORED).mutID()).getString()), true);
            }else if (blockContents.isOf(ModItems.MUTAGEN_VIAL)) {
                player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.contents", Text.translatable(blockEntity.getItems().get(0).get(ModComponents.MUTATION_STORED).mutID()).getString()), true);
            } else if (blockContents.isEmpty()) {
                player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.empty"), true);
            }
        }
        return ActionResult.SUCCESS;
    }



    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        return ActionResult.SUCCESS;
    }

    public void layDown(BlockPos pos, PlayerEntity player) {

        player.trySleep(pos).ifLeft((reason) -> {
            if (reason.getMessage() != null) {
                player.sendMessage(reason.getMessage(), true);
            }
        }).ifRight((ignored) -> {
            player.setPosition((double) pos.getX() + (double) 0.5F, (double) pos.getY() + (double) 1.75F, (double) pos.getZ() + (double) 0.5F);
        });
    }

    public static void infusePlayer(BlockPos blockPos, PlayerEntity player) {
        ItemStack chairContents = ((InfusionStationEntity) player.getWorld().getBlockEntity(blockPos)).getStack(0).copy();
        MutationInfo selectedMutation = chairContents.get(ModComponents.MUTATION_STORED);
        if (selectedMutation != null) {
            GeneticChimerism.LOGGER.info("mutation applied, chair contents: " + chairContents);
            List<MutationInfo> playerMutations = player.getAttached(MutationAttachments.PLAYER_MUTATION_LIST);
            if (playerMutations == null) playerMutations = new ArrayList<>();
            List<MutationInfo> settingMutations = new ArrayList<>(playerMutations);

            Mutation mutation = MutationTrees.mutationFromCodec(selectedMutation);
            if (mutation != null && mutation.getPrereq() != null){
                if (!playerMutations.contains(MutationTrees.mutationToCodec(mutation.getPrereq()))) {
                    player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.no_prereqs"), true);
                    return;
                }
            }
            String mutationName = Text.translatableWithFallback("mutations.mutation."+selectedMutation.mutID(),selectedMutation.mutID()).getString();
            if(selectedMutation.mutID().equals("antigen")){
                player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.infuse_antigen"), true);
                for (MutationInfo info : settingMutations){
                    MutationTrees.mutationFromCodec(info).onRemoved(player);
                }
                settingMutations.clear();
            } else if (playerMutations.contains(selectedMutation)) {
                player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.already_infused", mutationName), true);
                return;
            } else {
                player.sendMessage(Text.translatable("block.genetic_chimerism.infusion_station.infuse_success", mutationName), true);
                settingMutations.add(selectedMutation);
                MutationTrees.mutationFromCodec(selectedMutation).onApplied(player);
            }
            player.setAttached(MutationAttachments.PLAYER_MUTATION_LIST, settingMutations);
            GeneticChimerism.LOGGER.info("player mutations: " + player.getAttached(MutationAttachments.PLAYER_MUTATION_LIST));
        }
    }

    // This method will drop all items onto the ground when the block is broken
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof InfusionStationEntity infusionStation) {
                ItemScatterer.spawn(world, pos, infusionStation);
                // update comparators
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (direction) {
            case NORTH -> {
                return SHAPE_NORTH;
            }
            case SOUTH -> {
                return SHAPE_SOUTH;
            }
            case WEST -> {
                return SHAPE_WEST;
            }
            default -> {
                return SHAPE_EAST;
            }
        }
    }
}

