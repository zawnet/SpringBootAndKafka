package com.zawnet.population.localdata.repository;

import com.zawnet.population.localdata.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByCity (String city) throws NoSuchElementException;
}
