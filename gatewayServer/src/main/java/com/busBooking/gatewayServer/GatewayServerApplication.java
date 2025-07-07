package com.busBooking.gatewayServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p ->
                        p.path("/busBooking/busadmin/**")
                                .filters(f -> f.rewritePath("/busBooking/busadmin/(?<segment>.*)", "/${segment}")
                                        .circuitBreaker(config -> config.setName("busadminCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport"))
                                        .retry(retryConfig -> retryConfig.setRetries(4).setMethods(HttpMethod.GET)
                                                .setBackoff(Duration.ofMillis(100),Duration.ofMillis(2000),3,true)
                                        )
                                )
                                .uri("lb://BUSADMIN")

                )
                .route(p ->
                        p.path("/busBooking/buspassengers/**")
                                .filters(f -> f.rewritePath("/busBooking/buspassengers/(?<segment>.*)", "/${segment}")
                                        .circuitBreaker(config -> config.setName("buspassangersCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport")))
                                .uri("lb://BUSPASSENGERS")
                )
                .route(p ->
                        p.path("/busBooking/busschedule/**")
                                .filters(f -> f.rewritePath("/busbooking/busschedule/(?<segment>.*)", "/${segment}")
                                                .retry(retryConfig -> retryConfig.setRetries(3)
                                                        .setMethods(HttpMethod.GET)
                                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),3,true)
                                                )
                                        )
                                .uri("lb://BUSSCHEDULE")
                )
                .route(p ->
                        p.path("/busBooking/usermaster/**")
                                .filters(f -> f.rewritePath("/busbooking/usermaster/(?<segment>.*)", "/${segment}")
                                        .circuitBreaker(config -> config.setName("usermasterCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport")))
                                .uri("lb://USERMASTER")
                )
                .build();
    }


}
