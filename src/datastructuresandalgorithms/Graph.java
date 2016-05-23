/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresandalgorithms;

import java.util.LinkedList;

/**
 *
 * adjacencty list representation of the graph
 */
public class Graph {
    int size;
    boolean isDirected;
    LinkedList<GNode>[] graph;
    Graph(int size, boolean isDirected){
        this.size = size;
        this.isDirected = isDirected;
        graph = new LinkedList[size];
        for(int i = 0; i < size; i++){
            graph[i] = new LinkedList<GNode>();
        }//end for
    }//end od constructor
    
    public void conncect(int src, int dest, int weight){
        if(src < 0 || src >= graph.length || dest < 0 || dest >= graph.length){
            throw new RuntimeException("Invalid vertex sequence");
        }
        graph[src].add(new GNode(src, dest, weight));
        if(!isDirected){
            graph[dest].add(new GNode(dest, src, weight));
        }
    }//end of method
    
    public void conncect(int src, int dest){
        conncect(src, dest, 1);
    }//end of method
    
    public void print(){
        for(int i = 0; i < size; i++){
            System.out.print(i + " : ");
            for(GNode g : graph[i]){
                System.out.print(g.dest + " ");
            }//end for
            System.out.println();
        }
    }//end of mtehod
    
    /**************** graph algorithms *********************/
    boolean hasCycle(){
        DisjointSetForest ds = new DisjointSetForest(size);
        for(int i = 0; i <size; i++){
            for(GNode node : graph[i]){
                int src = node.src;
                int dest = node.dest;
                if(!isDirected && src > dest){
                    continue;
                }//end if
                if(ds.inSameSet(src, dest)){
                    return true;
                }
                ds.union(src, dest);
            }//end for
        }//end for
        return false;
    }//end of method
    
    int numConncectedComponents(){
        DisjointSetForest ds = new DisjointSetForest(size);
        for(int i = 0; i <size; i++){
            for(GNode node : graph[i]){
                int src = node.src;
                int dest = node.dest;
                ds.union(src, dest);
            }//end for
        }//end for
        return ds.numSets();
    }//end of method
}//end of class

class GNode{
    int src;
    int dest;
    int weight;

    public GNode(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }//end of constructor   
}//end of class
