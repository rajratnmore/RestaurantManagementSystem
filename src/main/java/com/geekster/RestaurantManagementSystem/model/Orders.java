package com.geekster.RestaurantManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Orders.class, property = "orderId")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Enumerated(value = EnumType.STRING)
    private Status orderStatus;
    private Integer orderQuantity;
    private LocalDateTime orderDateTime;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "fk_food_id")
    FoodItem foodItem;

}
