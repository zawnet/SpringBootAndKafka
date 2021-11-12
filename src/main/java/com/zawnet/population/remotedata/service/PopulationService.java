package com.zawnet.population.remotedata.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zawnet.population.kafka.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PopulationService {

    @Value("${spring.population.service.url}")
    private String url;

    private String allCitiesPopulationEndpoint = "/population";

    private RestTemplate restTemplate = new RestTemplate();


    //TODO: In future, if api will give endpoint witch return one City.
    public Message getCityPopulationFromApi(Message msg) throws NoSuchMethodException {
        throw new NoSuchMethodException("The method not implemented yet.");
    }

    public List<Message> getAllCitiesPopulationFromApi() {
        String txt = "";
        List<Message> messageList= new ArrayList<>();
        txt = restTemplate.getForObject(url+allCitiesPopulationEndpoint, String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            if(txt.startsWith("{[")){
                txt = txt.substring(1);
            }
             messageList = mapper.readValue(txt, new TypeReference<List<Message>>(){});
        }
        catch (JsonParseException e){
            System.out.println(e.getMessage());
        }
        catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        return messageList;
    }

    public Message getPopulation(Message msg) {
        Message result =  getAllCitiesPopulationFromApi().stream()
                .filter(message -> message.getCity().equals(msg.getCity()))
                .findFirst().orElseThrow(() ->
                        new NoSuchElementException( "Population for city: "+msg.getCity()+" were not found"));
        msg.setPopulation(result.getPopulation());
        return msg;
    }
}
