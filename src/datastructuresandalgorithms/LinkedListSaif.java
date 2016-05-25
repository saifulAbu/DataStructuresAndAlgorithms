/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresandalgorithms;

/**operations addFirst, addlast, delete(int index), delete(val)
 *  
 * @author Saif
 */
public class LinkedListSaif {
    int size;
    Node head;
    Node tail;
    
    public LinkedListSaif() {
        size = 0;
        head = new Node(Integer.MIN_VALUE);
        tail = head;
    }//end const
    
    public void addFirst(int v){
       size++;
       Node nd = new Node(v);
       nd.next = head.next;
       head.next = nd;
       if(size == 1){
           tail = nd;
       }//end if
    }//end of method
    
    public void addLast(int v){
        size++;
        Node nd = new Node(v);
        tail.next = nd;
        tail = nd;
        if(size == 1){
            head.next = nd;
        }//end if
    }//end of method
    
    public int size(){
        return size;
    }//end of method
    
    public void delete(int val){
        if(size == 0){
            throw new RuntimeException("List is empty");
        }
        size--;
        Node cur = head;
        while(cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;
                return;
            }//end if
            cur = cur.next;
        }//end while
    }//end of method
    
    public void deleteAt(int index){
        if(index < 0 || index > size() - 1){
            throw new RuntimeException("Invalid index " + index);
        }//end if
        size--;
        Node tempHead = head;
        for(int i = 0; i < index; i++){
            tempHead = tempHead.next;
        }//end for
        tempHead.next = tempHead.next.next;
    }//end of method
    
    public int get(int index){
        if(index < 0 || index > size() - 1){
            throw new RuntimeException("Invalid index " + index);
        }//end if
        
        Node tempHead = head;
        for(int i = 0; i < index; i++){
            tempHead = tempHead.next;
        }//end for
        return tempHead.next.val;
    }
}//end of class

class Node{
    int val;
    Node next;

    public Node(int val){
        this.val = val;
        next = null;
    }//end of const    
}//end of class
