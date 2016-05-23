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
public class AVLTree {
    AVLTreeNode root;

    public AVLTree() {
        root = null;
    }//end of const
    
    public void insert(int k){
        if(root == null){
            root = new AVLTreeNode(k);
            return;
        }//end if
        if(root.find(k)){
            return;
        }//end if
        //the key doesn't exist so insert
        root = root.insert(k);
    }//end of mehtod
    
    public boolean find(int k){
        if(root == null){
            return false;
        }
        return root.find(k);
    }//
    
    public void delete(int k){
        if(!root.find(k)){
            return;
        }
        root = root.delete(k);
    }
}//end of class

class AVLTreeNode{
    int key;
    int height;
    AVLTreeNode left;
    AVLTreeNode right;

    public AVLTreeNode(int k) {
        key = k;
        left = right = null;
    }//end of constructor
    
    boolean find(int k){
        if(k == key){
            return true;
        }else if(k < key && left != null){
            return left.find(k);
        }else if(k > key && right != null){
            return right.find(k);
        }else {
            return false;
        }
    }//end of method
    
    //balance around this node. as soon as any change of the given node is detected; eg. a new left or right subtree added to this node
    //call balance
    AVLTreeNode balance(){
        //update height
        updateHeight();
        //right sub tree is bigger
        if(bFactor() == 2){
            if(left != null && left.bFactor() < 0){ //right - left case
                left = left.rotateRight();
            }
            return rotateLeft();
        }else if(bFactor() == - 2){
            if(right != null && right.bFactor() > 0){
                right = right.rotateLeft();
            }
            return rotateRight();
        }
        //the tree is balanced to nothing to do
        return this;
    }//end of method
    
    int bFactor(){
        if(left == null && right == null){
            return 0;
        }else if(left == null){
            return 1 + right.height;
        }else if(right == null){
            return -(1 + left.height);
        }else{
            return right.height - left.height;
        }//end if else
    }//end of method

    private AVLTreeNode rotateRight() {
        AVLTreeNode q = left;
        AVLTreeNode B = null;
        if(q != null){
            B = q.right;
            q.right = this;
        }
        this.left = B;
        return q;
    }//end of method

    private AVLTreeNode rotateLeft() {
        AVLTreeNode p = right;
        AVLTreeNode B = null;
        if(p != null){
            B = p.left;
            p.left = this;
        }//end if        
        this.right = B;
        return p;
    }//end of method

    private void updateHeight() {
        if(left!= null && right != null){
            height = 1 + Math.max(left.height, right.height);
        }else if(right != null){
            height = 1 + right.height;
        }else if(left != null){
            height = 1 + left.height;
        }//end if else
    }//end of method
    
    public AVLTreeNode insert(int k){
        if(key < k){
            if(right != null){
                right = right.insert(k);
            }else{
                right = new AVLTreeNode(k);
            }
        }else{
            if(left != null){
                left = left.insert(k);
            }else{
                left = new AVLTreeNode(k);
            }
        }//end if else
        return balance();
    }//end of method

    AVLTreeNode delete(int k) {
        if(key < k){
            right = right.delete(k);
        }else if(key > k){
            left = left.delete(k);
        }else{
            AVLTreeNode min = null;
            if(right != null){ 
                min = right.getMinNode();
                right = right.deleteMinNode();
            }else{
                return left;
            }
            min.left = left;
            min.right = right;
            
            return min.balance();
        }//end if
        return balance();
    }//end of method

    private AVLTreeNode getMinNode() {
        if(left == null){
            return this;
        }else{
            return left.getMinNode();
        }
    }//end of method

    private AVLTreeNode deleteMinNode() {
        if(left == null){
            return this;
        }
        left = left.deleteMinNode();
        return balance();
    }
}//end of class