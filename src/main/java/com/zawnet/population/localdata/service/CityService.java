package com.zawnet.population.localdata.service;

import com.zawnet.population.kafka.model.Message;

import com.zawnet.population.localdata.model.City;
import com.zawnet.population.localdata.repository.CityRepository;
import com.zawnet.population.remotedata.service.PopulationService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final PopulationService populationService;


    public CityService(CityRepository cityRepository, PopulationService populationService) {
        this.cityRepository = cityRepository;
        this.populationService = populationService;

    }

    public Message getCountryByCity(Message msg) throws NoSuchElementException{
        City city;
        if((city = cityRepository.findByCity(msg.getCity())) != null){
            msg.setCountry(city.getCountry());
            msg = populationService.getPopulation(msg);
            return msg;
        }
        else {
            throw new NoSuchElementException("No country found for: "+ msg.getCity());
        }

    }
}
