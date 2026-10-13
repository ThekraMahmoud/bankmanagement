package DTO;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Entity
public class EmployeeDTO {


        @NotBlank(message = "please enter userName")
        @Size(min = 4,max = 10,message = "user name length most be between 4 and 10 characters")
        private String userName;


        @NotBlank(message = "please enter yourName")
        @Size(min = 2,max = 20,message = "name length most be between 2 and 20 characters")
        private String name;

        @NotBlank(message = "please enter Password")
        @Size(min = 6,message = "Password most be it lest 6 characters ")
        private String Password;

        @NotBlank(message = "please enter your Email")
        @Email
        private String email;


        @NotBlank(message = "please Select role")
        @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$")
        private String role;


        //


        @NotBlank(message = "please enter your position")
        private String position;

        @Column(nullable = false)
        private BigDecimal salary;

        @OneToOne
        @MapsId
        @JsonIgnore
        private User user;
    }


