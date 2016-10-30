package com.jami.entity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.util.Closeable;

/**
 * Custom wrapper for the <code>ObjectifyFilter</code> class.
 * 
 * @author tomasl
 *
 */
public class OfyFilter extends ObjectifyFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        try (Closeable closeable = OfyService.begin()) {
            chain.doFilter(request, response);
        }
    }

}
