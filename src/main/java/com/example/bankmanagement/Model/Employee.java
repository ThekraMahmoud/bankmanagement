package com.example.bankmanagement.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Employee {

    @Id
    private Integer id;


    @NotBlank(message = "please enter your position")
    @Column(nullable = false)
    private String position;


    @NotBlank(message = "please enter your position")
    @PositiveOrZero(message = "jest Allow positive number")
    @Column(nullable = false)
    private BigDecimal salary;


    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

}
