package Task;
public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length];
        metrics.startTimer();
        sort(arr, temp, 0, arr.length - 1, metrics);
        metrics.stopTimer();
    }

    private static void sort(int[] arr, int[] temp, int left, int right, Metrics metrics) {
        metrics.incrementDepth();
        if (left >= right) {
            metrics.decrementDepth();
            return;
        }
        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            metrics.decrementDepth();
            return;
        }
        int mid = left + (right - left) / 2;
        sort(arr, temp, left, mid, metrics);
        sort(arr, temp, mid + 1, right, metrics);
        merge(arr, temp, left, mid, right, metrics);
        metrics.decrementDepth();
    }
    private static void merge(int[] arr, int[] temp, int left, int mid, int right, Metrics metrics) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }
    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left) {
                metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
}