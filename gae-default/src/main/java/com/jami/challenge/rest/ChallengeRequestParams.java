package com.jami.challenge.rest;

import static com.jami.rest.RestConstants.CHALLENGE_ID;
import static com.jami.rest.RestConstants.USER_ID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @author tomasl
 */
@Value
@Builder
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ChallengeRequestParams {

    @Context
    private HttpServletRequest servletRequest;

    @QueryParam(CHALLENGE_ID)
    private Long challengeId;

    @QueryParam(USER_ID)
    private Long userId;

}
