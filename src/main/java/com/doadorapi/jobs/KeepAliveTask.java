package com.doadorapi.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepAliveTask {

    private final RestTemplate restTemplate = new RestTemplate();

    // EXECUTA A CADA 10 MINUTOS (600_000 MS)
    @Scheduled(fixedRate = 600000)
    public void sendKeepAliveRequest() {
        try {
            String url = "https://doadores-spring.onrender.com/cadastro.html";
            restTemplate.getForObject(url, String.class);
            System.out.println("Keep-alive enviado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao enviar keep-alive: " + e.getMessage());
        }
    }
}
