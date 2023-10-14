package com.geekster.RestaurantManagementSystem.service;

import com.geekster.RestaurantManagementSystem.model.FoodItem;
import com.geekster.RestaurantManagementSystem.repo.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    @Autowired
    IFoodItemRepo foodItemRepo;

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepo.findAll();
    }

}
