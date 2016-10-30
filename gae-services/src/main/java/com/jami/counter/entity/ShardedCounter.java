package com.jami.counter.entity;

import lombok.Builder;

/**
 * @author igor
 */
@Builder
public class ShardedCounter {
    private int numShards;
}
