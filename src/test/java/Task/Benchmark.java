package Task;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
public class Benchmark {
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] inputTypes = {"random", "sorted", "duplicates"};
        String[] algorithms = {"MergeSort", "QuickSort", "QuickSelect"};

        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");
            for (int n : sizes) {
                for (String type : inputTypes) {
                    for (String algo : algorithms) {
                        System.out.println("Running " + algo + " on " + type + " array of size " + n + "...");

                        Metrics[] runs = new Metrics[5];

                        for (int i = 0; i < 5; i++) {
                            int[] arr = generateArray(n, type);
                            runs[i] = new Metrics();

                            if (algo.equals("MergeSort")) {
                                MergeSort.sort(arr, runs[i]);
                            } else if (algo.equals("QuickSort")) {
                                QuickSort.sort(arr, runs[i]);
                            } else if (algo.equals("QuickSelect")) {
                                int k = RANDOM.nextInt(n);
                                QuickSelect.select(arr, k, runs[i]);
                            }
                        }

                        Metrics medianMetrics = getMedianRun(runs);
                        writer.printf("%s,%s,%d,%d,%d,%d\n",
                                algo, type, n,
                                medianMetrics.getTimeInMilliseconds(),
                                medianMetrics.getComparisons(),
                                medianMetrics.getMaxDepth());
                    }
                }
            }
            System.out.println("Done! Results saved in results.csv");
        } catch (IOException e) {
            System.out.println("Rerror while saving in file:  " + e.getMessage());
        }
    }
    private static int[] generateArray(int n, String type) {
        int[] arr = new int[n];
        if (type.equals("random")) {
            for (int i = 0; i < n; i++) {
                arr[i] = RANDOM.nextInt(1_000_000);
            }
        } else if (type.equals("sorted")) {
            for (int i = 0; i < n; i++) {
                arr[i] = i;
            }
        } else if (type.equals("duplicates")) {
            for (int i = 0; i < n; i++) {
                arr[i] = RANDOM.nextInt(10); // значения от 0 до 9
            }
        }
        return arr;
    }
    private static Metrics getMedianRun(Metrics[] runs) {
        for (int i = 0; i < runs.length - 1; i++) {
            for (int j = 0; j < runs.length - i - 1; j++) {
                if (runs[j].getTimeInMilliseconds() > runs[j + 1].getTimeInMilliseconds()) {
                    Metrics temp = runs[j];
                    runs[j] = runs[j + 1];
                    runs[j + 1] = temp;
                }
            }
        }
        return runs[2];
    }
}
