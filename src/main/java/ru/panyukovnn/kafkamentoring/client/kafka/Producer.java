package ru.panyukovnn.kafkamentoring.client.kafka;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.panyukovnn.kafkamentoring.dto.DemoRequest;
import ru.panyukovnn.kafkamentoring.dto.common.CommonRequest;
import ru.panyukovnn.kafkamentoring.util.JsonUtil;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.kafka.change-fio", name = "enabled", havingValue = "true")
public class Producer {

    private final JsonUtil jsonUtil;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.change-fio.topic}")
    private String topicIn;

//    @PostConstruct
    public void sendMessage() {
        CommonRequest<DemoRequest> request = CommonRequest.<DemoRequest>builder()
            .body(DemoRequest.builder()
                .id(UUID.randomUUID().toString())
                .text("Тест")
                .build())
            .build();

        kafkaTemplate.send(topicIn, jsonUtil.toJson(request))
            .thenAccept(sendResult -> log.info("Запрос отправлен в кафку: {}", sendResult));
    }
}
