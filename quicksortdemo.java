public class quicksortdemo {
    public static void main(String[] args) {
        int[] unsortedArray = {2, 1, 3, 4, 6, 5, 8, 7, 9};
        printArr(unsortedArray);
        quicksort(unsortedArray);
        printArr(unsortedArray);
    }
    public static void printArr(int[] arr){
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void quicksort(int[] A) {
        quicksortrec(A, 0, A.length-1);
    }
    public static void quicksortrec(int[] A, int start, int end) {
        if (end <= start) return;
        int pivot = partition(A, start, end);       // used for pivoting, returns the final index of the pivot
        quicksortrec(A, start, pivot - 1);          // recur on left subarray
        quicksortrec(A, pivot+1, end);              // recur on right subarray
    }
    public static int partition(int[] A, int start, int end) {
        int pivot = A[end];
        int i = start - 1;                          // i represents the end of the left subarray
        int temp;                                   // use for swapping
        for (int j = start; j <= end; j++) {
            if (A[j] < pivot) {                     // populate the left subarray
                i++;
                temp = A[j];
                A[j] = A[i];
                A[i] = temp;
            }
        }
        i++;
        temp = A[i];
        A[i] = A[end];
        A[end] = temp;
        return i;
    }
}