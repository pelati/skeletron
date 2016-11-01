package com.jami.zadeva.rest;

import static com.jami.entity.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jami.entity.Zadeva;
import com.jami.entity.ZadevaRequest;
import com.jami.entity.ZadevaResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/zadeva")
public class ZadevaFacade {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ZadevaResponse create(@BeanParam ZadevaRequestParams userRequestParams, ZadevaRequest zadeveRequest) {
        log.info("Saving: {}", zadeveRequest);
        if (zadeveRequest != null && zadeveRequest.getZadeve() != null) {
            ofy().save().entities(zadeveRequest.getZadeve()).now();
            return ZadevaResponse.builder()
                    .errorMessage(null)
                    .returnType(0)
                    .build();
        }

        // Error
        return ZadevaResponse.builder()
                .errorMessage("No zadeve in the request")
                .returnType(1)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/list")
    public ZadevaResponse get(@BeanParam ZadevaRequestParams userRequestParams, ZadevaRequest zadeveRequest) {
        log.info("Loading: {}", zadeveRequest);
        List<String> zadevaNames = new ArrayList<>();
        if (zadeveRequest != null && zadeveRequest.getZadeve() != null) {
            for (Zadeva zadeva : zadeveRequest.getZadeve()) {
                zadevaNames.add(zadeva.getName());
            }
            List<Zadeva> zadeve = ofy().load().type(Zadeva.class).filter("name in", zadevaNames).list();
            return ZadevaResponse.builder()
                    .zadeve(zadeve)
                    .errorMessage(null)
                    .returnType(0)
                    .build();
        }

        // Error
        return ZadevaResponse.builder()
                .errorMessage("No zadeve in the request")
                .returnType(1)
                .build();
    }

    @DELETE
    public void delete(@BeanParam ZadevaRequestParams params) {
        log.info("Deleting: {}", params.getZadevaId());
        Zadeva zadeva = ofy().load().type(Zadeva.class).id(params.getZadevaId()).now();
        if (zadeva != null) {
            ofy().delete().entity(zadeva).now();
        }
    }

}