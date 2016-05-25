/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresandalgorithms;

/**
 *  array based implementation of the stack
 * @author Saif
 */
public class StackArray {
    int size;
    int numElements;
    int head;
    int elements[];
    StackArray(int size){
        this.size = size;
        elements = new int[size];
        numElements = 0;
        head = 0;
    }//end of cons
    
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
    
    void push(int k){
        if(isFull()){
            throw new RuntimeException("Stack is full");
        }
        elements[head] = k;
        head = (head + 1) % size;
        numElements++;
    }//end of method
    
    int pop(){
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
       
        head = (size + head - 1) % size;
        numElements--;
        return elements[head];
    }
    
    int peek(){
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
       
        int prevElement = (size + head - 1) % size;
        return elements[prevElement];
    }//end of method
}//end of class
