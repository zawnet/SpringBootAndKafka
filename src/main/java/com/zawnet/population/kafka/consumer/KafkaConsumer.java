package com.zawnet.population.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zawnet.population.kafka.model.Message;
import com.zawnet.population.kafka.producer.KafkaSender;
import com.zawnet.population.localdata.service.CityService;
import com.zawnet.population.remotedata.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class KafkaConsumer {

    @Value("${spring.kafka.producer.topic}")
    private String producerTOPIC;

    CityService cityService;
    PopulationService populationService;
    KafkaSender kafkaSender;



    @Autowired
    public KafkaConsumer(CityService cityService, PopulationService populationService, KafkaSender kafkaSender) {
        this.cityService = cityService;
        this.populationService = populationService;
        this.kafkaSender = kafkaSender;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message)
    {
        System.out.println("message = " + message);
        ObjectMapper mapper = new ObjectMapper();
        Message msg = null;
        try {
            msg = mapper.readValue(message, Message.class);
            msg = cityService.getCountryByCity(msg);
            if(msg.getCountry() != null){
                msg = populationService.getPopulation(msg);
            }
            if(msg.getPopulation() > 0){
               kafkaSender.sendMessage(msg,producerTOPIC);
            }
        }catch (ListenerExecutionFailedException e){
            System.out.println("Unsupported conversion from kafka message");
        }
        catch (JsonMappingException e){
            System.out.println("Error while mapping from kafka message");
        }
        catch (JsonProcessingException e) {
            System.out.println("Unexpected format in input kafka message");
        }
        catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
}