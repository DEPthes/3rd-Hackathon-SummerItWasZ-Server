package com.sample.domain.gpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.domain.gpt.service.GptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gpt")

public class GptController {
//        @Autowired
        private final GptService gptService;

        @Autowired
        public GptController(GptService gptService) {
                this.gptService = gptService;
        }

        @PostMapping
        public ResponseEntity<?> getAssistantMsg(@RequestParam String msg) throws JsonProcessingException {
                return gptService.getAssistantMsg(msg);
        }
}

