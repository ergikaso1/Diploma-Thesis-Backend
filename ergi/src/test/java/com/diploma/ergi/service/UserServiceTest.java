package com.diploma.ergi.service;
import com.diploma.ergi.entity.Roles;
import com.diploma.ergi.entity.User;
import com.diploma.ergi.entity.UserRoleRelate;
import com.diploma.ergi.repository.RoleRepository;
import com.diploma.ergi.repository.UserRepository;
import com.diploma.ergi.repository.UserRoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    private List<User> users;
    private User user;

    @Before
    public void setUp() {
        users = new ArrayList<>();
        user = new User();
        user.setId(1);
        user.setName("John");
        user.setSurname("Doe");
        user.setAge(25);
        user.setBirthday(new Date());
        user.setPhone("1234567890");
        user.setEmail("john@example.com");
        user.setGender("Male");
        user.setFirebaseId("firebase123");
        users.add(user);
    }

    @Test
    public void testSaveUser() {
        Roles userRole = new Roles();
        userRole.setName("user");

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleRepository.findByName("user")).thenReturn(userRole);
        when(userRoleRepository.save(any(UserRoleRelate.class))).thenReturn(null);

        User createdUser = userService.saveUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
        verify(userRepository, times(1)).save(any(User.class));
        verify(userRoleRepository, times(1)).save(any(UserRoleRelate.class));
    }

    @Test
    public void testFindByFirebaseId() {
        when(userRepository.findByFirebaseId("firebase123")).thenReturn(user);
        User foundUser = userService.findByFirebaseId("firebase123");

        assertNotNull(foundUser);
        assertEquals("firebase123", foundUser.getFirebaseId());
        verify(userRepository, times(1)).findByFirebaseId("firebase123");
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(users);
        List<User> fetchedUsers = userService.getAllUsers();

        assertNotNull(fetchedUsers);
        assertEquals(users.size(), fetchedUsers.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllAsc() {
        when(userRepository.findAllByOrderByIdAsc()).thenReturn(users);
        List<User> fetchedUsers = userService.getAllAsc();

        assertNotNull(fetchedUsers);
        assertEquals(users.size(), fetchedUsers.size());
        verify(userRepository, times(1)).findAllByOrderByIdAsc();
    }
}
