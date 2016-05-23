/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresandalgorithms;

import java.util.Random;

/**
 *
 * @author Saif
 */
public class DataStructuresAndAlgorithms {

    /**
     * @param args the command line arguments
     */
    
    public static void testMinHeap(){
        // TODO code application logic here
        MinHeap h = new MinHeap(16);
        h.add(10);
        h.add(2);
        h.add(5);
        h.add(6);
        h.add(7);
        
        for(int i = 0; i < 20; i++){
            h.add(i);
            //System.out.println(h.extractMin());
        }
    }
    
    public static void testBSTree(){
        BinarySearchTree bst = new BinarySearchTree();
        Random rand = new Random();
        
        for(int i = 0; i < 5; i++){
            int j = rand.nextInt(10);
            System.out.println("inserting " + j);
            bst.insert(j);
        }
        
        for(int i = 0; i < 10; i++){
            System.out.println(i + " is present " + bst.find(i));
        }
    }
    
    public static void testAVLTree(){
        AVLTree avlt = new AVLTree();
        for(int i = 0; i < 10; i++){
            avlt.insert(i);
        }
        
        for(int i = 5; i < 15; i++){
            //System.out.println(i + " is present " + avlt.find(i));
            avlt.delete(i);
        }
        
        for(int i= 0; i < 10; i++){
            System.out.println(i + " is present " + avlt.find(i));
        }
    }//end of method
    
    public static void testDSF(){
        int n = 4;
        DisjointSetForest ds = new DisjointSetForest(n);
        System.out.println("looking for 5 : " + ds.find(5));
        ds.union(0, 3);
        ds.union(3, 0);
        ds.print();
        ds.union(2, 1);
        ds.print();
        ds.union(3, 1);
        ds.print();
        //ds.union(5, 0);
        
    }//end method
    
    public static void testGraph(){
        Graph g = new Graph(7, false);
        g.conncect(0, 1);
        g.conncect(1, 4);
        g.conncect(4, 0);
        
        g.conncect(3, 6);
        
        g.print();
        
        System.out.println("has cycle : " + g.hasCycle());
    }//end of method
    public static void main(String[] args) {
        
        testGraph();;
        
    }//end main
    
}//end class
