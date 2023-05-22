package com.PBL3.utils.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTVerify {
    // private static Logger logger = LoggerFactory.getLogger(JWTVerify.class);

    // private static
    private static String token;

    public JWTVerify(String token) {
        JWTVerify.token = token;
    }

    public static DecodedJWT verifyingJWT(String token) {
        try {

            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
