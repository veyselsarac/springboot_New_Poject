package com.tpe.springbootdemo181.security.service;

import com.tpe.springbootdemo181.domain.Role;
import com.tpe.springbootdemo181.domain.User;
import com.tpe.springbootdemo181.exception.ResourceNotFoundException;
import com.tpe.springbootdemo181.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username).orElseThrow(()->new ResourceNotFoundException("Could not found the User With UserName" + username));

        if (user != null){
            //this is not our user class. Security -> user.
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    buildGrantedAuthorities(user.getRoles())
            );
        }else {
            throw new UsernameNotFoundException("Could not found the User WIth UserName" + username);
        }

    }


    private static List<SimpleGrantedAuthority> buildGrantedAuthorities(final Set<Role> roles){

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));

        }

        return authorities;

    }
}