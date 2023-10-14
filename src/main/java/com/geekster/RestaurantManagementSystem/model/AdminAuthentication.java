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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = AdminAuthentication.class, property = "tokenId")
public class AdminAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDateTime;

    @OneToOne
    @JoinColumn(name = "fk_admin_id")
    Admin admin;


    public AdminAuthentication(Admin newAdmin) {
        this.setAdmin(newAdmin);
        this.setTokenCreationDateTime(LocalDateTime.now());
        this.setTokenValue(UUID.randomUUID().toString());
    }
}
