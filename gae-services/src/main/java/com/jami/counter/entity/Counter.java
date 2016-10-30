package com.jami.counter.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Builder;
import lombok.Value;

/**
 * @author igor
 */
@Entity
@Value
@Builder
public class Counter {
    @Id
    String id;

    long count;
}
