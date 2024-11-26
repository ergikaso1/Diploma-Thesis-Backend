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

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@CrossOrigin
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    public User saveUser(User userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getAge(),
                userDTO.getBirthday(),
                userDTO.getPhone(),
                userDTO.getEmail(),
                userDTO.getGender(),
                userDTO.getFirebaseId(),
                new Date()
        );
        User createdUser = userRepository.save(user);

        UserRoleRelate userRole = new UserRoleRelate();
        userRole.setUser(createdUser);
        userRole.setRole(roleRepository.findByName("user"));
        userRoleRepository.save(userRole);

        return createdUser;
    };

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User findByFirebaseId(String firebaseId) {
        return userRepository.findByFirebaseId(firebaseId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public List<User> getAllAsc(){
        return userRepository.findAllByOrderByIdAsc();
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}
