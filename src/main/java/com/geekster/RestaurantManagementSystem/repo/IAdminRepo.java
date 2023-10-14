package com.geekster.RestaurantManagementSystem.repo;

import com.geekster.RestaurantManagementSystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin, Integer> {
    Admin findFirstByAdminEmail(String email);
}
