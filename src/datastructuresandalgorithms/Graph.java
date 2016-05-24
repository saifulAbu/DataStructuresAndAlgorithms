/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresandalgorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * adjacencty list representation of the graph
 */
public class Graph {
    int numVertex; // number of vertices
    int numEdges;
    boolean isDirected;
    LinkedList<GNode>[] graph;
    Graph(int size, boolean isDirected){
        this.numVertex = size;
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
        numEdges++;
        if(!isDirected){
            graph[dest].add(new GNode(dest, src, weight));
        }//end if
        
    }//end of method
    
    public void disconnect(int src, int dest){
        
        disconnectHelper(src, dest);
        numEdges--;
        if(!isDirected){
            disconnectHelper(dest, src);
        }//end if
    }//end of method
    
    private void disconnectHelper(int src, int dest){
        if(src < 0 || src >= graph.length || dest < 0 || dest >= graph.length){
            throw new RuntimeException("Invalid vertex sequence");
        }
        //disconnect src -> dest link
        int i = 0;
        for(GNode node : graph[src]){
            if(node.dest == dest){
                break;
            }
            i++;
        }//end for
        if(i == graph[src].size()){
            return; //the element is not there
        }
        graph[src].remove(i);
    }
    
    public void conncect(int src, int dest){
        conncect(src, dest, 1);
    }//end of method
    
    public void print(){
        for(int i = 0; i < numVertex; i++){
            System.out.print(i + " : ");
            for(GNode g : graph[i]){
                System.out.print(g.dest + "(" + g.weight + ")" + " ");
            }//end for
            System.out.println();
        }
    }//end of mtehod
    
    /**************** graph algorithms *********************/
    /*
        returns int[] as the path, has size = graph.size + 1. 
        the source is put in path[path.length-1] index.
    */
    int[] DFS(int src){
        if(!validVertex(src)){
            throw new RuntimeException("invalid vertex sequence");
        }//end if
        
        int[] path = new int[numVertex + 1];
        path[numVertex] = src;
        for(int i = 0; i < numVertex; i++){
            path[i] = -1;
        }//end for
        
        boolean[] visited = new boolean[numVertex];
        DFShelper(src, path, visited);
        return path;
    }//end of method
    
    int[] BFS(int src){
        if(!validVertex(src)){
            throw new RuntimeException("invalid vertex sequence");
        }//end if
        
        int[] path = new int[numVertex + 1];
        path[numVertex] = src;
        for(int i = 0; i < numVertex; i++){
            path[i] = -1;
        }//end for
        boolean[] visited = new boolean[numVertex];
        BFShelper(src, path, visited);
        return path;
    }//end of method
    
    void BFShelper(int src, int[] path, boolean[] visited){
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[src] = true;
        queue.add(src);
        
        int curVertex;
        while(!queue.isEmpty()){
            curVertex = queue.poll();
            for(GNode node : graph[curVertex]){
                if(!visited[node.dest]){
                    visited[node.dest] = true;
                    path[node.dest] = curVertex;
                    queue.add(node.dest);
                }//end if
            }//end for
        }//end while
    }//end of method
    
    void DFShelper(int src, int[] path, boolean[] visited){
        visited[src] = true;
        for(GNode node : graph[src]){
            if(!visited[node.dest]){
                path[node.dest] = src;
                DFShelper(node.dest, path, visited);
            }//end if
        }//end for
    }//end of method
    
    /*src is stored in path[path.length-1] index*/
    void printPath(int dest, int[] path){
        if(!validVertex(dest)){
            return;
        }
        Stack<Integer> pathStack = new Stack<>();
        int src = path[path.length - 1];
        int curIndex = dest;
        while(true){
            pathStack.push(curIndex);
            if(path[curIndex] == -1 ){
                if(curIndex != src){
                    System.out.println("No path from " + src + " to " + dest);
                    return;
                }
                break;
            }//end if
            curIndex = path[curIndex];
        }//end while
        int i;
        while(pathStack.size() > 1){
            i = pathStack.pop();
            System.out.print(i + " --> ");
        }//end while
        System.out.println(pathStack.pop());
    }//end method
    
