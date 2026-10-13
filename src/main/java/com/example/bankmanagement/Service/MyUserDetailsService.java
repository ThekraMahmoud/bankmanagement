package com.example.bankmanagement.Service;


import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findUserByUserName(username);
        if(user==null){
            throw new ApiException("wrong user name or password");
        }
        return user;
    }


}
