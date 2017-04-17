package sort;

/**
 *
 * @author Matt Getz
 */
public class ComplexSorts {

    public static void mergeSort(int[] a, String direction) {
        int nElems = a.length;
        int[] workSpace = new int[nElems];
        recMergeSort(a, direction, workSpace, 0, nElems - 1);
    }

    public static void recMergeSort(int[] a, String drirection, int[] workSpace,
            int lowerBound, int upperBound) {
        if (lowerBound == upperBound) {
            return;
        } else {
            int mid = (lowerBound + upperBound) / 2;
            recMergeSort(a, drirection, workSpace, lowerBound, mid);
            recMergeSort(a, drirection, workSpace, mid + 1, upperBound);
            merge(a, drirection, workSpace, lowerBound, mid + 1, upperBound);
        }
    }

    public static void merge(int[] a, String direction, int[] workSpace,
            int lowPtr, int highPtr, int upperBound) {
        int j = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;

        while (lowPtr <= mid && highPtr <= upperBound) {
            if (a[lowPtr] < a[highPtr] && direction.equalsIgnoreCase("A")
                    || a[lowPtr] > a[highPtr] && direction.equalsIgnoreCase("D")) {
                workSpace[j++] = a[lowPtr++];
            } else {
                workSpace[j++] = a[highPtr++];
            }
        }

        while (lowPtr <= mid) {
            workSpace[j++] = a[lowPtr++];
        }

        while (highPtr <= upperBound) {
            workSpace[j++] = a[highPtr++];
        }

        for (j = 0; j < n; j++) {
            a[lowerBound + j] = workSpace[j];
        }
    }

    public static void quickSort(int[] a, String direction) {
        if (direction.equalsIgnoreCase("A")) {
            recursiveQuickSortAsc(a, 0, a.length - 1);
        }
        if (direction.equalsIgnoreCase("D")) {
            recursiveQuickSortDsc(a, 0, a.length);
        }
    }

    public static void recursiveQuickSortAsc(int[] a, int startIdx, int endIdx) {

        int idx = partition(a, startIdx, endIdx);

        // Recursively call quicksort with left part of the partitioned array
        if (startIdx < idx - 1) {
            recursiveQuickSortAsc(a, startIdx, idx - 1);
        }

        // Recursively call quick sort with right part of the partitioned array
        if (endIdx > idx) {
            recursiveQuickSortAsc(a, idx, endIdx);
        }
    }

    /**
     * Divides array from pivot, left side contains elements less than Pivot
     * while right side contains elements greater than pivot.
     */
    public static int partition(int[] a, int left, int right) {
        int pivot = a[left]; // taking first element as pivot

        while (left <= right) {
            //searching number which is greater than pivot, bottom up
            while (a[left] < pivot) {
                left++;
            }
            //searching number which is less than pivot, top down
            while (a[right] > pivot) {
                right--;
            }

            // swap the values
            if (left <= right) {
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;

                //increment left index and decrement right index
                left++;
                right--;
            }
        }
        return left;
    }

    public static void recursiveQuickSortDsc(int[] a, int left, int right) {
        if (left < right) {
            int pivot = left;
            for (int i = left + 1; i < right; i++) {
                if (a[i] > a[left]) {
                    swapDsc(a, i, ++pivot);
                }
            }
            swapDsc(a, left, pivot);
            recursiveQuickSortDsc(a, left, pivot);
            recursiveQuickSortDsc(a, pivot + 1, right);
        }
    }

    private static void swapDsc(int[] a, int left, int right) {
        int temp = a[right];
        a[right] = a[left];
        a[left] = temp;
    }
}
