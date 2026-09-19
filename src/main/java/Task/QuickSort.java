package Task;

import java.util.Random;
public class QuickSort {
    private static final Random RANDOM = new Random();

    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        metrics.startTimer();
        quickSort(arr, 0, arr.length - 1, metrics);
        metrics.stopTimer();
    }
    private static void quickSort(int[] arr, int left, int right, Metrics metrics) {
        metrics.incrementDepth();

        while (left < right) {
            int pivotIndex = left + RANDOM.nextInt(right - left + 1);
            swap(arr, pivotIndex, right);
            int pivot = arr[right];
            int lt = left;
            int gt = right;
            int i = left;
            while (i <= gt) {
                metrics.incrementComparisons();
                if (arr[i] < pivot) {
                    swap(arr, i++, lt++);
                } else if (arr[i] > pivot) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }
            if (lt - left < right - gt) {
                quickSort(arr, left, lt - 1, metrics);
                left = gt + 1;
            } else {
                quickSort(arr, gt + 1, right, metrics);
                right = lt - 1;
            }
        }
        metrics.decrementDepth();
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}