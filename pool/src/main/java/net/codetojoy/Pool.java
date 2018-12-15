package net.codetojoy;

import java.util.*;
import java.util.concurrent.atomic.*;

public class Pool {
    private static Hashtable<String,String> cache = new Hashtable<>();

    protected static AtomicInteger numCalls = new AtomicInteger(0);
    protected static AtomicInteger numCacheHits = new AtomicInteger(0);
    protected static AtomicInteger numCacheMisses = new AtomicInteger(0);

    public static void logStats() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(" numCalls: " + numCalls.get());
        buffer.append(" numCacheMisses: " + numCacheMisses.get());
        buffer.append(" numCacheHits: " + numCacheHits.get());
        buffer.append(" cacheSize: " + cache.size());
        System.out.println("TRACER " + buffer.toString());
    }

    private static void simulateLookup() {
        final long DELAY_IN_MILLIS = 300L;
        try { Thread.sleep(DELAY_IN_MILLIS); } catch(Exception ex) {}
    }

    public static String getConnection(String name) {
        String result = null;

        numCalls.getAndIncrement();

        if (! cache.containsKey(name)) {
            numCacheMisses.getAndIncrement();
            simulateLookup();
            cache.put(name,name + numCalls.get());
        } else {
            numCacheHits.getAndIncrement();
        }

        result = cache.get(name);

        return result;
    }
}
