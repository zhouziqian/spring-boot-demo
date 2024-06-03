package com.zhouqi.demoauthsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class DemoAuthSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAuthSecurityApplication.class, args);
    }

}
