package com.jami.counter.service;

/**
 * @author igor
 */
public interface CounterService {
    void increment(String id);
    long getCount(String id);
}
