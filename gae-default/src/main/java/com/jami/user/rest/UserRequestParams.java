package com.jami.user.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.xml.bind.annotation.XmlRootElement;

import static com.jami.rest.RestConstants.*;

/**
 * @author jonp
 */
@Value
@Builder
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserRequestParams {
	
    @Context
    private HttpServletRequest servletRequest;

    @HeaderParam(HASH_HEADER)
    private String hashValue;

    @QueryParam(DEVICE_ID)
    private String deviceId;

    @QueryParam(TIMESTAMP)
    private Long timestamp;

    @QueryParam(OS_VERSION)
    private Long osVersion;

    @QueryParam(TOKEN)
    private Long token;

    @QueryParam(USER_ID)
    private Long userId;

}
