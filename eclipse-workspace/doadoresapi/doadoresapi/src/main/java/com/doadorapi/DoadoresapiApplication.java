package com.doadorapi;

import java.util.Collections;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.doadorapi") // Adicione esta linha
public class DoadoresapiApplication {
	public static void main(String[] args) {
	    String port = System.getenv("PORT");
	    System.out.println("PORT env variable: " + port);
	    SpringApplication app = new SpringApplication(DoadoresapiApplication.class);
	    app.setDefaultProperties(Collections.singletonMap("server.port", port != null ? port : "8080"));
	    app.run(args);
	}

	@Bean
	public ApplicationRunner runner() {
	    return args -> {
	        System.out.println("Aplicação inicializada em " + System.currentTimeMillis());
	    };
	}
}
