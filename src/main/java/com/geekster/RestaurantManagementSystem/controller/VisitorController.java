package com.geekster.RestaurantManagementSystem.controller;

import com.geekster.RestaurantManagementSystem.model.FoodItem;
import com.geekster.RestaurantManagementSystem.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitorController {

    @Autowired
    VisitorService visitorService;

    @GetMapping("foods")
    public List<FoodItem> getAllFoodItems(){
        return visitorService.getAllFoodItems();
    }

}
