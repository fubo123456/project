package com.ps.sec.filter.util;

import io.jsonwebtoken.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boshen
 **/
public class JwtTokenUtils {

    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks"); // 寻找证书文件
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    static {//静态代码块 拿取证书里边的公钥和私钥
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, "123456".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray()); // jwt 为 命令生成整数文件时的别名
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Token 生成令牌
     * @param subject 令牌主题【用户名】
     * @param expireTime 令牌过期时间 秒
     * @param content 自定义内容
     * @return
     */
    public static String generateToken(String subject,int expireTime,Map<String,Object> content){
        return Jwts.builder()
                .setClaims(content)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+expireTime*1000))
                .signWith(SignatureAlgorithm.RS256,privateKey)
                .compact();
    }

    /**
     * Token 获取用户名
     * @param token
     * @return
     */
    public static String findUsername(String token){
        return  Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Tokne 获取自定主内容
     * @param tokne
     * @return
     */
    public static Map<String,Object> findContent(String token){
        Map<String,Object> content = null;
        try {
            content = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return content;
    }
    public static void main(String[] args) {
        String username = "admin";
        Map<String, Object> map = new HashMap<>();
        map.put("ip", "192.168.56.102");
        String aaa =  Jwts.builder()
                .setClaims(map)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+800000))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();


        System.out.println(aaa);
        Claims body = Jwts.parser().setSigningKey(publicKey).parseClaimsJws("eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbjEyMyIsImlwIjoiMDowOjA6MDowOjA6MDoxIiwiZXhwIjoxNTY5ODIxNjE1fQ.lwAWY3ZrT08IHK9eSyJXv4S90yNOc4e9WHGXqmGi7Ed0sd9GnBioBfKgvVtCrUDSlOwJy2Y2ysmV3prv6embdQBhA95VEh_lnPfQBpODVrtai-70PFASAufu9YRcxCByrKauzB4haJhyBON-WIxAyacH_28a8GspWz8u98Xjty8").getBody();

        System.out.println(body.getSubject());
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(aaa);
        System.out.println(claimsJws);

        BCryptPasswordEncoder bencoder = new BCryptPasswordEncoder();

        String encode = bencoder.encode("123456");
        System.out.println("密码："+encode);
        System.out.println( bencoder.matches("123456","$2a$10$RL94tHaTnDTDx3TnVcegAOZ9rIaH5f8illVnsvEmkThDz1OKeoswi"));
    }
}

