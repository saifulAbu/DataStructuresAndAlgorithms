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
public class QueueArray {
    int size;
    int numElements;
    int[] elem;
    int front;
    int back;

    public QueueArray(int size) {
        this.size = size;
        elem = new int[size];
        numElements = 0;
        front = 0;
        back = 0;
    }//end const
    
    boolean isEmpty(){
        if(numElements == 0){
            return true;
        }
        return false;
    }//end of method
    
    boolean isFull(){
        if(numElements == size){
            return true;
        }
        return false;
    }
    
    void enque(int k){
        if(isFull()){
            throw new RuntimeException("Queue is full");
        }
        elem[back] = k;
        back = (back + size + 1) % size;
        numElements++;        
    }//end of method
    
    int dequeue(){
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        int retVal = elem[front];
        front = (front + size + 1) % size;
        numElements--;
        return retVal;
    }//end of method
    
    int peek(){
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        int retVal = elem[front];
        return retVal;
    }//end of method
    
    
}//end of class
