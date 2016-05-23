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
public class BinarySearchTree {
    BSTNode root;

    public BinarySearchTree() {
        root = null;
    }//end of construcot
    
    public boolean find(int k){
        return root.find(k);
    }//end of method
    
    public void insert(int k){
        if(root == null){
            root = new BSTNode(k);
            return;
        }
        if(root.find(k)){
            return;
        }
        root.insert(k);
    }//end of method
    
    public void delete(int k){
        if(root.find(k)){
            root.delete(k);
        }
    }
}//end of class

class BSTNode{
    int key;
    BSTNode left;
    BSTNode right;
    public BSTNode(int k) {
        key = k;
        left = right = null;
    }//end of constructor
    
    public void insert(int k){
        if(key < k){
            if(right != null){
                right.insert(k);
            }else{
                right = new BSTNode(k);
            }
        }else{
            if(left != null){
                left.insert(k);
            }else{
                left = new BSTNode(k);
            }//end of if else inner
        }//end of if else
    }//end of method\\
    
    public BSTNode delete(int k){
        if(k == key){
            BSTNode min = null;
            if(right != null){
                min = right.getMinNode();
                right = right.deleteMinNode();
            }//end if
            min.left = left;
            min.right = right;
            return min;
        }else if(key < k){
            right = right.delete(k);
        }else{
            left = left.delete(k);
        }
        return this;
    }//end of method
    
    public BSTNode getMinNode(){
        if(left == null){
            return this;
        }
        return left.getMinNode();
    }
    
    public BSTNode deleteMinNode(){
        if(left == null){
            return right;
        }
        left = left.deleteMinNode();
        return this;
    }
    
    public boolean find(int k){
        if(k == key){
            return true;
        }
        if(key < k && right != null){
            return right.find(k);
        }
        
        if(key > k && left != null){
            return left.find(k);
        }
        
        return false;
    }//end of method
    
}//end of class
