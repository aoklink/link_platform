package com.linkfeeling.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class,args);
    }

//    @Bean
//    GymAccountServer gymAccountServer(){
//        return new SimpleGymAccountServer();
//    }
//
//    @Bean
//    GymPlatformServer gymPlatformServer(){
//        return new SimpleGymPlatformServer();
//    }
}
