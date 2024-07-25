package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City,Integer> {


    City findByName(String name);
}
