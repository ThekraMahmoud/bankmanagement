package com.example.bankmanagement.Repository;

import com.example.bankmanagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<User, Integer> {
  User findUserByUserName(String userName);
}
