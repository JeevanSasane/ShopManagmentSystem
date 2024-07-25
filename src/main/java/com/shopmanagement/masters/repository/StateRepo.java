package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository<State,Integer> {


    State findByName(String name);
}
