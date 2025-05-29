package com.doadorapi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvChecker {

    @Value("${spring.datasource.url:NOT_SET}")
    private String datasourceUrl;

    @Value("${spring.datasource.username:NOT_SET}")
    private String datasourceUsername;

    @Value("${spring.datasource.password:NOT_SET}")
    private String datasourcePassword;

    @PostConstruct
    public void check() {
        System.out.println("spring.datasource.url = " + datasourceUrl);
        System.out.println("spring.datasource.username = " + datasourceUsername);
        System.out.println("spring.datasource.password está configurada? " + (!datasourcePassword.equals("NOT_SET")));
    }
}
