package com.test.gateway.filter;

import com.laq.libsharejwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {
    @Autowired
    JwtUtil jwtUtil;
    private static final List<String> openPaths = List.of("/auth/login");

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var req = exchange.getRequest();
        if (openPaths.stream().anyMatch(p -> req.getPath().toString().contains(p))) {
            return chain.filter(exchange);
        }

        String token = req.getHeaders().getFirst("Authorization");
        if (token == null || !token.startsWith("Bearer ") || jwtUtil.isInvalid(token.substring(7))) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        Claims claims = jwtUtil.getClaims(token.substring(7));
        exchange.getRequest().mutate()
                .header("X-Auth-Username", claims.getSubject())
                .build();

        return chain.filter(exchange);
    }

    public int getOrder() { return -1; }
}
