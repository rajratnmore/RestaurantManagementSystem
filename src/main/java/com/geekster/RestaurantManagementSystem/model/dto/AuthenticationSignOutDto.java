package com.geekster.RestaurantManagementSystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationSignOutDto {

    private String email;
    private String tokenValue;

}
