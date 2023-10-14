package com.geekster.RestaurantManagementSystem.service;

import com.geekster.RestaurantManagementSystem.model.FoodItem;
import com.geekster.RestaurantManagementSystem.model.Orders;
import com.geekster.RestaurantManagementSystem.model.User;
import com.geekster.RestaurantManagementSystem.model.UserAuthentication;
import com.geekster.RestaurantManagementSystem.model.dto.AuthenticationSignOutDto;
import com.geekster.RestaurantManagementSystem.model.dto.SignInDto;
import com.geekster.RestaurantManagementSystem.repo.IFoodItemRepo;
import com.geekster.RestaurantManagementSystem.repo.IOrdersRepo;
import com.geekster.RestaurantManagementSystem.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    FoodItemService foodItemService;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    IFoodItemRepo foodItemRepo;

    @Autowired
    IOrdersRepo ordersRepo;

    public String userSignUp(User newUser) {
        String email = newUser.getUserEmail();
        User user = userRepo.findFirstByUserEmail(email);
        if(user != null){
            return "User has already existed";
        }

        try {
            String encryptedPassword = PasswordEncryptor.getEncryptedPassword(newUser.getUserPassword());
            newUser.setUserPassword(encryptedPassword);
            newUser.setOrders(null);
            userRepo.save(newUser);
            return "new user created";
        } catch (NoSuchAlgorithmException e) {
            return "Something wrong while password encryption";
        }


    }

    public String userSignIn(SignInDto signInDto) {
        String email = signInDto.getUserEmail();
        User existedUser = userRepo.findFirstByUserEmail(email);

        if(existedUser == null) {
            return "Invalid credential !";
        }

        try {
            String encryptedPassword = PasswordEncryptor.getEncryptedPassword(signInDto.getUserPassword());
            if(existedUser.getUserPassword().equals(encryptedPassword)){
                UserAuthentication token = new UserAuthentication(existedUser);
                userAuthenticationService.tokenCreate(token);
                return token.getTokenValue();
            }else{
                return "Invalid credential !";
            }

        } catch (NoSuchAlgorithmException e) {
            return "Something wrong happened while password encrypted !";
        }


    }

    public String userSignOut(AuthenticationSignOutDto userSignOut) {

        if(userAuthenticationService.isAuthenticated(userSignOut)){
            userAuthenticationService.deleteToken(userSignOut.getTokenValue());
            return "Successfully signed out !";
        }else{
            return "Unauthenticated access !";
        }



    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    public String placeOrder(AuthenticationSignOutDto userAuthentication, Orders order) {
        if(userAuthenticationService.isAuthenticated(userAuthentication)){
            String email = userAuthentication.getEmail();
            User existedUser = userRepo.findFirstByUserEmail(email);
            order.setUser(existedUser);

            // find order
            Integer foodId = order.getFoodItem().getFoodId();
            boolean isValidFoodId = foodItemRepo.existsById(foodId);

            if(isValidFoodId){
                order.setOrderDateTime(LocalDateTime.now());
                ordersRepo.save(order);
                return "your order has been placed";
            }else {
                return "Invalid FoodId !";
            }

        }else{
            return "Invalid user";
        }

    }
}
