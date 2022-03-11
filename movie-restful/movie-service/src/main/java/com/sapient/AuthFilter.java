package com.sapient;


import com.sapient.vob.UserVob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
@WebFilter("/")
@Order(1)
public class AuthFilter implements Filter {

    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.debug("AuthFilter.doFilter called");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String authorization = req.getHeader("Authorization");

        if (authorization != null) {
            String[] parts = authorization.split(" ");
            if (parts.length == 2) {
                String token = parts[1];

                UserVob userVob = WebClient.create(authServiceUrl)
                        .get()
                        .uri("/?token=" + token)
                        .retrieve()
                        .bodyToMono(UserVob.class)
                        .block();
                req.setAttribute("user", userVob);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
