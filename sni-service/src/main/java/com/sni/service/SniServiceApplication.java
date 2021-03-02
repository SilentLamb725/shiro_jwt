package com.sni.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.locks.LockSupport;

@SpringBootApplication
@ImportResource(value = {"classpath:dubbo-provider.xml"})
public class SniServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SniServiceApplication.class, args);
        LockSupport.park();
    }
}
