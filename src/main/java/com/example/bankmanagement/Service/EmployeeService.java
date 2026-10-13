package com.example.bankmanagement.Service;

import DTO.EmployeeDTO;
import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.Model.Employee;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthUserRepository;
import com.example.bankmanagement.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private AuthUserRepository userRepository;


    public List<Employee>getAll(){
        return employeeRepository.findAll();
    }



    public void add(EmployeeDTO dto){

        User user=new User();
        String hash=new BCryptPasswordEncoder().encode(dto.getPassword());
        user.setPassword(hash);
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getName());
        user.setName(dto.getName());
        user.setRole("EMPLOYEE");

        Employee employee=new Employee();
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        user.setEmployee(employee);
        employee.setUser(user);
        userRepository.save(user);
    }

    public void update(Integer employeeId,EmployeeDTO dto){
        User user=userRepository.findById(employeeId).orElse(null);
        if(user==null){
            throw new ApiException("user not found");
        }

        Employee employee=employeeRepository.findEmployeeById(employeeId);

        if(employee==null){
            throw new ApiException("employee not found");
        }
        user.setUserName(dto.getUserName());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        employee.setPosition(dto.getPosition());
        userRepository.save(user);
        employeeRepository.save(employee);
    }


    public void delete(Integer id){
        User user=userRepository.findById(id).orElse(null);
        if(user==null){
            throw new ApiException("user not found");
        }
        Employee employee=employeeRepository.findEmployeeById(id);
        if(employee==null){
            throw new ApiException("employee not found");
        }
        userRepository.delete(user);
    }
}
