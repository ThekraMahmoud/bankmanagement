package com.example.bankmanagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
public class Configuration {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth->auth.requestMatchers("/api/v1/customer/add").permitAll()
                        .requestMatchers("/api/v1/account/all",
                                "/api/v1/account/block/{accountId}",
                                "/api/v1/customer/all",
                                "/api/v1/employee/all"
                        ).hasAuthority("ROLE_ADMIN")

                        .requestMatchers(
                                "/api/v1/customer/add",
                                "/api/v1/customer/update/{customerID}",
                                "/api/v1/customer/delete/{customerID}",
                                "/api/v1/account/my-accounts",
                                "/api/v1/account/add",
                                "/api/v1/account/update/{accountId}",
                                "/api/v1/account/delete/{accountId}",
                                "/api/v1/account/details/{accountId}",
                                "/api/v1/account/deposit/{accountId}/{amount}",
                                "/api/v1/account/withdraw/{accountId}/{amount}",
                                "/api/v1/account/transfer/{accountOneId}/{accountTwoId}/{amount}").hasRole("CUSTOMER")
                        .requestMatchers(
                                "/api/v1/employee/add",
                                "/api/v1/employee/update/{employeeId}",
                                "/api/v1/customer/delete/{customerID}").hasAnyRole("EMPLOYEE")
                        .anyRequest().authenticated())
                .logout(logout->logout.logoutUrl("/api/v1/auth/logout").invalidateHttpSession(true)
                )
                .httpBasic(basic->{})
                .build();

    }
}
