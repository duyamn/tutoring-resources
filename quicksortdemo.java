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
        int pivot = partition(A, start, end);
        // int pivot = partitionAlt(A, start, end);
        quicksortrec(A, start, pivot - 1);
        quicksortrec(A, pivot+1, end); 
    }
    public static int partition(int[] A, int start, int end) {
        int pivot = A[end];
        int i = start - 1;
        int temp;                                   // use for swapping
        for (int j = start; j <= end; j++) {
            if (A[j] < pivot) {
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
    public static int partitionAlt(int[] A, int start, int end) {
        int pivot = A[end];
        int i = start - 1;
        int j = start - 1;
        int temp;                                   // use for swapping
        for (int n = start; n <= end; n++) {
            if (A[n] < pivot) {
                i++;
                temp = A[n];
                A[n] = A[i];
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