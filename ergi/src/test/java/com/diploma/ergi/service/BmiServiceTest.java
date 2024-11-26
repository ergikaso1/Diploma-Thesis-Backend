package com.diploma.ergi.service;

import com.diploma.ergi.entity.BMI;
import com.diploma.ergi.entity.User;
import com.diploma.ergi.repository.BmiRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BmiServiceTest {

    @Mock
    private BmiRepository bmiRepository;

    @InjectMocks
    private BmiService bmiService;

    private BMI bmi;
    private User user;
    private List<BMI> bmiList;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setName("John");

        bmi = new BMI();
        bmi.setId(1);
        bmi.setAgeBmi(25);
        bmi.setHeight(175);
        bmi.setWeight(70);
        bmi.setWeightGoal(65);
        bmi.setBmi(22);
        bmi.setBodyType("Athletic");
        bmi.setUser(user);

        bmiList = new ArrayList<>();
        bmiList.add(bmi);
    }

    @Test
    public void testGetBmiById() {
        when(bmiRepository.findById(1)).thenReturn(Optional.of(bmi));

        Optional<BMI> fetchedBmi = bmiService.getBmiById(1);

        assertTrue(fetchedBmi.isPresent());
        assertEquals(bmi.getId(), fetchedBmi.get().getId());
        assertEquals(bmi.getBodyType(), fetchedBmi.get().getBodyType());
        verify(bmiRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveBmi() {
        when(bmiRepository.save(bmi)).thenReturn(bmi);

        BMI savedBmi = bmiService.saveBmi(bmi);

        assertNotNull(savedBmi);
        assertEquals(bmi.getId(), savedBmi.getId());
        assertEquals(bmi.getBodyType(), savedBmi.getBodyType());
        verify(bmiRepository, times(1)).save(bmi);
    }

    @Test
    public void testFindLatest() {
        when(bmiRepository.findByUserOrderByIdDesc(user)).thenReturn(bmiList);

        List<BMI> fetchedBmiList = bmiService.findLatest(user);

        assertNotNull(fetchedBmiList);
        assertEquals(bmiList.size(), fetchedBmiList.size());
        assertEquals(bmiList.get(0).getId(), fetchedBmiList.get(0).getId());
        verify(bmiRepository, times(1)).findByUserOrderByIdDesc(user);
    }

    @Test
    public void testFindAll() {
        when(bmiRepository.findBMIByUser(user)).thenReturn(bmiList);

        List<BMI> fetchedBmiList = bmiService.findAll(user);

        assertNotNull(fetchedBmiList);
        assertEquals(bmiList.size(), fetchedBmiList.size());
        assertEquals(bmiList.get(0).getId(), fetchedBmiList.get(0).getId());
        verify(bmiRepository, times(1)).findBMIByUser(user);
    }
}

