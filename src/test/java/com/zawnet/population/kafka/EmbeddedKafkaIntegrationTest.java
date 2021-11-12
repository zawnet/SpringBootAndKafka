package com.zawnet.population.kafka;

import com.zawnet.population.kafka.consumer.KafkaConsumer;

import com.zawnet.population.kafka.model.Message;
import com.zawnet.population.kafka.producer.KafkaSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9093", "port=9093" })
public class EmbeddedKafkaIntegrationTest {

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaSender producer;

   Message message;



    @Test
    public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived()
            throws Exception {
        message = new Message();
        message.setPopulation(3645000);
        message.setCountry("GERMANY");
        message.setCity("BERLIN");
        producer.sendMessage(message, "output_topic");


        //assertThat(consumer.getLatch().getCount(), equalTo(0L));
        //assertThat(consumer.getPayload(), containsString("embedded-test-topic"));
    }
}
