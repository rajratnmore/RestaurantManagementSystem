package com.geekster.RestaurantManagementSystem.repo;

import com.geekster.RestaurantManagementSystem.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersRepo extends JpaRepository<Orders, Integer> {

}
