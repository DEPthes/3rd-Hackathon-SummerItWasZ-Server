//package com.sample.global.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class ChatGPTConfig {
//
//    @Value("${CHATGPT_API_KEY}")
//    private String secretKey;
//
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate;
//    }
//
//    @Bean
//    public HttpHeaders httpHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(secretKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return headers;
//    }
//}
//
