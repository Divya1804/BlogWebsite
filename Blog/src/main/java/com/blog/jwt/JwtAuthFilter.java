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

package com.blog.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtTokenHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");

        //Bearer 2352345235sdfrsfgsdfsdf

        logger.info("Header :  {}", requestHeader);
        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            token = requestHeader.substring(7);
            System.out.println(token);
            try {
                username = this.jwtHelper.getUsernameFromToken(token);
                System.out.println(username);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Invalid Header Value !! ");
        }


        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //fetch user detail from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
                logger.info("Validation fails !!");
            }


        }

        filterChain.doFilter(request, response);


    }

}