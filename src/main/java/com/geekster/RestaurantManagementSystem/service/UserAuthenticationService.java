package com.geekster.RestaurantManagementSystem.service;

import com.geekster.RestaurantManagementSystem.model.UserAuthentication;
import com.geekster.RestaurantManagementSystem.model.dto.AuthenticationSignOutDto;
import com.geekster.RestaurantManagementSystem.repo.IUserAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    @Autowired
    IUserAuthenticationRepo userAuthentication;

    public void tokenCreate(UserAuthentication token) {
        userAuthentication.save(token);
    }


    public boolean isAuthenticated(AuthenticationSignOutDto userSignOut) {
        String email = userSignOut.getEmail();
        String tokenValue = userSignOut.getTokenValue();

        UserAuthentication userAuthenticationToken = userAuthentication.findFirstByTokenValue(tokenValue);
        if (userAuthenticationToken != null){
            return userAuthenticationToken.getUser().getUserEmail().equals(email);
        }else {
            return false;
        }

    }

    public void deleteToken(String tokenValue) {
        UserAuthentication token = userAuthentication.findFirstByTokenValue(tokenValue);
        userAuthentication.delete(token);
    }

}
