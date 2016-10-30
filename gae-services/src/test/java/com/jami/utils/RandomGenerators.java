package com.jami.utils;

import com.google.common.base.Preconditions;

import java.util.Random;

/**
 * @author igor
 */
public class RandomGenerators {
    private static final Random random = new Random();

    public static long anyLong(long from, long to) {
        long value = (long) (Math.abs(random.nextDouble()) * (to - from)) + from;
        Preconditions.checkArgument(from <= value);
        Preconditions.checkArgument(value < to);
        return value;
    }
}
