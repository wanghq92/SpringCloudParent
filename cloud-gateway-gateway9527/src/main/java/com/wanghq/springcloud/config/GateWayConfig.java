package com.wanghq.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-27 22:12
 */
@Configuration
public class GateWayConfig {
    /**
     * 配置了一个id为route-name的路由规则，
     * 当访问地址 http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     *
     * id：     #路由的ID，没有固定规则但要求唯一，建议配合服务名
     * uri:     #匹配后提供服务的路由地址
     * path:    # 断言，路径相匹配的进行路由
     */

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_wanghq",
                r->r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route2_wanghq",
                r->r.path("/guoji")
                        .uri("http://news.baidu.com")).build();
        return routes.build();
    }
}
