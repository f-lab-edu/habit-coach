package app.habit.service.gpt.coach;

import app.habit.dto.openaidto.GptRsWrapper;
import app.habit.dto.openaidto.GptRsWrapper.Choice.Message;
import app.habit.dto.openaidto.PhaseEvaluationRs;
import app.habit.service.gpt.request.RequestPrompt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class SmartGoalFeedbackGptCoach {

    @Value("${app.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public String requestSmartGoalFeedback(RequestPrompt requestBody, String url) {
        GptRsWrapper adviceBody = writeFeedback(requestBody, url);
        return parseFeedback(adviceBody);
    }

    private GptRsWrapper writeFeedback(RequestPrompt requestBody, String url) {
        return Optional.ofNullable(requestBody)
                .map(body -> new HttpEntity<>(body, createHeaders()))
                .map(entity -> restTemplate.exchange(url, HttpMethod.POST, entity, GptRsWrapper.class))
                .map(HttpEntity::getBody)
                .orElseThrow(NullPointerException::new);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private String parseFeedback(GptRsWrapper body) {
        Message message = body.getChoices().get(0).getMessage();
        String content = message.getContent();
        TypeReference<String> typeReference = new TypeReference<>() {
        };
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(content, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}