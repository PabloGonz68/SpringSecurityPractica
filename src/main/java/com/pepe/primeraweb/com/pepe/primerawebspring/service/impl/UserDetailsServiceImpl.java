package com.pepe.primeraweb.com.pepe.primerawebspring.service.impl;

import com.pepe.primeraweb.com.pepe.primerawebspring.entities.User;
import com.pepe.primeraweb.com.pepe.primerawebspring.repository.UserRepository;
import com.pepe.primeraweb.com.pepe.primerawebspring.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user== null){
            throw new UsernameNotFoundException("Usuario no encontrado");

        }
        return new MyUserDetails(user);
    }
}
