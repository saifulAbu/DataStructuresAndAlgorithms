/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresandalgorithms;

/**
 *
 * @author Saif
 */
public class MinHeap {
    int []heap;
    int size;
    MinHeap(int size){
        heap = new int[size+1];
        this.size = 0;
    }//end of constructor
    
    void add(int n){
        if(size == heap.length - 1){
            throw new ArrayIndexOutOfBoundsException(size+1);
        }
        size++;
        heap[size] = n;
        int i = size;
        while(i > 1){
            if(heap[i] > heap[i/2]){
                break;
            }
            int temp = heap[i/2];
            heap[i/2] = heap[i];
            heap[i] = temp;
            i = i / 2;
        }//end while
    }//end of method
    
    int extractMin(){
        if(size == 0){
            throw new ArrayIndexOutOfBoundsException(-1);
        }
        int retval = heap[1];
        size--;
        heap[1] = heap[size+1];
        int min = 1;
        while(min < size){
            int nextMin = min;
            if(2 * min <= size && heap[2 * min] < heap[min]){
                nextMin = 2 * min;
            }
            
            if((2 * min + 1) <= size && heap[2 * min + 1] < heap[nextMin]){
                nextMin = 2 * min + 1;
            }
            
            if(min == nextMin){
                break;
            }
            //swap
            int temp = heap[min];
            heap[min] = heap[nextMin];
            heap[nextMin] = temp;
            min = nextMin;
        }//end of method
        
        return retval;
    }
}//end of class
