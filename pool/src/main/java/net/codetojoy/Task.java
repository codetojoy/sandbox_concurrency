package net.codetojoy;

import java.util.*;

public class Task implements Runnable {
    private final int id;
    private final List<String> names;
    private final static int NUM_CALLS = 10;

    public Task(int id, List<String> names) {
        this.id = id;
        this.names = names;
    }

    @Override
    public void run() {
        int index = new Utils().getRandomNumberInRange(0,names.size()-1);
        String name = names.get(index);
        for (int i = 0; i < NUM_CALLS; i++) {
            Pool.getConnection(name);
        }
    }
}
