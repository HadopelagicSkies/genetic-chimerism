package com.genetic_chimerism.synthblock;

import com.genetic_chimerism.*;
import com.genetic_chimerism.mutation_setup.Mutation;
import com.genetic_chimerism.mutation_setup.MutationTrees;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import java.util.List;


public class SynthScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public final PlayerEntity player;
    public int treeIndex=-1;
    public int mutationIndex=-1;
    public SynthRecipe setRecipe;
    public Mutation setMutation;

    private final int treeNum = MutationTrees.listTrees().size();
    private static final int CONFIRM_BUTTON_INDEX = 0;
    private static final int TREE_BUTTON_START_INDEX = 1;
    private final int MUTATION_BUTTON_START_INDEX = treeNum+1;

    // This constructor gets called on the client when the server wants it to open the screenHandler,
    // The client will call the other constructor with an empty Inventory and the screenHandler will automatically
    // sync this empty inventory with the inventory on the server.
    public SynthScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(2));
    }


    // This constructor gets called from the BlockEntity on the server without calling the other constructor first, the server knows the inventory of the container
    // and can therefore directly provide it as an argument. This inventory will then be synced to the client.
    public SynthScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(GeneticChimerism.SYNTH_SCREEN_HANDLER, syncId);
        checkSize(inventory, 2);
        this.inventory = inventory;
        this.player = playerInventory.player;
        // some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player);

        // Our inventory
        this.addSlot(new Slot(inventory, 0, 279, 114) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.GLASS_BOTTLE);
            }
        });
        this.addSlot(new Slot(inventory, 1, 279, 145){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        // The player inventory
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + (i + 1) * 9, 108 + j * 18, 172 + i * 18));
            }
        }
        // The player Hotbar
        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 108 + i * 18, 230));
        }

    }

    public void generateVial(PlayerEntity player) {
        ItemStack bottleStack = inventory.getStack(0);
        ItemStack vialStack = inventory.getStack(1);

        if(bottleStack.isOf(Items.GLASS_BOTTLE) && vialStack.isEmpty() && bottleStack.getCount() > 0){

            vialStack = new ItemStack(GeneticChimerismItems.MUTAGEN_VIAL,1);
            vialStack.set(GeneticChimerismComponents.MUTATION_STORED,MutationTrees.mutationToCodec(this.setMutation));


            GeneticChimerism.LOGGER.info("checking recipe for: " + this.setMutation.getMutID());
            this.setRecipe = this.setMutation.getRecipe();
            List<ItemStack> recipeInputs = this.setRecipe != null ? this.setRecipe.getInputs() : List.of();
            boolean allValid = true;
            for(ItemStack input : recipeInputs){
                if(!input.isEmpty()) allValid = checkInventory(player,input);
                if(!allValid) break;
            }

            GeneticChimerism.LOGGER.info("all valid: " +allValid);
            if(player.isCreative() || allValid){
                for(ItemStack input : recipeInputs){
                    if(!player.isCreative()) removeIngredients(player,input);
                }
                this.inventory.setStack(1,vialStack);
                bottleStack.increment(-1);
                this.inventory.setStack(0,bottleStack);
            }
        }
            this.inventory.markDirty();
    }
    public void removeIngredients(PlayerEntity player, ItemStack input){
        ItemStack inputStack = input.copy();
        for(int i=0; i<player.getInventory().size();i++){
            ItemStack stack = player.getInventory().getStack(i).copy();
            if (stack.isOf(input.getItem())){
                if(input.isOf(GeneticChimerismItems.CRUDE_TISSUE_SAMPLE)||input.isOf(GeneticChimerismItems.FRESH_TISSUE_SAMPLE)||input.isOf(GeneticChimerismItems.ENSOULED_TISSUE_SAMPLE)){
                    if (input.get(GeneticChimerismComponents.TISSUE_TYPE).equals(stack.get(GeneticChimerismComponents.TISSUE_TYPE))){
                        player.getInventory().getStack(i).setCount(stack.getCount() - inputStack.getCount());
                        inputStack.increment(-1 * stack.getCount());
                        if (inputStack.getCount() == 0) {break;}
                    }
                }
                else {
                    player.getInventory().getStack(i).setCount(stack.getCount() - inputStack.getCount());
                    inputStack.increment(-1 * stack.getCount());
                    if (inputStack.getCount() == 0) {break;}
                }
            }
        }
        player.getInventory().markDirty();
    }

    public boolean checkInventory(PlayerEntity player, ItemStack input){
        ItemStack inputStack = input.copy();
        for(int i=0; i<player.getInventory().size();i++){
            ItemStack stack = player.getInventory().getStack(i).copy();
            if (stack.isOf(input.getItem())){
                if(input.isOf(GeneticChimerismItems.CRUDE_TISSUE_SAMPLE)||input.isOf(GeneticChimerismItems.FRESH_TISSUE_SAMPLE)||input.isOf(GeneticChimerismItems.ENSOULED_TISSUE_SAMPLE)){
                    if (input.get(GeneticChimerismComponents.TISSUE_TYPE).equals(stack.get(GeneticChimerismComponents.TISSUE_TYPE))){
                        inputStack.increment(-1 * stack.getCount());
                        if (inputStack.getCount() == 0) {
                            break;
                        }
                    }
                }
                else{
                    inputStack.increment(-1 * stack.getCount());
                    if (inputStack.getCount() == 0) {
                        break;
                    }
                }
            }
        }
        GeneticChimerism.LOGGER.info("item checked: " + input.getName() + ", is present: " + (inputStack.isEmpty()|| inputStack.getCount()<=0));
        return inputStack.isEmpty()|| inputStack.getCount()<=0;
    }


    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if(id == CONFIRM_BUTTON_INDEX && this.treeIndex != -1 && this.mutationIndex != -1) {
            generateVial(player);
            GeneticChimerism.LOGGER.info("confirm button server click");
            GeneticChimerism.LOGGER.info("Button click, ID: "+ id +", Tree Index: " + this.treeIndex + ", Mutation Index: " +this.mutationIndex);
            return true;
        }
        else if (id > CONFIRM_BUTTON_INDEX && id < MUTATION_BUTTON_START_INDEX) {
            this.treeIndex = id;
            this.setMutation = null;
            this.setRecipe = null;
            GeneticChimerism.LOGGER.info("Button click, ID: "+ id +", Tree Index: " + this.treeIndex + ", Mutation Index: " +this.mutationIndex);
            return true;
        }
        else if (id >= MUTATION_BUTTON_START_INDEX) {
            this.mutationIndex=id;
            GeneticChimerism.LOGGER.info("Button click, ID: "+ id +", Tree Index: " + this.treeIndex + ", Mutation Index: " + this.mutationIndex);
            this.setMutation =  MutationTrees.listTrees().get(this.treeIndex-TREE_BUTTON_START_INDEX).mutations.get(this.mutationIndex-MUTATION_BUTTON_START_INDEX);
            return true;
        }
        GeneticChimerism.LOGGER.info("Button click, ID: "+ id +", Tree Index: " + this.treeIndex + ", Mutation Index: " +this.mutationIndex);
        return false;
    }

    public void setTreeIndex(int selectedIndex) {treeIndex=selectedIndex; }

    public void setMutationIndex(int selectedIndex) {mutationIndex=selectedIndex; }

    public List<MutationTrees> getTrees() {
        return MutationTrees.listTrees();
    }
}