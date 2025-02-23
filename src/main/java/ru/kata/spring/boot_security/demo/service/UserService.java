package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;



public interface UserService extends UserDetailsService {
    List<User> getAll();
    void save(User user);
    User getUserById(long id);
    void delete(long id);
    User findByUsername(String username);
    List<Role> getAllRoles();
}