    boolean validVertex(int index){
        if(index < 0 || index >= numVertex)
            return false;
        return true;
    }
    
    boolean hasCycle(){
        if(!isDirected){
            DisjointSetForest ds = new DisjointSetForest(numVertex);
            for(int i = 0; i <numVertex; i++){
                for(GNode node : graph[i]){
                    int src = node.src;
                    int dest = node.dest;
                    if(src > dest){
                        continue;
                    }//end if
                    if(ds.inSameSet(src, dest)){
                        return true;
                    }
                    ds.union(src, dest);
                }//end for
            }//end for
            return false;
        }//end if
        
        //directed case
        boolean[] visited = new boolean[numVertex];
        for(int i = 0; i < numVertex; i++){
            
            if(directedGraphCheckCycleHelper(i, visited)){
                return true;
            }
        }//end for
        return false;
    }//end of method
    
    boolean directedGraphCheckCycleHelper(int src, boolean[] visited){
        boolean hasCycle = false;
        visited[src] = true;
        for(GNode node : graph[src]){
            if(visited[node.dest] || directedGraphCheckCycleHelper(node.dest, visited)){
                hasCycle = true;
                break;
                
            }
        }
        visited[src] = false;
        return hasCycle; 
    }//end of method
    
    int numConncectedComponents(){
        DisjointSetForest ds = new DisjointSetForest(numVertex);
        for(int i = 0; i <numVertex; i++){
            for(GNode node : graph[i]){
                int src = node.src;
                int dest = node.dest;
                ds.union(src, dest);
            }//end for
        }//end for
        return ds.numSets();
    }//end of method
    
