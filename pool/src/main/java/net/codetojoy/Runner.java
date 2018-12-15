package net.codetojoy;

import java.util.concurrent.*;
import java.util.*;

public class Runner {
    public void simpleGo() {
        Pool.getConnection("abc");
        Pool.getConnection("abc");
        Pool.getConnection("def");
        Pool.getConnection("def");
        Pool.logStats();
    }

    private static final int NUM_THREADS = 20;
    private static final int NUM_TASKS = 20;
    private static final long DELAY_IN_MILLIS = 10 * 1000;
    private static final boolean DO_USE_POOL2 = true;

    private List<String> getNames() {
        List<String> names = new ArrayList<>();
        names.add("name" + 1);
        names.add("name" + 2);
        names.add("name" + 3);
        return Collections.unmodifiableList(names);
    }

    public void threadedGo() {
        List<String> names = getNames();
        ExecutorService service = Executors.newFixedThreadPool(NUM_THREADS);

        for (int id = 0; id < NUM_TASKS; id++) {
            Task task = new Task(id, names, DO_USE_POOL2);
            service.execute(task);
        }

        try { Thread.sleep(DELAY_IN_MILLIS); } catch(Exception ex) {}
        service.shutdown();

        if (DO_USE_POOL2) {
            Pool2.logStats();
            Pool2.clearCache();
        } else {
            Pool.logStats();
            Pool.clearCache();
        }

        Connection.logStats();
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        // runner.simpleGo();
        runner.threadedGo();
        System.out.println("TRACER Ready.");
    }
}
