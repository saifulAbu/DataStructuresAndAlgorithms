/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresandalgorithms;

/**
 *path compression and height-wise union implementation of the Disjoint set Forest
 * 
 */
public class DisjointSetForest {
    int[] set;
    int numSets;

    public DisjointSetForest(int size) {
        set = new int[size];
        numSets = size;
        //initialize to -1
        for(int i =0; i < size; i++){
            set[i] = -1;
        }//end for
    }//end of constructor
    
    public int find(int k){
        if(k < 0 || k >= set.length){
            return -1;
        }//end if
        if(set[k] < 0){
            return k;
        }
        set[k] = find(set[k]); //path compression; point directly to the root
        return set[k];
    }//end of method
    
    public boolean inSameSet(int a, int b){
        int ra = find(a);
        int rb = find(b);
        if(ra == -1 || rb == -1){
            throw new RuntimeException("Index of out of bound");
        }
        if(ra == rb){
            return true;
        }
        return false;
    }//end of method
    
    public void union(int a, int b){
        int ra = find(a);
        int rb = find(b);
        //invalid index
        if(ra == -1 || rb == -1){
            throw new RuntimeException("Index of out of bound");
        }
        if(numSets == 1){
            return;
        }
        //already in the same set
        if(ra == rb){
            return;
        }
        if(set[ra] == set[rb]){ //both of the set has same height
            set[rb] = ra;
            set[ra]--; //increase size of the first set
        }else if(set[ra] < set[rb]){ //first set has bigger height
            set[rb] = ra;
        }else{
            set[ra] = rb; //second has bigger height
        }
        numSets--;
    }//end of method
    
    int numSets(){
        return numSets;
    }
    
    void print(){
        for(int i = 0; i < set.length; i++){
            System.out.print(set[i] + " ");
        }
        System.out.println();
    }
}//end of class
