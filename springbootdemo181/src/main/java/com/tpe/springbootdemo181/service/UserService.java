package com.tpe.springbootdemo181.service;

import com.tpe.springbootdemo181.domain.Role;
import com.tpe.springbootdemo181.domain.User;
import com.tpe.springbootdemo181.domain.enums.UserRole;
import com.tpe.springbootdemo181.dto.UserRequest;
import com.tpe.springbootdemo181.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public void saveUser(UserRequest userDTO) {

        User myUser = new User();
        myUser.setFirstName(userDTO.getFirstName());
        myUser.setLastName(userDTO.getLastName());
        myUser.setUserName(userDTO.getUserName());


        String password = userDTO.getPassword();
        String encodePassword = passwordEncoder.encode(password); // for example : 1234 -> MTIzNA==

        myUser.setPassword(encodePassword);
            // adding role to my user
        Role role = roleService.getRoleByType(UserRole.ROLE_ADMIN);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        myUser.setRoles(roles);
        userRepository.save(myUser);

    }
}


