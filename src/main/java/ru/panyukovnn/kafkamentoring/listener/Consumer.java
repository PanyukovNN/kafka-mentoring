package ru.panyukovnn.kafkamentoring.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.kafka.change-fio", name = "enabled", havingValue = "true")
public class Consumer {

    @KafkaListener(topics = "${app.kafka.change-fio.topic}")
    public void consume(ConsumerRecord<String, String> consumerRecord,
                        @Payload(required = false) String payload,
                        @Header(KafkaHeaders.GROUP_ID) String groupId) {
        log.info("Ответ получен: " + consumerRecord.toString());

        // сложная логика

//        throw new RuntimeException();
    }

}