    //single source shortest path
    //retVal[0] contains the cost
    //retVal[1] contains the path
    int[][] applyDjKastraAlgorithm(int src){
        if(!validVertex(src)){
            throw new RuntimeException("Invalid vertex as source");
        }//end if
        int[][] retVal = new int[2][numVertex+1];
        int[] cost = retVal[0];
        int[] path = retVal[1];
        path[numVertex] = src;
        //initialize
        for(int i = 0; i < numVertex; i++){
            cost[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }//end for
        boolean[] visited = new boolean[numVertex];
        cost[src] = 0;
        PriorityQueue<MinHeapElement> pq = new PriorityQueue<>();
        pq.add(new MinHeapElement(0, src));
        while(!pq.isEmpty()){
            MinHeapElement elem = pq.poll();
            if(visited[elem.dest]){
                continue;
            }//end if
            int curSrc = elem.dest;
            visited[curSrc] = true;
            for(GNode node : graph[curSrc]){
                if(visited[node.dest]){
                    continue;
                }//end if
                int destCost = cost[curSrc] + node.weight;
                if(destCost < cost[node.dest]){
                    cost[node.dest] = destCost;
                    path[node.dest] = curSrc;
                    pq.add(new MinHeapElement(node.dest, curSrc));
                }//end if
            }//end for
        }//end while
        return retVal;
    }//end of method
    
    // returns order of the things to be done
    int[] topologicalSort(){
        if(hasCycle() || (numConncectedComponents() != 1) || !isDirected){
            return null;
        }//end if
        int[] order = new int[numVertex];
        int[] indegree = new int[numVertex];
        for(int i = 0; i < numVertex; i++){
            for(GNode node : graph[i]){
                indegree[node.dest]++;
            }//end for
        }//end for
        int curWork = 0;
        Queue<Integer> q = new LinkedList<>();
        
        //insert zero indegree elements into the list
        for(int i = 0; i < numVertex; i++){
            if(indegree[i] == 0){
                q.add(i);
            }//end if
        }//end for
        
        while(!q.isEmpty()){
            int curSrc = q.poll();
            order[curWork++] = curSrc;
            for(GNode node : graph[curSrc]){
                indegree[node.dest]--;
                if(indegree[node.dest] == 0){
                    q.add(node.dest);
                }//end if
            }//end for
        }//end while
            
        return order;
    }//end of method
    
    //returns the minimum spanning tree by applying kruskal's algorithm
    Graph applyKruskal(){
        if(isDirected || numConncectedComponents() != 1){
            return null;
        }//end if
        
        Comparator<GNode> comparator = new Comparator<GNode>() {
            @Override
            public int compare(GNode t, GNode t1) {
                return t.weight - t1.weight;
            }//end of method
        };
        GNode[] edges = new GNode[numEdges];
        int curEdgeIndex = 0;
        for(int i = 0; i < numVertex; i++){
            for(GNode node : graph[i]){
                if(node.src > node.dest){
                    continue; //only take one edge. eg take 0->4 but not 4->0
                }
                edges[curEdgeIndex++] = node;
            }//end for
        }//end for
        
        //sort the edges
        Arrays.sort(edges, comparator);
        
        Graph mst = new Graph(numVertex, isDirected);
        for(int i = 0; i < numEdges; i++){
            if(mst.numEdges == (numVertex - 1)){
                break;
            }//end if
            GNode node = edges[i];
            mst.conncect(node.src, node.dest, node.weight);
            if(mst.hasCycle()){
                mst.disconnect(node.src, node.dest);
            }
        }//end for 
        
        return mst;
    }//end of method
    
    //get minimum spanning tree by applying prim
    Graph applyPrim(){
        if(isDirected || numConncectedComponents() != 1){
            return null;
        }//end if
        
        Comparator<GNode> comparator = new Comparator<GNode>() {
            @Override
            public int compare(GNode t, GNode t1) {
                return t.weight - t1.weight;
            }//end of method
        };
        Graph mst = new Graph(numVertex, isDirected);
        PriorityQueue<GNode> pq = new PriorityQueue<>(comparator);
        //lets start from vertex 0
        for(GNode node : graph[0]){
            pq.add(node);
        }//end for
        HashSet<Integer> hs = new HashSet<>();
        hs.add(0);
        while(mst.numEdges < numVertex - 1){
            GNode min = pq.poll();
            if(hs.contains(min.dest)){
                continue;
            }//end if
            mst.conncect(min.src, min.dest, min.weight);
            hs.add(min.dest);
            //add all edges coming from min.dest to the pq
            for(GNode node : graph[min.dest]){
                //the edges that connects back to the vertex of current graph, ignore them
                if(!hs.contains(node.dest)){
                    pq.add(node);
                }
            }//end for
        }//end while
        return mst;
    }//end of method
    
    int[] getHamiltonianPath(){
        int[] path = new int[numVertex];
        boolean[] visited = new boolean[numVertex];
        path[0] = 0;
        visited[0] = true;
        for(GNode node : graph[0]){
            if(hamiltonianPathHelper(node.dest, 1, visited, path)){
                return path;
            }//end path
        }//end for
        return null;
    }//end of method

    private boolean hamiltonianPathHelper(int src, int pos, boolean[] visited, int[] path) {
        if(visited[src]){
            return false;
        }//end if
        visited[src] = true;
        path[pos] = src;
        if(pos == path.length-1){
            return true;
        }//end if
        for(GNode node : graph[src]){
            if(hamiltonianPathHelper(node.dest, pos+1, visited, path)){
                return true;
            }
        }//end for
        visited[src] = false;
        path[pos] = -1;
        return false;
    }//end of method
    
    int[] getHamiltonianCycle(){
        int[] path = new int[numVertex + 1];
        boolean[] visited = new boolean[numVertex+1];
        path[0] = 0;
        visited[0] = true;
        for(GNode node : graph[0]){
            if(hamiltonianCycleHelper(node.dest, 1, visited, path)){
                return path;
            }//end path
        }//end for
        return null;
    }//end of method

    private boolean hamiltonianCycleHelper(int src, int pos, boolean[] visited, int[] path) {
        if(visited[src]){
            return false;
        }//end if
        visited[src] = true;
        path[pos] = src;
        if(pos == path.length-2){
            for(GNode node : graph[src]){
                if(node.dest == 0){
                    path[pos + 1] = 0;
                    return true;
                }//end if
            }//end for
            return false;
        }//end if
        for(GNode node : graph[src]){
            if(hamiltonianCycleHelper(node.dest, pos+1, visited, path)){
                return true;
            }//end if
        }//end for
        visited[src] = false;
        path[pos] = -1;
        return false;
    }//end of method
    
    public int[][] getAdjacencyRepresentation(){
        int[][] adj = new int[numVertex][numVertex]; //adjacency list representation of the graph
        //init everything with infinity
        for(int i = 0; i < numVertex; i++){
            for(int j = 0; j < numVertex; j++){
                adj[i][j] = Integer.MAX_VALUE;
            }//end for
        }//end for
        
        //fill in proper value now
        for(int src = 0; src < numVertex; src++){
            for(GNode node : graph[src]){
                adj[src][node.dest] = node.weight;
            }//end for
        }//end for
        
        return adj;
    }//end of method
    
    //shortest of all the hamiltonian cycle
    int[] getTSPCycle(){
        if(isDirected){
            return null;
        }//end if
        int[][] adj = getAdjacencyRepresentation();
        int n = numVertex - 1;
        int nPow = (int)Math.pow(2, n);
        int set = nPow - 1; // if n = 3; then nPow = (1000) in binary subtracting 1 leads to 111
        
        int[][]path = new int[numVertex][nPow];
        int[][]memo = new int[numVertex][nPow];
        
        //initialize to -1 path and memo array
        for(int end = 0; end < numVertex; end++){
            for(int curSet = 0; curSet < nPow; curSet++){
                path[end][curSet] = -1;
                memo[end][curSet] = -1;
            }//end for
        }//end for
        
        if(TSPHelper(numVertex - 1, adj, set, path, memo, n, nPow) == Integer.MAX_VALUE){
            return null;
        }
        int[] tspPath = new int[numVertex + 1];
        tspPath[numVertex] = numVertex - 1;
        getTSPpath(path, tspPath, nPow - 1, n, nPow, n);
        return tspPath;
    }//end of method
    
    
    //starting from numVertex -1 th vertex (the last one) how much cost it takes for minimum to reach vertex end
    // and passing through all the vertices in "set"
    //set contains all the vertices except the last vertex and the end vertex
    int TSPHelper(int end, int[][] adj, int set, int[][] path, int[][] memo, int n, int nPow){
        if(set == 0){
            memo[end][set] = adj[n][end];
            if(adj[n][end] != Integer.MAX_VALUE){                
                path[end][set] = n;                    
            }//end if
        }//end if
        if(memo[end][set] != -1){
            return memo[end][set];
        }
        
        //remove 1 set at a time from the current set and 
        int curMin = Integer.MAX_VALUE;
        int prevMinVertex = -1;
        for(int i = 0; i < n; i++){
            int mask = (nPow - 1) - (1 << i);
            int curSet = set & mask;
            if(curSet == set){
                continue;   // set = 110 if masked with 110 produces 110 no change in the set. in those cases nothing to be done
            }
            
            if(adj[i][end] == Integer.MAX_VALUE){
                continue;
            }
            int curCost = TSPHelper(i, adj, curSet, path, memo, n, nPow) + adj[i][end];
            if(curCost  < curMin){
                curMin = curCost;
                prevMinVertex = i;
            }//end if
        }//end for
        path[end][set] = prevMinVertex;
        memo[end][set] = curMin;
        return curMin;
    }//end of method

    private void getTSPpath(int[][] path, int[]tspPath, int set, int end, int nPow, int index) {
        if(set == 0){
            tspPath[0] = path[end][set];
            return;
        }
        int prevVertex = tspPath[index] = path[end][set];
        int mask = (nPow - 1) - (1 << prevVertex);
        int newSet = set & mask; //exclude the prevVertex from the set
        getTSPpath(path, tspPath, newSet, prevVertex, nPow, index - 1);
    }
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

class MinHeapElement implements Comparable<MinHeapElement>{
    int dest;
    int cost;

    @Override
    public int compareTo(MinHeapElement t) {
        return cost - t.cost;
    }//end of method

    public MinHeapElement(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }//end of constructor    
}//end of class
