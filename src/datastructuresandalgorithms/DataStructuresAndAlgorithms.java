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
        Graph g;
        //g = new Graph(5, false);
//        g.conncect(0, 1);
//        g.conncect(1, 3);
//        g.conncect(1, 2);
//        g.conncect(3, 4);
//        
//        g.print();
//        
//        g.printPath(4, g.DFS(0));
//        //System.out.println("has cycle : " + g.hasCycle());
//        //System.out.println("number of connected components " + g.numConncectedComponents());
//        g.printPath(4, g.BFS(0));
        
        //test djkastra's algorthim
//        g = new Graph(5, false);
//        g.conncect(0, 1, 2);
//        g.conncect(0, 2, 5);
//        
//        g.conncect(1, 2, 1);
//        g.conncect(1, 3, 3);
//        
//        g.conncect(2, 4, 2);
//        
//        g.conncect(3, 4, 4);
//        
//        g.print();
//        
//        g.printPath(4, g.applyDjKastraAlgorithm(0)[1]);

        // test topolocial sort
//        g = new Graph(6, true);
//        g.conncect(0, 1);
//        g.conncect(0, 2);
//        g.conncect(1, 4);
//        g.conncect(2, 1);
//        g.conncect(2, 3);
//        g.conncect(3, 4);
//        g.conncect(5, 1);
//        
//        g.print();
//        
//        int[] order = g.topologicalSort();
//        System.out.println(g.hasCycle());
//        for(int i = 0; i < g.numVertex; i++){
//            System.out.print(order[i] + " ");
//        }//end for
//        System.out.println();
        //test kruskal's algortihm
//        g = new Graph(4, false);
//        g.conncect(0, 1, 3);
//        g.conncect(0, 3, 1);
//        
//        g.conncect(1, 2, 4);
//        g.conncect(1, 3, 2);
//        
//        g.conncect(2, 3, 4);
//        
//        //g.print();
//        //Graph mst = g.applyKruskal();
//        Graph mst = g.applyPrim();
//        System.out.println("The minimum spanning tree has cycle? " + mst.hasCycle());
//        mst.print();

        //test hamiltonian path
//        g = new Graph(5, false);
//        
//        g.conncect(0, 1);
//        g.conncect(0, 3);
//        
//        g.conncect(1, 2);
//        g.conncect(1, 3);
//        g.conncect(1, 4);
//        
//        g.conncect(2, 4);
//        
//        //g.conncect(3, 4);
//        
//        //g.print();
//        
//        //int[] hamPath = g.getHamiltonianPath();
//        int[] hamPath = g.getHamiltonianCycle();
//        if(hamPath != null){
//            for(int i = 0; i < hamPath.length; i++){
//                System.out.print(hamPath[i] + " ");
//            }
//            System.out.println();
//        }else{
//            System.out.println("No hamiltonian cycle");
//        }

        //check tsp problem
        g = new Graph(4, false);
        
        g.conncect(0, 1, 1);
        g.conncect(0, 2, 1);
        g.conncect(0, 3, 1);
        
        g.conncect(1, 2, 1);
        g.conncect(1, 3, 1);
        
        g.conncect(2, 3, 1);
        
        int[] path = g.getTSPCycle();
        if(path != null){
            for(int i = 0; i < g.numVertex + 1; i++){
                System.out.print(path[i] + " ");
            }//end for
        }
        System.out.println();
    }//end of method
    
    
    public static void main(String[] args) {
        
        testGraph();;
        
    }//end main
    
}//end class
