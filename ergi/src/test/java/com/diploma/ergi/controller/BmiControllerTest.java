package com.diploma.ergi.controller;


import com.diploma.ergi.entity.BMI;
import com.diploma.ergi.entity.User;
import com.diploma.ergi.service.BmiService;
import com.diploma.ergi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BmiControllerTest {

    @Mock
    private BmiService bmiService;

    @Mock
    private UserService userService;


    @InjectMocks
    private BmiController bmiController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddBmi() {
        BMI bmi = new BMI();
        // Mock the saveBmi method to do nothing
        when(bmiService.saveBmi(any(BMI.class))).thenReturn(null);

        ResponseEntity<String> response = bmiController.addBmi(bmi);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("BMI data saved successfully", response.getBody());
    }

    @Test
    public void testLatestBmi() {

        UserService mockUserService = Mockito.mock(UserService.class);

        User user = new User();
        BMI bmi = new BMI();
        List<BMI> bmiList = Arrays.asList(bmi);

        when(mockUserService.findByFirebaseId(anyString())).thenReturn(user);
        when(bmiService.findLatest(any(User.class))).thenReturn(bmiList);

        ResponseEntity<?> response = bmiController.latestBmi("firebase-id");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bmi, response.getBody());
    }



    @Test
    public void testLatestBmiNotFound() {
        User user = new User();

        when(userService.findByFirebaseId(anyString())).thenReturn(user);
        when(bmiService.findLatest(any(User.class))).thenReturn(Arrays.asList());

        ResponseEntity<?> response = bmiController.latestBmi("firebase-id");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No BMI records found for the user.", response.getBody());
    }

    @Test
    public void testFindAll() {
        User user = new User();
        BMI bmi1 = new BMI();
        BMI bmi2 = new BMI();
        List<BMI> bmiList = Arrays.asList(bmi1, bmi2);

        when(userService.findByFirebaseId(anyString())).thenReturn(user);
        when(bmiService.findAll(any(User.class))).thenReturn(bmiList);

        ResponseEntity<?> response = bmiController.findAll("firebase-id");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bmiList, response.getBody());
    }

    @Test
    public void testGetBmi() {
        User user = new User();
        BMI bmi = new BMI();

        when(userService.findByFirebaseId(anyString())).thenReturn(user);

        ResponseEntity<?> response = bmiController.getBmi("firebase-id");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bmi, response.getBody());
    }
}
