package com.jami.counter.service.impl;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.jami.counter.entity.Counter;
import com.jami.counter.service.CounterService;
import lombok.extern.slf4j.Slf4j;

import static com.google.appengine.api.memcache.MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * @author igor
 */
@Slf4j
public class CounterServiceImpl implements CounterService {
    private final MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService();

    @Override
    public void increment(String id) {
        Long count = memcacheService.increment(id, 1);
        if (count == null) {
            rebuildMemcacheCounter(id);
            memcacheService.increment(id, 1);
        }
    }

    @Override
    public long getCount(String id) {
        Long count = (Long) memcacheService.get(id);
        if (count == null) {
            count = rebuildMemcacheCounter(id);
        }

        return count;
    }

    /**
     * Try getting count from DS and store it in Memcache.
     * @param id Counter ID
     * @return Value from DS
     */
    private long rebuildMemcacheCounter(String id) {
        log.error("No value in Memcache for counter '{}'.", id);

        long count = 0L;
        Counter counter = ofy().load().type(Counter.class).id(id).now();
        // If counter exists in DS, use this value
        if (counter != null) {
            count = counter.getCount();
        }

        // Put it in Memcache if it does not exist
        memcacheService.put(id, count, null, ADD_ONLY_IF_NOT_PRESENT);

        return count;
    }
}
