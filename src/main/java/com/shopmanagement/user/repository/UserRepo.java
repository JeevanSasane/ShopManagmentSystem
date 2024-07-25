package com.shopmanagement.user.repository;

import com.shopmanagement.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByLoginName(String loginName);
}
