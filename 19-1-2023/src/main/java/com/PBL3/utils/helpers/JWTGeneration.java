package com.PBL3.utils.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class JWTGeneration {
    private final KeyPairGenerator keyPairGenerator;
    private final KeyPair keyPair;

    public JWTGeneration() throws NoSuchAlgorithmException {
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
    }

    public String generate(Map<String, String> payload) {
        Builder tokenBuilder = JWT.create().withExpiresAt(Date.from(Instant.now().plusSeconds(300)))
                .withIssuedAt(Date.from(Instant.now())).withIssuer("minhngoc");
        payload.entrySet().forEach(action -> tokenBuilder.withClaim(action.getKey(), action.getValue()));
        return tokenBuilder
                .sign(Algorithm.RSA256(((RSAPublicKey) keyPair.getPublic()), ((RSAPrivateKey) keyPair.getPrivate())));
    }
}
