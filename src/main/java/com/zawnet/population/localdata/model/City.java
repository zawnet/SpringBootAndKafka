package com.zawnet.population.localdata.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {
    @Id
    private long id;
    private String city;
    private String country;

    public long getId() {
        return id;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
