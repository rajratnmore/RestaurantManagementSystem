package com.geekster.RestaurantManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = UserAuthentication.class, property = "tokenId")
public class UserAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    User user;

    public UserAuthentication(User newUser){
        this.setUser(newUser);
        this.setTokenCreationTime(LocalDateTime.now());
        this.setTokenValue(UUID.randomUUID().toString());
    }

}
