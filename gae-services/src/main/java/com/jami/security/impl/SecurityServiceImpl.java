package com.jami.security.impl;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.jami.security.SecurityService;

/**
 * @author jonp
 */
public enum SecurityServiceImpl implements SecurityService {
    INSTANCE;

    @Override
    public String calculateHash(String... args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg);
        }
        return Hashing.sha512().hashString(sb.toString(), Charsets.UTF_8).toString();
    }
}
