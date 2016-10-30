package com.jami.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyFilter;

/**
 * Custom wrapper for the <code>ObjectifyFilter</code> class.
 * 
 * @author tomasl
 *
 */
public class CustomResponseHeaderFilter extends ObjectifyFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(request, response);
    }

}
