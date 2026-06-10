package com.demo.edge;

import java.util.Locale;

/** HTTP header helpers. */
public final class HeaderUtils {

    private HeaderUtils() {
    }

    public static String bearerToken(String authorization) {
        if (authorization == null) {
            return "";
        }
        String prefix = "bearer ";
        if (authorization.toLowerCase(Locale.ROOT).startsWith(prefix)) {
            return authorization.substring(prefix.length()).trim();
        }
        return "";
    }
}
