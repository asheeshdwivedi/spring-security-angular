package com.example.setup;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
public class SecurityConstants {


    public static final String ADMIN1_EMAIL = "admin1@xxx.com";
    public static final String ADMIN1_PASS = "admin1Pass";
    public static final String ADMIN2_EMAIL = "admin2@xxx.com";
    public static final String ADMIN2_PASS = "admin2Pass";
    public static final String USER1_EMAIL = "user1@xxx.com";
    public static final String USER1_PASS = "user1Pass";


    public static final class Authorities {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

    private SecurityConstants() {
        throw new AssertionError();
    }
}
