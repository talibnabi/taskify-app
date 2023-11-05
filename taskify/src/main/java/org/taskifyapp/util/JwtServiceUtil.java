package org.taskifyapp.util;

public class JwtServiceUtil {

    private JwtServiceUtil() {

    }

    public final static String JWT_SECRET_KEY = "88a920881cb162d917ef47935e108c40b7b0c3927142e7337370bb0cbe117971";
    public static final String JWT_AUTHORITIES_KEY = "AUTH_KEY";

    public static final String JWT_SERVICE_ADMIN = "ROLE_ADMIN";
    public static final String JWT_SERVICE_USER = "ROLE_USER";
    public static final String JWT_SERVICE_UNKNOWN = "ROLE_UNKNOWN";
}
