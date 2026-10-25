
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedStudentMap {
    public static void main(String[] args) throws InterruptedException {

        // 1. Create a HashMap and convert it into a synchronized map
        Map<String, Integer> students =
                Collections.synchronizedMap(new HashMap<>());

        // 2. Create an ExecutorService with three threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 3. Each thread inserts 100 unique student records
        for (int thread = 1; thread <= 3; thread++) {
            final int threadId = thread;

            executor.submit(() -> {
                for (int i = 1; i <= 100; i++) {
                    String name = "Student-" + threadId + "-" + i;
                    int marks = 50 + (i % 51);

                    students.put(name, marks);
                }
            });
        }

        // 4. Shut down the executor and wait for tasks to finish
        executor.shutdown();

        boolean completed = executor.awaitTermination(
                1, TimeUnit.MINUTES);

        if (!completed) {
            executor.shutdownNow();
            System.out.println("Tasks did not finish within one minute.");
            return;
        }

        // 5. Display expected and actual number of entries
        int expectedEntries = 3 * 100;
        int actualEntries = students.size();

        System.out.println("Expected number of entries: "
                + expectedEntries);
        System.out.println("Actual number of entries: "
                + actualEntries);

        System.out.println("All tasks completed successfully.");
    }
}
