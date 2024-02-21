//package com.blog.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtTokenHelper {
//
//    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//    private String secret = "jwtTokenKey";
//
//    public String getUsernameFromToken(String token){
//        return getClaimForToken(token, Claims::getSubject);
//    }
//
//    public Date getExpirationDateFromToken(String token){
//        return getClaimForToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimForToken(String token, Function<Claims, T> claimResolver){
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//
//    private Boolean isTokenExpired(String token){
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    public String generateToken(UserDetails user){
//        Map<String, Object> claims = new HashMap<>();
//        return doGenerateToken(claims, user.getUsername());
//    }
//
//    public String doGenerateToken(Map<String, Object> claims, String subject){
//        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 10000))
//                .signWith(SignatureAlgorithm.HS512, secret).compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails user){
//        final String username = getUsernameFromToken(token);
//        return (username.equals(user.getUsername()) && !isTokenExpired(token));
//    }
//
//}
