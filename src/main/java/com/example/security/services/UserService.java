package com.example.security.services;

import com.example.security.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User findByUsername(String username);

    void save(User user);

    List<User> list();

    User findUserById(int id);

    void deleteById(int id);
}
