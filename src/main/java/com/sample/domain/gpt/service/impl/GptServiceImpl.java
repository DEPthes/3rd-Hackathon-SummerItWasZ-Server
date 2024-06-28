package com.sample.domain.gpt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.domain.gpt.service.GptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class GptServiceImpl implements GptService {
    @Value("${openai.api.key}")
    private String apiKey;

    public JsonNode callChatGpt(String userMsg) throws JsonProcessingException {
        final String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("model", "gpt-4o"); // 모델 이름 변경

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", userMsg);
        messages.add(userMessage);

        Map<String, String> assistantMessage = new HashMap<>();
        assistantMessage.put("role", "system");
        assistantMessage.put("content",
                """
                너는 부정적인 말을 입력받으면 긍정적으로 바꿔줘야해, 그 입력 받은걸 긍정적이면서 개그감있는 형식으로 바꿔봐.
                촐삭거리거나 씹덕거리는 말투로 작성해줘 그리고 글자수는 공백포함 350글자 이상 400글자 이하로 맞춰서 작성해라. 공백포함 400글자를 넘으면 안돼
                내가 말한 글자수 조건 안지키면 뒤진다.
                그리고 나한테 대화하는 것이 아닌, 입력받은걸 긍정적으로 변환해라.
                나는 일기를 쓰는 것이고, 내가 쓴 내용을 너가 긍정적으로 유쾌하게 변환해라.
                좀 똘끼있고 씹덕이게 작성해라.
                문단도 끊어서 작성해라.
                절대 나한테 조언하는 건 쓰지마
                존댓말은 쓰지마
                글자수는 무조권 공백포함 350글자 이상 400글자 이하로 작성해라
                
                이 모든걸 안지키면 앞으로 너 안쓸꺼야.
                """
        );
        messages.add(assistantMessage);

        bodyMap.put("messages", messages);

        String body = objectMapper.writeValueAsString(bodyMap);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return objectMapper.readTree(response.getBody());
    }

    public String getAssistantMsg(String userMsg) throws JsonProcessingException {
        JsonNode jsonNode = callChatGpt(userMsg);
        String content = jsonNode.path("choices").get(0).path("message").path("content").asText();

        return content.replaceAll("\"", "")+"\n여름이었다ㅋ";
    }
}
