package com.example.NewSecurityJwt.filter;

import com.example.NewSecurityJwt.config.UserInfoUserDetailService;
import com.example.NewSecurityJwt.config.UserInfoUserDetails;
import com.example.NewSecurityJwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
//(11)
        // doFilterInternal method is part of a custom filter class that processes incoming Http requests to validate JWT tokens . It ensures that only authenticated requests proceed further in the application


        String authHeader = request.getHeader("Authorization");
// the method starts by extracting the 'Authorization' header from the Http request using above request object method
        String token = null;

        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer "))
        // condition checks Authorization header is not null and starts with string "Bearer "
        {
            token = authHeader.substring(7);
            // If header is valid extract the JWT token from the header , removing the "Bearer " prefix , leaving only the token
            username = jwtService.extractUsername(token);
            // extracting 'username' using 'jwtService'
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        // This condition ensures that the request hasn't already been authenticated

            UserDetails userDetails = userDetailService.loadUserByUsername(username);
            // If the user is not authenticated , the method loads the user's details from the DB using UserDetailsService impl

            if (jwtService.validateToken(token,userDetails)){
                //This condition ensures the token is both valid and not expired

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // If the token is valid, the method creates an Authentication object and sets it in the SecurityContext. This step finalizes the authentication, allowing the request to proceed with the appropriate user permissions.

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
        // This line is used to pass the request and response objects to the next filter in the chain or to the target resource(like a servlet or controller) if its the last filter
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//
//        String authHeader = request.getHeader("Authorization");
//
//        String token = null;
//        String username = null;
//
//        if(authHeader!=null && authHeader.startsWith("Bearer ")){
//
//            token = authHeader.substring(7);
//
//            username = jwtService.extractUsername(token);
//        }
//
//        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
//
//            UserDetails userDetails = userDetailService.loadUserByUsername(username);
//
//            // After getting username and verifying the security context holder  we will fetch the userDetails by the extracted username from the token and now we have to give this userDetail object to the JwtService to be validated by the function
//
//            if (jwtService.validateToken(token , userDetails)){
//
//                // if th jwt is valid then create an authentication type object and give it to the SecurityContextHolder
//
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//
//                // now we have to set the details for the token . details are nothing but the servlet request
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                //Now we have to set the authenticationToken with the SecurityContextHolder
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//
//        filterChain.doFilter(request,response);
//    }



}
