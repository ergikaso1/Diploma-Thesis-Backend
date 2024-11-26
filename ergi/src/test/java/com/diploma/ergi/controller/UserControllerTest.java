package com.diploma.ergi.controller;

import com.diploma.ergi.entity.User;
import com.diploma.ergi.entity.UserRoleRelate;
import com.diploma.ergi.service.UserRoleService;
import com.diploma.ergi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRoleService userRoleService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setFirebaseId("firebase-id");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        // Verify that userService.saveUser was called with the correct argument
        // Add assertions as needed
    }

    @Test
    public void testAddRole() {
        UserRoleRelate userRole = new UserRoleRelate();
        when(userRoleService.saveUserRole(any(UserRoleRelate.class))).thenReturn(userRole);

        UserRoleRelate result = userController.addRole("uid", null, userRole);
        assertEquals(userRole, result);
    }

    @Test
    public void testRemoveRole() {
        doNothing().when(userRoleService).removeUserRole(anyInt());

        userController.removeRole(1, null);
        // Verify that userRoleService.removeUserRole was called with the correct argument
        // Add assertions as needed
    }

    @Test
    public void testGetUserByUid() {
        User user = new User();
        user.setFirebaseId("firebase-id");
        when(userService.findByFirebaseId(anyString())).thenReturn(user);

        ResponseEntity<User> response = userController.getUserByUid("firebase-id", null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetAll() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        List<User> result = userController.getAll();
        assertEquals(users, result);
    }

    @Test
    public void testGetAllAsc() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllAsc()).thenReturn(users);

        List<User> result = userController.getAllAsc();
        assertEquals(users, result);
    }
}
