package net.codetojoy;

import java.util.Random;

public class Utils {
    public int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
     	return r.nextInt((max - min) + 1) + min;
    }
}
