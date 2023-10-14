package com.geekster.RestaurantManagementSystem.service;

import com.geekster.RestaurantManagementSystem.model.Admin;
import com.geekster.RestaurantManagementSystem.model.AdminAuthentication;
import com.geekster.RestaurantManagementSystem.model.dto.AuthenticationSignOutDto;
import com.geekster.RestaurantManagementSystem.repo.IAdminAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationService {

    @Autowired
    IAdminAuthenticationRepo adminAuthenticationRepo;

    public void tokenCreate(AdminAuthentication token) {
        adminAuthenticationRepo.save(token);
    }

    public boolean isAuthenticator(AuthenticationSignOutDto authenticationSignOutDto){
        String email = authenticationSignOutDto.getEmail();
        String tokenValue = authenticationSignOutDto.getTokenValue();

        AdminAuthentication authenticatedAdmin = adminAuthenticationRepo.findFirstByTokenValue(tokenValue);

        if(authenticatedAdmin != null){
            return authenticatedAdmin.getAdmin().getAdminEmail().equals(email);
        }else{
            return false;
        }

    }

    public void deleteToken(String tokenValue) {
        AdminAuthentication adminAuthentication = adminAuthenticationRepo.findFirstByTokenValue(tokenValue);
        adminAuthenticationRepo.delete(adminAuthentication);
    }



}
