package com.geekster.RestaurantManagementSystem.service;

import com.geekster.RestaurantManagementSystem.model.Orders;
import com.geekster.RestaurantManagementSystem.repo.IOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    IOrdersRepo orderRepo;


    public String placeOrder(Orders order) {

        orderRepo.save(order);
        return "Order placed";
    }
}
