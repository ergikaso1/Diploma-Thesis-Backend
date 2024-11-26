package com.diploma.ergi.service;

import com.diploma.ergi.entity.Foods;
import com.diploma.ergi.repository.FoodRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private FoodService foodService;

    private Foods food;
    private List<Foods> foodsList;

    @Before
    public void setUp() {
        food = new Foods();
        food.setId(1);
        food.setName("Chicken Breast");
        food.setDescription("Lean source of protein");
        food.setProteinCount(31);
        food.setFatCount(3);
        food.setFiberCount(0);
        food.setCaloriesPer100G(165);
        food.setImage("chicken_breast.jpg");

        foodsList = new ArrayList<>();
        foodsList.add(food);
    }

    @Test
    public void testAddFood() {
        // Mock the save method to return the same food object when called
        when(foodRepository.save(any(Foods.class))).thenReturn(food);

        foodService.addFood(food);

        // Verify that save was called once
        verify(foodRepository, times(1)).save(any(Foods.class));
    }


    @Test
    public void testGetAllFoods() {
        when(foodRepository.findAll()).thenReturn(foodsList);

        List<Foods> fetchedFoods = foodService.getAllFoods();

        assertNotNull(fetchedFoods);
        assertEquals(foodsList.size(), fetchedFoods.size());
        assertEquals(foodsList.get(0).getName(), fetchedFoods.get(0).getName());
        verify(foodRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteFood() {
        doNothing().when(foodRepository).deleteById(1);

        foodService.delete(1);

        verify(foodRepository, times(1)).deleteById(1);
    }
}

