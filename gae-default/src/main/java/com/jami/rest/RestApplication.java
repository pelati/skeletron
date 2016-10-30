package com.jami.rest;

import java.util.Set;

import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.common.collect.ImmutableSet;
import com.jami.challenge.rest.ChallengeFacade;
import com.jami.offer.rest.OfferFacade;
import com.jami.toshl.rest.ToshlFacade;
import com.jami.user.rest.UserFacade;

public class RestApplication extends Application {
    public static final Set<Object> facades = createFacades();
    public static final Set<Class<?>> classes = createClasses();

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return facades;
    }

    private static Set<Object> createFacades() {
        return ImmutableSet.builder()
                .add(new UserFacade())
                .add(new OfferFacade())
                .add(new ToshlFacade())
                .add(new ChallengeFacade())
                .build();
    }

    private static Set<Class<?>> createClasses() {
        return ImmutableSet.<Class<?>>builder()
                .add(JacksonJaxbJsonProvider.class)
                .build();
    }
}
