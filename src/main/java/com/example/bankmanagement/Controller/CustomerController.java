
        package com.example.bankmanagement.Controller;

import DTO.CustomerDTO;
import com.example.bankmanagement.Api.ApiResponse;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.add(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer added successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return ResponseEntity.status(200).body(customerService.getAllCustomer());
    }

    @PutMapping("/update/{customerID}")
    public ResponseEntity<?> update(@PathVariable Integer customerID,@RequestBody CustomerDTO dto) {
        customerService.update(customerID, dto);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }

    @DeleteMapping("/delete/{customerID}")
    public ResponseEntity<?> delete(@PathVariable Integer customerID) {
        customerService.delete(customerID);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successfully"));
    }
}