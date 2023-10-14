package com.geekster.RestaurantManagementSystem.service;

import com.geekster.RestaurantManagementSystem.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {

    @Autowired
    FoodItemService foodItemService;


    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }
}
