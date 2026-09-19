package Task;

public class Metrics {
    private long comparisons;
    private int maxDepth;
    private int currentDepth;
    private long startTime;
    private long endTime;

    public Metrics() {
        this.comparisons = 0;
        this.maxDepth = 0;
        this.currentDepth = 0;
    }

    public void startTimer() {
        this.startTime = System.nanoTime();
    }

    public void stopTimer() {
        this.endTime = System.nanoTime();
    }

    public long getTimeInMilliseconds() {
        return (endTime - startTime) / 1_000_000;
    }

    public long getTimeInNanoseconds() {
        return endTime - startTime;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementDepth() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void decrementDepth() {
        currentDepth--;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}