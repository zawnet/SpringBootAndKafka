package com.zawnet.population.kafka.producer;

import com.zawnet.population.kafka.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import java.util.Map;

@Component
public class KafkaSender {

    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    KafkaSender(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message, String topicName) {
        kafkaTemplate.send(topicName, message);
    }

}
