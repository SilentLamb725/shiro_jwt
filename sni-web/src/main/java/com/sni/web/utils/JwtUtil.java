package com.sni.web.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    // 生成签名使用的密钥
    private final String base64SecretKey;

    public JwtUtil(String secretKey) {
        this.base64SecretKey = Base64.encodeBase64String(secretKey.getBytes());
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    /**
     * 生成 JWT Token
     * @param username 签发人账号
     * @param expireDuration 过期时长，单位：毫秒
     * @param claims 加密签名涉及到的字段
     * @return
     */
    public String encode(String username, long expireDuration, Map<String, Object> claims) {

        if (claims == null) {
            claims = new HashMap<>();
        }

        JwtBuilder jwtBuilder = Jwts.builder()
                  .setClaims(claims)
                  .setId(UUID.randomUUID().toString())
                  .setIssuedAt(new Date())
                  .setSubject(username)
                  .signWith(SignatureAlgorithm.HS256, base64SecretKey)
                  .setExpiration(new Date(System.currentTimeMillis() + expireDuration));

        return jwtBuilder.compact();

    }

    /**
     * JWT Token 由头部，荷载部，签名部组成。签名部无法反向解密，头部和荷载部由Base64编码生成，可以反向编码，所以JWT Token中不能放敏感数据
     * @param jwtToken
     * @return
     */
    public Claims decode(String jwtToken) {
        return Jwts.parser()
                  .setSigningKey(base64SecretKey)
                  .parseClaimsJws(jwtToken)
                  .getBody();
    }

    /**
     * 校验token
     * 可以使用官方的校验
     * 或者自定义校验规则，例如在token中携带密码，进行加密处理后和数据库中的密码比较
     *
     * @param jwtToken
     * @return
     */
    public boolean isVerify(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(Base64.decodeBase64(base64SecretKey));
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(jwtToken);  // 校验不通过会抛出异常
        } catch (SignatureVerificationException e) {
            LOGGER.error("============解密失败, jwtToken:{}", jwtToken, e);
            return false;
        } catch (TokenExpiredException e) {
            LOGGER.info("============token已过期");
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;

        /*
        Claims claims = decode(jwtToken);
        if (claims.get("password").equals(user.getPassword())) {
            return true;
        }
        */
    }

    public static void main(String[] args) {

        String jwtToken = egEncrypt();
        JwtUtil jwtUtil = new JwtUtil("whoareyou");

        jwtUtil.decode(jwtToken).forEach((key, value) -> System.out.println(key + ":" + value));

        System.out.println(jwtUtil.isVerify(jwtToken) ? "合法" : "不合法");
    }

    public static String egEncrypt() {
        JwtUtil jwtUtil = new JwtUtil("whoareyou");

        Map<String, Object> map = new HashMap<>();
        map.put("username", "gaoyang");
        map.put("password", "123456");

        String jwtToken = jwtUtil.encode("1", 30000, map);
        System.out.println(jwtToken);


        return jwtToken;
    }
}
