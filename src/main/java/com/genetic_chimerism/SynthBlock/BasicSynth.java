package com.genetic_chimerism.SynthBlock;

import com.genetic_chimerism.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class BasicSynth extends HorizontalFacingBlock implements BlockEntityProvider {

    public BasicSynth(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    public static Direction getDirection(BlockView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.getBlock() instanceof BedBlock ? (Direction)blockState.get(FACING) : null;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    
    @Override
    protected MapCodec<? extends BasicSynth> getCodec() {
        return createCodec(BasicSynth::new);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BasicSynthEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // Make sure to check world.isClient if you only want to tick only on serverside.
        return validateTicker(type, BasicSynthEntity::tick);
    }
    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> validateTicker(BlockEntityType<A> givenType, BlockEntityTicker<? super E> ticker) {
        return ModBlockEntities.MUTAGEN_SYNTHESIZER_BLOCK_ENTITY == givenType ? (BlockEntityTicker<A>) ticker : null;
    }

    @Override
    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            // This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
            // a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                // With this call the server will request the client to open the appropriate Screenhandler
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }


        // This method will drop all items onto the ground when the block is broken
        @Override
        public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
            if (state.getBlock() != newState.getBlock()) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof BasicSynthEntity basicSynth) {
                    ItemScatterer.spawn(world, pos, basicSynth);
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
    }


