package com.geekster.RestaurantManagementSystem.repo;

import com.geekster.RestaurantManagementSystem.model.Admin;
import com.geekster.RestaurantManagementSystem.model.AdminAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminAuthenticationRepo extends JpaRepository<AdminAuthentication, Integer> {




    AdminAuthentication findFirstByTokenValue(String tokenValue);
}
