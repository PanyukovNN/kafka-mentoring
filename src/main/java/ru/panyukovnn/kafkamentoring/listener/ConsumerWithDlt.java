package ru.panyukovnn.kafkamentoring.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.kafka.change-fio", name = "enabled", havingValue = "true")
public class ConsumerWithDlt {

    @RetryableTopic(
        attempts = "1",
        kafkaTemplate = "kafkaTemplate",
        dltStrategy = DltStrategy.FAIL_ON_ERROR)
//    @KafkaListener(topics = "${app.kafka.change-fio.topic}")
    public void consume(ConsumerRecord<String, String> consumerRecord) {
        log.info("Ответ получен: " + consumerRecord.toString());

        throw new RuntimeException();
    }

    @DltHandler
    public void handleDlt(String payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Произошла ошибка при чтении сообщения из топика: topic={}, payload={}", topic, payload);
    }

}
