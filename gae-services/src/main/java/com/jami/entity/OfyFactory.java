package com.jami.entity;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.googlecode.objectify.ObjectifyFactory;

public class OfyFactory extends ObjectifyFactory {
    // All entities must be registered here
    private static Set<Class<?>> REGISTERED_CLASSES = ImmutableSet.<Class<?>> builder()
            .add(Challenge.class)
            .add(User.class)
            .add(Offer.class)
            .add(Zadeva.class)
            .build();

    public OfyFactory() {
        for (Class<?> clazz : REGISTERED_CLASSES) {
            this.register(clazz);
        }

    }

    @Override
    public Ofy begin() {
        return new Ofy(this);
    }
}
