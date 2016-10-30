package com.jami.offer.rest;

import static com.jami.entity.OfyService.ofy;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jami.entity.Offer;
import com.jami.entity.User;
import com.jami.rest.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/offer")
public class OfferFacade {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<List<Offer>> create(@BeanParam OfferRequestParams params, List<Offer> offers) {
        ofy().save().entities(offers).now();
        return Response.<List<Offer>> builder()
                .body(offers)
                .statusCode(200)
                .build();
    }

    @DELETE
    public void delete(@BeanParam OfferRequestParams params) {
        Offer offer = ofy().load().type(Offer.class).id(params.getOfferId()).now();
        if (offer != null) {
            ofy().delete().entity(offer).now();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<List<Offer>> get(@BeanParam OfferRequestParams params) {
        User user = ofy().load().type(User.class).id(params.getUserId()).now();
        log.info("Retrieving offers for user '{}'", user);
        // TODO: filter the offers based on some user parameters
        List<Offer> offers = ofy().load().type(Offer.class).list();
        return Response.<List<Offer>> builder()
                .body(offers)
                .statusCode(200)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response<List<Offer>> delete(@BeanParam OfferRequestParams params, List<Offer> offers) {
        ofy().delete().entities(offers).now();
        return Response.<List<Offer>> builder()
                .body(offers)
                .statusCode(200)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response<List<Offer>> getList(@BeanParam OfferRequestParams params) {
        List<Offer> offers = ofy().load().type(Offer.class).list();
        return Response.<List<Offer>> builder()
                .body(offers)
                .statusCode(200)
                .build();
    }
}