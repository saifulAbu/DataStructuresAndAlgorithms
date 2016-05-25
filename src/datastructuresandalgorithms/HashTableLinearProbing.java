/*
 * Hash table using linear probling.
operations : insert, delete, find
 */
package datastructuresandalgorithms;

/**
 *
 * @author Saif
 */
public class HashTableLinearProbing {
    int key[];
    int size;
    int totalElement;
    public HashTableLinearProbing(int size) {
        this.size = size;
        key = new int[size];
        //initialize everything with -1
        for(int i = 0; i < size; i++){
            key[i] = -1;
        }//end for
        totalElement = 0;
    }//end of method
    
    public boolean find(int k){
        if(totalElement == 0){
            return false;
        }//end if
        int hashVal = k % size;
        for(int i = 0; i < size; i++){
            int index = (hashVal + i) % size;
            if(key[index] == k){
                return true;
            }//end if
            if(key[index] == -1){   //reached the end of the map
                return false;
            }//end if
        }//end for
        return false;
    }//end of method
    
    public boolean add(int k){
        if(totalElement == size){
            return false;
        }//end if
        if(find(k)){
            return true;
        }//end if
        
        int hashVal = k % size;
        for(int i = 0; i < size; i++){
            int index = (hashVal + i) % size;
            if(key[index] < 0){   
                key[index] = k;
                break;
            }//end if
        }//end for
        totalElement++;
        return true;
    }//end of method
    
    public void delete(int k){
        if(totalElement == 0){
            return;
        }//end if
                
        int hashVal = k % size;
        for(int i = 0; i < size; i++){
            int index = (hashVal + i) % size;
            if(key[index] == k){
                key[index] = -2;
                totalElement--;
                break;
            }//end if
        }//end for
    }//end method
}//end of class
