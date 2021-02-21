package cn.bupt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@MapperScan("cn.bupt.mapper")
public class ResourcePoolServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourcePoolServerApplication.class, args);
    }
}