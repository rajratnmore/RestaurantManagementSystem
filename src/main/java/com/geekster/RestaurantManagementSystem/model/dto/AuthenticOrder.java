package com.geekster.RestaurantManagementSystem.model.dto;

import com.geekster.RestaurantManagementSystem.model.Orders;
import com.geekster.RestaurantManagementSystem.model.UserAuthentication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticOrder {
    AuthenticationSignOutDto authenticationSignOutDto;
    Orders orders;
}
