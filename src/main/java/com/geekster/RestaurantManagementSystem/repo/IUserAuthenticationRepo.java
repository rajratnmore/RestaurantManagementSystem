package com.geekster.RestaurantManagementSystem.repo;

import com.geekster.RestaurantManagementSystem.model.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAuthenticationRepo extends JpaRepository<UserAuthentication, Integer> {

    UserAuthentication findFirstByTokenValue(String tokenValue);
}
