package com.jami.challenge.rest;

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

import com.jami.entity.Challenge;
import com.jami.entity.User;
import com.jami.rest.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/challenge")
public class ChallengeFacade {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<List<Challenge>> create(@BeanParam ChallengeRequestParams params, List<Challenge> challenges) {
        ofy().save().entities(challenges).now();
        return Response.<List<Challenge>> builder()
                .body(challenges)
                .statusCode(200)
                .build();
    }

    @DELETE
    public void delete(@BeanParam ChallengeRequestParams params) {
        Challenge challenge = ofy().load().type(Challenge.class).id(params.getChallengeId()).now();
        if (challenge != null) {
            ofy().delete().entity(challenge).now();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<List<Challenge>> get(@BeanParam ChallengeRequestParams params) {
        User user = ofy().load().type(User.class).id(params.getUserId()).now();
        log.info("Retrieving challenges for user '{}'", user);
        // TODO: filter the Challenges based on some user parameters
        List<Challenge> challenges = ofy().load().type(Challenge.class).list();
        return Response.<List<Challenge>> builder()
                .body(challenges)
                .statusCode(200)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response<List<Challenge>> delete(@BeanParam ChallengeRequestParams params, List<Challenge> challenges) {
        ofy().delete().entities(challenges).now();
        return Response.<List<Challenge>> builder()
                .body(challenges)
                .statusCode(200)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response<List<Challenge>> getList(@BeanParam ChallengeRequestParams params) {
        List<Challenge> challenges = ofy().load().type(Challenge.class).list();
        return Response.<List<Challenge>> builder()
                .body(challenges)
                .statusCode(200)
                .build();
    }

}