//package com.cupk.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JWTUtils {
//    private static String SignKey="wjfdly";
//    private static Long expire=43200000L;
//    public static String GenJWT(Map<String, Object> claims) {
////        Map<String, Object> claims = new HashMap<String, Object>();
////        claims.put("id",1);
////        claims.put("username","dly");
//        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256,"wjfdly")   //设置签名算法
//                .setClaims(claims)   // 设置自定义数据，封装在map集合中
//                .setExpiration(new Date(System.currentTimeMillis()+expire))//设置jwt有效期为一个小时
//                .compact();//字符串类型的返回值,JWT令牌
//        return jwt;
//    }
//    public static Claims ParseJwt(String token){
//        Claims claims = Jwts.parser()
//                .setSigningKey("wjfdly")  //签名密钥
//                .parseClaimsJws(token)
//                .getBody();
//        return claims;
//    }
//}