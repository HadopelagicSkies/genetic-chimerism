package com.genetic_chimerism.mutation_setup_client;

import com.genetic_chimerism.mutation_setup.*;
import net.minecraft.entity.AnimationState;

import java.util.ArrayList;
import java.util.List;

public class MutationTreesClient {
    public static List<MutationTreesClient> mutationTreesListClient = new ArrayList<>();
    public final List<MutationClient> mutations;
    public final String treeID;

    public MutationTreesClient(List<MutationClient> mutations, String treeID) {
        this.mutations = mutations;
        this.treeID = treeID;
    }

    public static void initialize() {
        MutationClient.initialize();
        AmphibiousTreeClient.initialize();
        AquaticTreeClient.initialize();
//        HoovedTreeClient.initialize();
        HornedTreeClient.initialize();
//        InvertebrateTreeClient.initialize();
        ScaledTreeClient.initialize();
//        ShelledTreeClient.initialize();
//        SmallMammalTreeClient.initialize();
//        SpinedTreeClient.initialize();
        TentacledTreeClient.initialize();
//        TuskedTreeClient.initialize();
        WoolenTreeClient.initialize();
        WingedTreeClient.initialize();
//        SpecialTreeClient.initialize();

    }

    public static MutationTreesClient addTree(List<MutationClient> tree, String treeID){
        MutationTreesClient mutationTree = new MutationTreesClient(tree, treeID);
        mutationTreesListClient.add(mutationTree);
        return mutationTree;
    }



    public MutationClient addToTree(MutationClient mutation){
        this.mutations.add(mutation);
        return mutation;
    }

    public static List<MutationTreesClient> listTrees() {
        return mutationTreesListClient;
    }

    public static MutationInfo mutationToCodec(MutationClient mutation){
        return new MutationInfo(mutation.getMutID(), mutation.getTreeID());
    }

    public static MutationBodyInfo mutationToCodec(Mutation mutation, int patternIndex, int color1, int color2, int growth, boolean isReceding, AnimationState partAnim, AnimationState actionAnim){
        return new MutationBodyInfo(mutation.getMutID(), mutation.getTreeID(),patternIndex,color1,color2,growth,isReceding,partAnim,actionAnim);
    }

    public static MutationClient mutationFromCodec(MutationInfo mutationCodec){
        for(MutationTreesClient trees: mutationTreesListClient){
            if (trees.treeID.equals(mutationCodec.treeID())){
                for(MutationClient mut: trees.mutations){
                    if (mut.getMutID().equals(mutationCodec.mutID())){return mut;}
                }
            }
        }
        return null;
    }

    public static MutationClient mutationFromCodec(MutationBodyInfo mutationCodec){
        for(MutationTreesClient trees: mutationTreesListClient){
            if (trees.treeID.equals(mutationCodec.treeID())){
                for(MutationClient mut: trees.mutations){
                    if (mut.getMutID().equals(mutationCodec.mutID())){return mut;}
                }
            }
        }
        return null;
    }

    public static final MutationTreesClient human = addTree(new ArrayList<MutationClient>(), "human");
}
