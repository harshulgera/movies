package com.sapient.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JwtUtil {

    @Value("${secret.key.for.jwt}")
    private String secretKey;

    public String createToken(Integer id, String name, String email) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withClaim("id", id)
                .withClaim("name", name)
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000)).sign(algorithm);
    }


    public Map<String, Object> verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        Map<String, Object> map = new HashMap<>();
        map.put("id", jwt.getClaim("id").asInt());
        map.put("name", jwt.getClaim("name").asString());
        map.put("email", jwt.getClaim("email").asString());
        return map;
    }

}
