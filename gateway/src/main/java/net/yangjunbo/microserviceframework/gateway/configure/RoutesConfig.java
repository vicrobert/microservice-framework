package net.yangjunbo.microserviceframework.gateway.configure;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {
    @Bean
    public RouteLocator staticRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri("http://httpbin.org:80"))
//                .build();
        return builder.routes()
                .route(p -> p.path("/kbsweb").uri("http://sports.qq.com/"))
                .route(p -> p.path("/mobile").uri("https://news.qq.com/"))
                .route(p -> p.path("/mobile").uri("https://172.16.49.66:6443/v1/app/echo/"))
                .route(p -> p.path("/userAuth/index").uri("https://om.qq.com/"))
                .route(p -> p.path("/PartyMember/list").uri("http://api.jc.scity.cn/NankaiParty/"))
                .route(p -> p.path("/zqw").uri("https://172.16.49.66:6443/v1/app/echo/"))
                .build();
    }
}
