        package com.example.bankmanagement.Controller;

import DTO.EmployeeDTO;
import com.example.bankmanagement.Api.ApiResponse;
import com.example.bankmanagement.Model.Employee;
import com.example.bankmanagement.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.status(200).body(employeeService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody EmployeeDTO dto) {
        employeeService.add(dto);
        return ResponseEntity.status(200).body(new ApiResponse("Employee added successfully"));
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<?> update(@PathVariable Integer employeeId, @Valid @RequestBody EmployeeDTO dto) {
        employeeService.update(employeeId, dto);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> delete(@PathVariable Integer employeeId) {
        employeeService.delete(employeeId);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted successfully"));
    }
}
