package com.geekster.RestaurantManagementSystem.service;

import com.geekster.RestaurantManagementSystem.model.Admin;
import com.geekster.RestaurantManagementSystem.model.AdminAuthentication;
import com.geekster.RestaurantManagementSystem.model.FoodItem;
import com.geekster.RestaurantManagementSystem.model.dto.AuthenticationSignOutDto;
import com.geekster.RestaurantManagementSystem.model.dto.SignInDto;
import com.geekster.RestaurantManagementSystem.repo.IAdminAuthenticationRepo;
import com.geekster.RestaurantManagementSystem.repo.IAdminRepo;
import com.geekster.RestaurantManagementSystem.repo.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {

    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    IFoodItemRepo foodItemRepo;

    @Autowired
    AdminAuthenticationService adminAuthenticationService;

    @Autowired
    IAdminAuthenticationRepo adminAuthenticationRepo;


    public String addFoodItem(FoodItem foodItem) {
        foodItem.setOrders(null);
        foodItemRepo.save(foodItem);
        return "new food added";
    }


    public String adminSignIn(SignInDto signInDto) {
        String email = signInDto.getUserEmail();
        Admin existedAdmin = adminRepo.findFirstByAdminEmail(email);
        if(existedAdmin == null){
            return "Invalid credential";
        }

        try {
            String encryptedPassword = PasswordEncryptor.getEncryptedPassword(signInDto.getUserPassword());
            if(existedAdmin.getAdminPassword().equals(encryptedPassword)){
                AdminAuthentication token = new AdminAuthentication(existedAdmin);
                adminAuthenticationService.tokenCreate(token);
                return token.getTokenValue();
            }else{
                return "Invalid credential";
            }
        }catch (NoSuchAlgorithmException e){
            return "Something went wrong while password encrypting";
        }

    }

    public String adminSignOut(AuthenticationSignOutDto authenticationSignOutDto) {
        String tokenValue = authenticationSignOutDto.getTokenValue();
        if(adminAuthenticationService.isAuthenticator(authenticationSignOutDto)){
            adminAuthenticationService.deleteToken(tokenValue);
            return "admin signed out successfully";
        }else{
            return "Invalid credentials";
        }
    }

    public boolean isAdminSingIn() {
        return adminAuthenticationRepo.count() > 0;
    }
}













