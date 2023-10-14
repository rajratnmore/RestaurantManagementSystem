package com.geekster.RestaurantManagementSystem.controller;

import com.geekster.RestaurantManagementSystem.model.FoodItem;
import com.geekster.RestaurantManagementSystem.model.dto.AuthenticationSignOutDto;
import com.geekster.RestaurantManagementSystem.model.dto.SignInDto;
import com.geekster.RestaurantManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("admin/SignIn")
    public String adminSignIn(@RequestBody SignInDto signInDto){
        return adminService.adminSignIn(signInDto);
    }

    @PostMapping("admin/SignOut")
    public String adminSignOut(@RequestBody AuthenticationSignOutDto authenticationSignOutDto){
        return adminService.adminSignOut(authenticationSignOutDto);
    }

    @PostMapping("foodItem")
    public String addFoodItem(@RequestBody FoodItem foodItem){

        if(!adminService.isAdminSingIn()){
            return "Admin should sign in first";
        }

        return adminService.addFoodItem(foodItem);

    }




}
