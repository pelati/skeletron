package com.jami.security;

/**
 * @author jonp
 */
public interface SecurityService {
    String calculateHash(String... objects);
}
