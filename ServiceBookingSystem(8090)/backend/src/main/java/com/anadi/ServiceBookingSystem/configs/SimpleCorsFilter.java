package com.anadi.ServiceBookingSystem.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    @Value("${app.client.url")
    private String clientAppUrl = "";

    public SimpleCorsFilter(){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        ServletResponse res = servletResponse;
        HttpServletResponse response= (HttpServletResponse) res;
        ServletRequest req = servletRequest;
        HttpServletRequest  request = (HttpServletRequest) req;

        Map<String , String> map =  new HashMap<>();

        String originHeader = request.getHeader("origin");

        response.setHeader("Allow-Control-Allow-Origin",originHeader);
        response.setHeader("Allow-Control-Allow-Methods" , "POST , GET , PUT , OPTIONS , DELETE");
        response.setHeader("Allow-Control-Max-Age" , "3688");
        response.setHeader("Allow-Control-Allow-Headers" , "*");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            filterChain.doFilter(req,res);
        }
    }
}
