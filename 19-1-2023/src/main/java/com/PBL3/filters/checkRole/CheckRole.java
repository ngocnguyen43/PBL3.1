package com.PBL3.filters.checkRole;

import com.PBL3.utils.helpers.JWTVerify;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CheckRole {
    public static boolean check(HttpServletRequest req, Object... roles) {
//        System.out.println(roles[0].equals(decoded));
        try {
            String decoded = Objects.requireNonNull(JWTVerify.verifyingJWT(req.getHeader("ACCESS_TOKEN"))).getClaim("role").asString();
            for (Object role : roles) {
                if (role.equals(decoded)) return true;
            }
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }
}
