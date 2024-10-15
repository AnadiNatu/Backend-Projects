package com.example.swiggy_gateway.filter;


import com.example.swiggy_gateway.util.JwtUtil;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;


//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;


    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            if (validator.isSecured.test(exchange.getRequest())){
                //header contains header or not

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader!=null && authHeader.startsWith("Bearer ")){
                    authHeader=authHeader.substring(7); // actual token
                }

                try {
//                    // Rest call to AUTH service
//                    restTemplate.getForObject("http://IDENTITY-SERVICE//validate?token"+authHeader , String.class);
//                    // This makes a security breach , so there is another implementation

                      jwtUtil.validToken(authHeader);
                }catch (Exception ex){
                    System.out.println("Invalid Access");
                    throw new RuntimeException("Invalid auth");
                }
            }


            return chain.filter(exchange);
        });
    }

    public static class Config{



    }


}
