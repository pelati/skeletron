package com.jami;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

/**
 * @author jonp
 */
@Path("/photos")
@Slf4j
public class PhotoRest {

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/upload")
    public Response storePhoto(@QueryParam("id") String id, String jws) {
        log.info("Uploading image.");

        return Response
                .status(OK)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
