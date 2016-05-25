/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresandalgorithms;

import java.util.LinkedList;

/**
 *
 * @author Saif
 */
public class HashMapSaif {
    LinkedList<HashMapNode>[] hashTable;
    int numKeys;

    public HashMapSaif(int hashTableSize) {
        numKeys = 0;
        hashTable = new LinkedList[hashTableSize];
        for(int i = 0; i < hashTableSize; i++){
            hashTable[i] = new LinkedList<>();
        }//end for
    }//end of const
    
    public void remove(int k){
        if(!contains(k)){
            return;
        }
        int hashVal = k % hashTable.length;
        int del = 0;
        for(HashMapNode node : hashTable[hashVal]){
            if(node.key == k){
                break;
            }
            del++;
        }//end for
        hashTable[hashVal].remove(del);
    }
    
    public int get(int k){
        if(!contains(k)){
            return Integer.MIN_VALUE;
        }
        int hashVal = k % hashTable.length;
        int retVal = 0;
        for(HashMapNode node : hashTable[hashVal]){
            if(node.key == k){
                retVal = node.val;
                break;
            }
        }
        return retVal;
    }//end of method
    
    public void put(int k, int val){
        if(!contains(k)){
            int hashVal = k % hashTable.length;
            hashTable[hashVal].addFirst(new HashMapNode(k, val));
        }//end if
        
    }//end of method
    
    public boolean contains(int k){
        int hashVal = k % hashTable.length;
        for(HashMapNode node : hashTable[hashVal]){
            if(node.key == k){
                return true;
            }
        }
        return false;
    }//end of method

    private static class HashMapNode {
        int key;
        int val;
        public HashMapNode(int key, int val) {
            this.key = key;
            this.val = val;
        }//end of const
    }//end of class
    
    
}//end of class
