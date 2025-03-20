package com.zut.tlias.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

//jwt工具类
public class JwtUtils {
    //指定签名秘钥
    //不能太短
    private static String singKey = "comzut";

    //指定过期时间
    private static long expire = 60 * 60 * 12*1000L;

    //生成jwt令牌
   public static String generateJwt(Map<String, Object> claims){
       String jwt= Jwts.builder()
               //设置令牌中payload的中自定义内容
               .addClaims(claims)
               //设置签名算法和签名秘钥
               .signWith(SignatureAlgorithm.HS256,singKey)
               //设置过期时间
               //需要传递date类型的数据
               //date类型只能接受毫秒
               .setExpiration(new Date(System.currentTimeMillis()+expire))
               //将jwt令牌返回
               .compact();
       return jwt;
   }

    //解析jwt令牌
   public static Claims parseJwtToken(String jwtToken){
       Claims claims = Jwts.parser()
               //设置解析秘钥
               .setSigningKey(singKey)
               //设置要解析的jwt令牌
               .parseClaimsJws(jwtToken)
               //拿到jwt令牌中的自定义内容
               .getBody();
       return claims;
   }
}
