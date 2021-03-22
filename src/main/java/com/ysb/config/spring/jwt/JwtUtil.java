package com.ysb.config.spring.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.util.Date;

/**
 * @author yinshuaibin
 * @date 2020/4/28 16:11
 */
@Slf4j
public class JwtUtil {
    private static String LOGIN_SIGN = "Authorization";

    private static String TOKEN_PREFIX = "ysb";

    private static String KEY = "ysb123";

    private static final long EXPIRE_TIME = 10 * 24 * 60 * 60 * 1000;

    static boolean verify(@NonNull String token, @NonNull String username){
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            DecodedJWT verify = verifier.verify(token);
            Date expiresAt = verify.getExpiresAt();
            return expiresAt.after(new Date());
        }catch (Exception e){
            log.error("校验密码出现错误, 错误为: {}", e.getMessage());
            return false;
        }
    }

    static String getUsername(@NonNull String token){
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getClaim("username").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }

    public static String sign(@NonNull String username){
        Date date = new Date(Clock.systemDefaultZone().millis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        return JWT.create().
                withClaim("username", username).
                withIssuedAt(new Date()).
                withExpiresAt(date).
                sign(algorithm);
    }

    static String getLoginSign(){
        return JwtUtil.LOGIN_SIGN;
    }

    static String getTokenPrefix(){
        return JwtUtil.TOKEN_PREFIX;
    }
}
