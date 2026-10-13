package com.example.bankmanagement.Service;

import DTO.CustomerDTO;
import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Repository.CustomerRepository;
import com.example.bankmanagement.Repository.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthUserRepository userRepository;


    public List<Customer>getAllCustomer(){
        return customerRepository.findAll();
    }



    public void add(CustomerDTO customerDTO) {

        User user=new User();
        user.setUserName(customerDTO.getUserName());
        String hash=new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hash);
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        user.setRole("CUSTOMER");

        Customer customer=new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customer.setUser(user);
        user.setCustomer(customer);
        userRepository.save(user);
    }


    public void delete(Integer customerID){
        User user=userRepository.findById(customerID).orElse(null);
        if(user==null){
            throw new ApiException("Customer User not found");
        }
        userRepository.delete(user);
    }


    public void  update(Integer customerID, CustomerDTO dto){
        User user=userRepository.findById(customerID).orElse(null);
        if(user==null){
            throw new ApiException("Customer User not found");
        }
        Customer customer=customerRepository.findCustomerById(customerID);
        if(customer==null){
            throw new ApiException("Customer not found");
        }
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUserName(dto.getUserName());

        customer.setPhoneNumber(dto.getPhoneNumber());

        userRepository.save(user);
        customerRepository.save(customer);

    }


}
