package com.db2020prj.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.db2020prj.entities.User;

import java.util.Date;

public class Token {
    private static final long expireMs = 24 * 60 * 60 * 1000; // 30 minutes
    private static final String privateKey = "2020DBprjPrivateKey";
    private static final JWTVerifier verifier = JWT.require(Algorithm.HMAC512(privateKey)).build();

    public static String getToken(User user, boolean isAdmin) {
        return JWT.create().withClaim("userId", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("nickname", user.getNickname())
                .withClaim("isAdmin", isAdmin)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireMs))
                .sign(Algorithm.HMAC512(privateKey));
    }

    public static DecodedJWT verify(String token) throws InvalidTokenException{
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT.getExpiresAt().before(new Date())) {
                throw new InvalidTokenException("Token expired. Please login again.", 300);
            }
            return decodedJWT;
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException(e.getMessage(), 300);
        }
    }
}
