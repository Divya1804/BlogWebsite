//package com.blog.jwt;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import java.io.IOException;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsService userServices;
//
//    @Autowired
//    private JwtTokenHelper jwtTokenHelper;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String reqToken = request.getHeader("Authorization");
//        System.out.println(reqToken);
//
//        String username = null;
//        String token = null;
//
//        if(reqToken != null && reqToken.startsWith("Bearer")){
//            token = reqToken.substring(7);
//            try {
//                username = this.jwtTokenHelper.getUsernameFromToken(token);
//            }
//            catch(IllegalArgumentException e){
//                System.out.println("Unable to get JWT token");
//            }
//            catch(ExpiredJwtException e){
//                System.out.println("JWT token has been expired");
//            }
//            catch(MalformedJwtException e){
//                System.out.println("Invalid JWT Exceetion");
//            }
//        }
//        else{
//            System.out.println("JWT Token does not starts with bearer");
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = this.userServices.loadUserByUsername(username);
//            if (this.jwtTokenHelper.validateToken(token, userDetails)){
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//            else {
//                System.out.println("Invalid JWT Token");
//            }
//        }else{
//            System.out.println("Username i null or context is not null");
//        }
//        filterChain.doFilter(request, response);
//    }
//}
