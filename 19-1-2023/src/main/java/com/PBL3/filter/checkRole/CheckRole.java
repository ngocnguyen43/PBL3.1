package com.PBL3.filter.checkRole;

import com.PBL3.utils.helpers.JWTVerify;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CheckRole {
    public static boolean handle(HttpServletRequest req,Object... roles){
        String decoded = Objects.requireNonNull(JWTVerify.verifyingJWT(req.getHeader("ACCESS_TOKEN"))).getClaim("role").asString();
        try{
            for (Object role : roles) {
                if (role.equals(decoded)) return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}