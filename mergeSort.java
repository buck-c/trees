public class mergeSort {  //reused merge sort from project 6, making it compatible with student objects
    
    private student Array[];
    
    void mSort(student Arr[], int start, int end) {
    
        if(!(start == end)) { //keeps dividing itself until one element is left 
            int mid = (start + end) / 2; 
            mSort(Arr, start, mid); //calls itself to split the first half
            mSort(Arr, mid+1, end); //calls itself to split the second half
            merge(Arr, start, mid, end); //merges start and end in the correct order
        }
    
        if(start==end) //sets the array when finished so it can be printed
            setArray(Arr);
        
    }
    
    void merge(student Arr[], int start, int mid, int end) {
    
        student temp[] = new student[end - start + 1]; //creates a temp array to not overwrite the "real" one until the end
    
        int i = start, j = mid+1, k = 0; // indexes for moving through the arrays
     
        while(i <= mid && j <= end) { //until either i or j increment out of bounds
            if(Arr[j].getName().compareTo(Arr[i].getName())>=0) { //places the first value in temp if it is smaller or equal to the second value
                temp[k] = Arr[i];
                k++; i++;
            }
            else { //places the second value in temp if it is smaller than the first value
                temp[k] = Arr[j];
                k++; j++;
            }
        }
    
        while(i <= mid) { //places whatevers left in the first half if the second half was ran through first
            temp[k] = Arr[i];
            k++; i++;
        }
    
        while(j <= end) { //places whatevers left in the second half if the first half was ran through first
            temp[k] = Arr[j];
            k++; j++;
        }
    
        for(i = start; i <= end; i++) { //copies the temp array to the actual array
            Arr[i] = temp[i - start];
        }
    }
    
    void setArray(student[] arr){
        Array=arr;
    }
    
    student [] getArray(){
        return Array;
    }
    
    void print(){ // prints array, 20 numbers to a line to make it easier to read

        for(int i=0;i<Array.length;i++){
            System.out.println(Array[i]);   
        }
    }
    
    }