package Task;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
class SortTests {
    private final Random random = new Random();

    @Test
    void testMergeSortCorrectness() {
        for (int i = 0; i < 100; i++) {
            int[] arr = generateRandomArray(1000);
            int[] expected = arr.clone();
            Arrays.sort(expected);
            MergeSort.sort(arr, new Metrics());
            assertArrayEquals(expected, arr, "MergeSort sorted wrong!");
        }
    }
    @Test
    void testQuickSortCorrectness() {
        for (int i = 0; i < 100; i++) {
            int[] arr = generateRandomArray(1000);
            int[] expected = arr.clone();
            Arrays.sort(expected);
            QuickSort.sort(arr, new Metrics());
            assertArrayEquals(expected, arr, "QuickSort отсортировал неверно!");
        }
    }

    @Test
    void testQuickSelectCorrectness() {
        for (int i = 0; i < 100; i++) {
            int[] arr = generateRandomArray(1000);
            int[] sortedArr = arr.clone();
            Arrays.sort(sortedArr);
            int k = random.nextInt(arr.length);
            int expected = sortedArr[k];
            int result = QuickSelect.select(arr.clone(), k, new Metrics());
            assertEquals(expected, result, "QuickSelect find wrong element!");
        }
    }
    @Test
    void testEdgeCases() {
        int[] empty = {};
        int[] single = {42};
        int[] sorted = {1, 2, 3, 4, 5};
        int[] duplicates = {7, 7, 7, 7, 7};
        MergeSort.sort(empty, new Metrics());
        assertArrayEquals(new int[]{}, empty);
        QuickSort.sort(single, new Metrics());
        assertArrayEquals(new int[]{42}, single);
        MergeSort.sort(sorted, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
        QuickSort.sort(duplicates, new Metrics());
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, duplicates);
    }
    @Test
    void testQuickSortMaxDepth() {
        int n = 100_000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);
        int expectedMaxDepth = (int) (2 * (Math.log(n) / Math.log(2)));
        assertTrue(metrics.getMaxDepth() <= expectedMaxDepth,
                "The recursion depth is too great: " + metrics.getMaxDepth());
    }
    private int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }
}