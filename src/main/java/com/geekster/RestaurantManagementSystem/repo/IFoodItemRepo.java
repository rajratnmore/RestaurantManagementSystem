package com.geekster.RestaurantManagementSystem.repo;

import com.geekster.RestaurantManagementSystem.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodItemRepo extends JpaRepository<FoodItem, Integer> {

}
