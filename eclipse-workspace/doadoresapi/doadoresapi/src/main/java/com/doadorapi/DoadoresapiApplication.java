package com.doadorapi;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoadoresapiApplication {
	public static void main(String[] args) {
	    String port = System.getenv("PORT");
	    System.out.println("PORT env variable: " + port);
	    SpringApplication app = new SpringApplication(DoadoresapiApplication.class);
	    app.setDefaultProperties(Collections.singletonMap("server.port", port != null ? port : "8080"));
	    app.run(args);
	}
}
