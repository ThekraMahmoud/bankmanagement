package com.example.bankmanagement.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    private Integer id;


    @Column(nullable = false,unique = true)
    private String phoneNumber;


    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private Set<Account>accounts;


}
