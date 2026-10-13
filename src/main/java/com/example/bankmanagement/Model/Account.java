package com.example.bankmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "please Enter account Number")
    @Pattern(regexp = "^\\d{4}(-\\d{4}){3}$", message = "Account number must follow XXXX-XXXX-XXXX-XXXX format")
    @Column(unique = true,nullable = false)
    private String accountNumber;


    @PositiveOrZero(message = "jest Allow positive number")
    @Column(nullable = false)
    private Integer balance;


    @AssertFalse
    @Column(nullable = false)
    private Boolean isActive;


    @AssertFalse
    @Column(nullable = false)
    private Boolean isBlock;


    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
