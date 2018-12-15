package net.codetojoy;

import java.util.*;
import java.util.concurrent.atomic.*;

public class Pool {
    // This is not thread-safe !!
    private static Hashtable<String,Connection> cache = new Hashtable<>();

    protected static AtomicInteger numCalls = new AtomicInteger(0);
    protected static AtomicInteger numCacheHits = new AtomicInteger(0);
    protected static AtomicInteger numCacheMisses = new AtomicInteger(0);

    public static void logStats() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(" Pool - numCalls: " + numCalls.get());
        buffer.append(" numCacheMisses: " + numCacheMisses.get());
        buffer.append(" numCacheHits: " + numCacheHits.get());
        buffer.append(" cacheSize: " + cache.size());
        System.out.println("TRACER " + buffer.toString());
    }

    private static Connection simulateLookup() {
        final long DELAY_IN_MILLIS = 300L;
        try { Thread.sleep(DELAY_IN_MILLIS); } catch(Exception ex) {}
        return new Connection();
    }

    public static void clearCache() {
        for (String key : cache.keySet()) {
            Connection conn = cache.get(key);
            conn.close();
        }
    }

    public static Connection getConnection(String name) {
        Connection result = null;

        numCalls.getAndIncrement();

        synchronized (cache) {
        if (! cache.containsKey(name)) {
            numCacheMisses.getAndIncrement();
            Connection conn = simulateLookup();
            cache.put(name,conn);
        } else {
            numCacheHits.getAndIncrement();
        }
        }

        result = cache.get(name);

        return result;
    }
}
