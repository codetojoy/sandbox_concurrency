package net.codetojoy;

import java.util.*;

public class Task implements Runnable {
    private final int id;
    private final List<String> names;
    private final boolean usePool2;

    private final static int NUM_CALLS = 10;

    public Task(int id, List<String> names, boolean usePool2) {
        this.id = id;
        this.names = names;
        this.usePool2 = usePool2;
    }

    @Override
    public void run() {
        int index = new Utils().getRandomNumberInRange(0,names.size()-1);
        String name = names.get(index);

        for (int i = 0; i < NUM_CALLS; i++) {
            if (usePool2) {
                Pool2.getConnection(name);
            } else {
                Pool.getConnection(name);
            }
        }
    }
}
