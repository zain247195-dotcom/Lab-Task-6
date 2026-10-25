
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentStudentMap {
    public static void main(String[] args) throws InterruptedException {

        // Creating a ConcurrentHashMap
        ConcurrentHashMap<String, Integer> students =
                new ConcurrentHashMap<>();

        int numberOfThreads = 5;
        int recordsPerThread = 1000;

        ExecutorService executor =
                Executors.newFixedThreadPool(numberOfThreads);

        // Measuring the start time
        long startTime = System.currentTimeMillis();

        for (int thread = 1; thread <= numberOfThreads; thread++) {
            final int threadId = thread;
            executor.submit(() -> {
                for (int i = 1; i <= recordsPerThread; i++) {
                    String name = "Student-" + threadId + "-" + i;
                    int marks = 50 + (i % 51);
                    students.put(name, marks);
                }
            });
        }

        // Wait for all insertion tasks to finish
        executor.shutdown();

        boolean completed = executor.awaitTermination(
                1, TimeUnit.MINUTES);

        if (!completed) {
            executor.shutdownNow();
            System.out.println("Tasks did not finish within one minute.");
            return;
        }

    
        int retrievedCount = 0;

        for (int thread = 1; thread <= numberOfThreads; thread++) {
            for (int i = 1; i <= recordsPerThread; i++) {
                String name = "Student-" + thread + "-" + i;
                Integer marks = students.get(name);

                if (marks != null) {
                    retrievedCount++;
                }
            }
        }

        // Measure the end time after retrieval
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        int expectedEntries = numberOfThreads * recordsPerThread;
        int actualEntries = students.size();

        System.out.println("Number of threads: " + numberOfThreads);
        System.out.println("Expected entries: " + expectedEntries);
        System.out.println("Actual entries: " + actualEntries);
        System.out.println("Successfully retrieved records: "
                + retrievedCount);
        System.out.println("Execution time: "
                + executionTime + " milliseconds");
    }
}
