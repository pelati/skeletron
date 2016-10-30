package com.jami.toshl.rest;

import static com.jami.rest.RestConstants.HASH_HEADER;
import static com.jami.rest.RestConstants.USER_ID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @author jonp
 */
@Value
@Builder
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ToshlRequestParams {

    @Context
    private HttpServletRequest servletRequest;

    @HeaderParam(HASH_HEADER)
    private String hashValue;

    @QueryParam(USER_ID)
    private Long userId;

}
