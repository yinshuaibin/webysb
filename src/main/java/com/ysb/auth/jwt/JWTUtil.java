package com.ysb.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yinshuaibin
 * @date 2020/4/28 16:11
 */
@Slf4j
public class JWTUtil {

    public static final Map<String, Map<String, String>> loginUser = new LinkedHashMap<>();

    private static final  long EXPIRE_TIME = 10 * 24 * 60 * 60 * 1000;

    public static boolean verify(String token, String username, String password){
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            DecodedJWT verify = verifier.verify(token);
            Date expiresAt = verify.getExpiresAt();
            return expiresAt.after(new Date());
        }catch (Exception e){
            log.error("校验密码出现错误, 错误为: {}", e.getMessage());
            return false;
        }
    }

    public static String getUsername(String token){
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getClaim("username").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }

    public static String sign(String username, String pasword){
        Date date = new Date(Clock.systemDefaultZone().millis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(pasword);
        return JWT.create().
                withClaim("username", username).
                withIssuedAt(new Date()).
                withExpiresAt(date).
                sign(algorithm);
    }
}
