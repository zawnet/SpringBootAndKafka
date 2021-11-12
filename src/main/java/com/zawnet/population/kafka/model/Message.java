package com.zawnet.population.kafka.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("population")
    private int population;

    public Message(){
    }

    public void setPopulation(int population) {
        this.population = population;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public int getPopulation() {
        return population;
    }
}
