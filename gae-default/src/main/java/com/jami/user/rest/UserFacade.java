package com.jami.user.rest;

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

import com.jami.entity.User;
import com.jami.rest.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/user")
public class UserFacade {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<User> create(@BeanParam UserRequestParams userRequestParams, User user) {
        ofy().save().entity(user).now();
        return Response.<User> builder()
                .body(user)
                .statusCode(200)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<User> get(@BeanParam UserRequestParams userRequestParams) {
        log.info("Retrieving user {}", userRequestParams.getUserId());
        User user = ofy().load().type(User.class).id(userRequestParams.getUserId()).now();
        return Response.<User> builder()
                .body(user)
                .statusCode(200)
                .build();
    }

    @DELETE
    public void delete(@BeanParam UserRequestParams params) {
        User user = ofy().load().type(User.class).id(params.getUserId()).now();
        if (user != null) {
            ofy().delete().entity(user).now();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response<List<User>> getList(@BeanParam UserRequestParams params) {
        List<User> users = ofy().load().type(User.class).list();
        return Response.<List<User>> builder()
                .body(users)
                .statusCode(200)
                .build();
    }

}