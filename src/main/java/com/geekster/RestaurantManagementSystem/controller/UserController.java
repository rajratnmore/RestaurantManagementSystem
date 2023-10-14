package com.geekster.RestaurantManagementSystem.controller;

import com.geekster.RestaurantManagementSystem.model.FoodItem;
import com.geekster.RestaurantManagementSystem.model.Orders;
import com.geekster.RestaurantManagementSystem.model.User;
import com.geekster.RestaurantManagementSystem.model.dto.AuthenticOrder;
import com.geekster.RestaurantManagementSystem.model.dto.AuthenticationSignOutDto;
import com.geekster.RestaurantManagementSystem.model.dto.SignInDto;
import com.geekster.RestaurantManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // User Sign Up
    @PostMapping("user/signUp")
    public String userSignUp(@RequestBody User newUser){
        return userService.userSignUp(newUser);
    }


    // User Sign In
    @PostMapping("user/signIn")
    public String userSignIn(@RequestBody SignInDto signInDto){
        return userService.userSignIn(signInDto);
    }


    // User Sign Out
    @PostMapping("user/signOut")
    public String userSignOut(@RequestBody AuthenticationSignOutDto userSignOut){
        return userService.userSignOut(userSignOut);
    }

    // Get all Foods
    @GetMapping("foods")
    public List<FoodItem> getAllFoodItems(){
        return userService.getAllFoodItems();
    }

    @PostMapping("placeOrder")
    public String placeOrder(@RequestBody AuthenticOrder authenticOrder){
        return userService.placeOrder(authenticOrder.getAuthenticationSignOutDto(), authenticOrder.getOrders());
    }


}
