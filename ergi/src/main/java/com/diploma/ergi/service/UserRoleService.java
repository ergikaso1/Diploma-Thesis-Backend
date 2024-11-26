package com.diploma.ergi.service;

import com.diploma.ergi.controller.LoginResponse;
import com.diploma.ergi.entity.User;
import com.diploma.ergi.entity.UserRoleRelate;
import com.diploma.ergi.repository.RoleRepository;
import com.diploma.ergi.repository.UserRepository;
import com.diploma.ergi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;


@Service
@CrossOrigin
public class UserRoleService{
    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository){
        this.userRoleRepository = userRoleRepository;
    }

    public UserRoleRelate saveUserRole(UserRoleRelate userRoleRelate) {
        return userRoleRepository.save(userRoleRelate);
    };


    public void removeUserRole(int userRoleId) {
        userRoleRepository.deleteById(userRoleId);
    };

}
