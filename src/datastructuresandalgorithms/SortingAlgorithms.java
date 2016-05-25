package datastructuresandalgorithms;

/**
 *  Sorts an array of integer in ascending order using various algorithms
 * @author Saif
 */
public class SortingAlgorithms {
    public static void bubbleSort(int[] a){
        if(a.length <= 1){
            return;
        }//end if
        for(int i = 0; i < a.length - 1; i++){
            for(int j = i+1; j < a.length; j++){
                if(a[i] > a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }//end for
        }//end for
    }//end of method
    
    public static void mergeSort(int[] arr){
        if(arr.length <= 1){
            return;
        }
        int [] buffer = new int[arr.length];
        int start = 0;
        int end = arr.length - 1;
        mergeHelper(arr, start, end, buffer);
    }//end of method
    
    private static void mergeHelper(int[] arr, int start, int end, int buffer[]){
        if(start >= end){
            return;
        }//end if
        int middle = (start + end) / 2;
        mergeHelper(arr, start, middle, buffer);
        mergeHelper(arr, middle+1, end, buffer);
        merge(arr, start, middle, end, buffer);
        copyArr(arr, start, end, buffer);
    }//end of method
    
    private static void merge(int[] arr, int start, int middle, int end, int[]buffer){
        int leftPointer = start;
        int rightPointer = middle + 1;
        int curIndex = start;
        while(leftPointer <= middle && rightPointer <= end){
            if(arr[leftPointer] < arr[rightPointer]){
                buffer[curIndex] = arr[leftPointer];
                leftPointer++;
            }else{  
                buffer[curIndex] = arr[rightPointer];
                rightPointer++;
            }//end if else
            curIndex++;
        }//end while
        
        int leftOverIndex = -1;
        if(leftPointer > middle){
            leftOverIndex = rightPointer;
        }else{
            leftOverIndex = leftPointer;
        }//end if else
        
        while(curIndex <= end){
            buffer[curIndex++] = arr[leftOverIndex++];
        }//end while
    }//end of method
    
    private static void copyArr(int[]arr, int start, int end, int[] buffer){
        for(int i = start; i <= end; i++){
            arr[i] = buffer[i];
        }//end for
    }//end of method
    
    public static void quickSort(int [] arr){
        if(arr.length <= 1){
            return;
        }//end if
        int start = 0;
        int end = arr.length - 1;
        quickSortHelper(arr, start, end);
    }//end of method
    
    //wikipedia based method simpler
    private static void quickSortHelper(int[] arr, int start, int end){
        if(start >= end){
            return;
        }//end if
        
        int pivot = arr[start];
        int leftPointer = start + 1; // upto leftPointer - 1 index elements are less than or equal to pivot
        
        for(int i = start + 1; i <= end; i++){
            if(arr[i] < pivot){
                int temp = arr[i];
                arr[i] = arr[leftPointer];
                arr[leftPointer] = temp;
                leftPointer++;
            }//end if
        }//end for
        
        //swap the last known min element with the pivot
        int temp = arr[leftPointer - 1];
        arr[leftPointer - 1] = pivot;
        arr[start] = temp;
        
        quickSortHelper(arr, start, leftPointer - 2);
        quickSortHelper(arr, leftPointer, end);
    }//end of method
    
    private static void quickSortHelper2(int[] arr, int start, int end){
        if(start >= end){
            return;
        }//end if
        
        int pivot = arr[start];
        int leftPointer = start; // upto leftPointer - 1 index elements are less than pivot
        int rightPointer = end; // upto rightPointer + 1 index elements are greater than pivot
        
        while(leftPointer <= rightPointer){
            while(leftPointer <= end ){
                if(arr[leftPointer] > pivot){
                    break;
                }
                leftPointer++;
            }//end while
            
            while(leftPointer <= rightPointer){
                if(arr[rightPointer] < pivot){
                    break;
                }
                rightPointer--;
            }//end while
            //swap left and right
            if(leftPointer <= rightPointer){
                int temp = arr[leftPointer];
                arr[leftPointer] = arr[rightPointer];
                arr[rightPointer] = temp;
                rightPointer--;
                leftPointer++;
            }//end if
        }//end while
        int temp = arr[rightPointer];
        arr[rightPointer] = pivot;
        arr[start] = temp;
        quickSortHelper(arr, start, rightPointer - 1);
        quickSortHelper(arr, rightPointer + 1, end);
    }//end of method
    
    public static void printArray(int[] a){
        for(int val : a){
            System.out.print(val + " ");
        }//end for
        System.out.println();
    }//end of method
}//end of method
