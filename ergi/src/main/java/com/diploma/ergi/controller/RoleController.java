package com.diploma.ergi.controller;

import com.diploma.ergi.entity.Roles;
import com.diploma.ergi.entity.User;
import com.diploma.ergi.entity.UserRoleRelate;
import com.diploma.ergi.repository.RoleRepository;
import com.diploma.ergi.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.diploma.ergi.service.UserService;

import javax.management.relation.Role;
import java.util.List;


@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})

public class RoleController {


    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @GetMapping("/all")
    public List<Roles> getAll(){
        return roleRepository.findAll();
    }


}
