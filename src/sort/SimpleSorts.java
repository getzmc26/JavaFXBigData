package sort;

/**
 *
 * @author Matt Getz
 */
public class SimpleSorts {

    public static void bubbleSort(int[] a, String direction) {
        int out, in;
        boolean moreChanges = true;

        for (out = a.length - 1; out > 0 && moreChanges; out--) {
            moreChanges = false;
            for (in = 0; in < out; in++) {
                if (a[in] > a[in + 1] && direction.equalsIgnoreCase("A")
                        || a[in] < a[in + 1] && direction.equalsIgnoreCase("D")) {
                    int temp = a[in];
                    a[in] = a[in + 1];
                    a[in + 1] = temp;
                    moreChanges = true;
                }
            }
        }
    }

    public static void selectionSort(int[] a, String direction) {
        int in, out, firstIndex, temp;
        for (in = a.length - 1; in > 0; in--) {
            firstIndex = 0;
            for (out = 1; out <= in; out++) {
                if (a[out] > a[firstIndex] && direction.equalsIgnoreCase("A")
                        || a[out] < a[firstIndex] && direction.equalsIgnoreCase("D")) {
                    firstIndex = out;
                }
            }
            temp = a[firstIndex];
            a[firstIndex] = a[in];
            a[in] = temp;
        }
    }

    public static void insertionSort(int[] a, String direction) {
        int in, out;
        int nElems = a.length;
        if (direction.equalsIgnoreCase("A")) {
            for (out = 1; out < nElems; out++) {
                int temp = a[out];
                in = out;
                while (in > 0 && a[in - 1] > temp) {
                    a[in] = a[in - 1];
                    --in;
                }
                a[in] = temp;
            }
        }
        if (direction.equalsIgnoreCase("D")) {
            for (out = 0; out < nElems - 1; out++) {
                in = out + 1;
                int temp = a[in];
                while (in > 0 && temp > a[in-1]) {
                    a[in] = a[in - 1];
                    --in;
                }
                a[in] = temp;
            }
        }
    }
}
