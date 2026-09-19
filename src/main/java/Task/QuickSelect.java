package Task;
import java.util.Random;
public class QuickSelect {
    private static final Random RANDOM = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid input: array is empty or k is out of bounds.");
        }
        metrics.startTimer();
        int result = quickSelect(a, 0, a.length - 1, k, metrics);
        metrics.stopTimer();
        return result;
    }

    private static int quickSelect(int[] arr, int left, int right, int k, Metrics metrics) {
        metrics.incrementDepth();
        while (left <= right) {
            if (left == right) {
                metrics.decrementDepth();
                return arr[left];
            }
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
            if (k >= lt && k <= gt) {
                metrics.decrementDepth();
                return arr[k];
            } else if (k < lt) {
                right = lt - 1;
            } else {
                left = gt + 1;
            }
        }
        metrics.decrementDepth();
        return -1;
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
