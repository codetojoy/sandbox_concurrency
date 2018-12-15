package net.codetojoy;

import java.util.concurrent.atomic.AtomicInteger;

public class Connection {
    private static final AtomicInteger numInstances = new AtomicInteger(0);
    private static final AtomicInteger numClosed = new AtomicInteger(0);

    public Connection() {
        numInstances.getAndIncrement();
    }

    public void close() {
        numClosed.getAndIncrement();
    }

    public static void logStats() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(" Connection - numInstances: " + numInstances.get());
        buffer.append(" numClosed: " + numClosed.get());
        System.out.println("TRACER " + buffer.toString());
    }
}
