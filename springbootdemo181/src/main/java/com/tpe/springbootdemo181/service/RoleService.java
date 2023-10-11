package com.tpe.springbootdemo181.service;

import com.tpe.springbootdemo181.domain.Role;
import com.tpe.springbootdemo181.domain.enums.UserRole;
import com.tpe.springbootdemo181.exception.ResourceNotFoundException;
import com.tpe.springbootdemo181.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByType(UserRole roleName){
        return roleRepository.findByName(roleName).orElseThrow(()-> new ResourceNotFoundException("Couldn't find the role specified." + roleName));
    }

}
