package com.skbroadband.doms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {com.github.gavlyukovskiy.boot.jdbc.decorator.DataSourceDecoratorAutoConfiguration.class,
        com.github.gavlyukovskiy.cloud.sleuth.SleuthListenerAutoConfiguration.class,
        com.github.gavlyukovskiy.boot.jdbc.decorator.DataSourceDecoratorAutoConfiguration.class})
public class DomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomsApplication.class, args);
    }

}
