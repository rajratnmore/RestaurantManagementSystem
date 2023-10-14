package com.geekster.RestaurantManagementSystem.service.config;

import com.geekster.RestaurantManagementSystem.model.Admin;
import com.geekster.RestaurantManagementSystem.repo.IAdminRepo;
import com.geekster.RestaurantManagementSystem.service.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;

@Configuration
public class AdminConfiguration {

    @Autowired
    IAdminRepo iAdminRepo;

    @Bean
    public String newAdminCreation(){

        if(iAdminRepo.count() > 0){
            return "admin has created !";
        }

        Admin newAdmin = new Admin(101, "Rajratn","rajmorecsn@admin.com", "Raj@1996");
        try {
            String encryptedPassword = PasswordEncryptor.getEncryptedPassword(newAdmin.getAdminPassword());
            newAdmin.setAdminPassword(encryptedPassword);
            iAdminRepo.save(newAdmin);
            return "admin created successfully";
        } catch (NoSuchAlgorithmException e) {
            return "something went wrong while admin creation";
        }

    }
}
