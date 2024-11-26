package com.diploma.ergi.service;

import com.diploma.ergi.entity.Diet;
import com.diploma.ergi.entity.DietFoodRelate;
import com.diploma.ergi.entity.Foods;
import com.diploma.ergi.repository.DietFoodRepository;
import com.diploma.ergi.repository.DietRepository;
import com.diploma.ergi.repository.FoodRepository;
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
public class DietServiceTest {

    @Mock
    private DietRepository dietRepository;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private DietFoodRepository dietFoodRepository;

    @InjectMocks
    private DietService dietService;

    private Diet diet;
    private Foods food;
    private DietFoodRelate dietFoodRelate;
    private List<Diet> dietList;

    @Before
    public void setUp() {
        diet = new Diet();
        diet.setId(1);
        diet.setGoal("Weight Loss");
        diet.setBenefits("Reduce weight");
        diet.setNoCalories(500);
        diet.setMinAge(18);
        diet.setMaxAge(45);

        food = new Foods();
        food.setId(1);
        food.setCaloriesPer100G(200);

        dietFoodRelate = new DietFoodRelate();
        dietFoodRelate.setDiet(diet);
        dietFoodRelate.setFood(food);
        dietFoodRelate.setFood_amount(150);

        dietList = new ArrayList<>();
        dietList.add(diet);
    }

    @Test
    public void testAddFoodToDiet() throws Exception {
        when(dietFoodRepository.save(any(DietFoodRelate.class))).thenReturn(dietFoodRelate);
        when(dietRepository.findById(diet.getId())).thenReturn(Optional.of(diet));
        when(foodRepository.findById(food.getId())).thenReturn(Optional.of(food));

        DietFoodRelate result = dietService.addFoodToDiet(dietFoodRelate);

        assertNotNull(result);
        assertEquals(dietFoodRelate.getDiet().getId(), result.getDiet().getId());
        verify(dietFoodRepository, times(1)).save(dietFoodRelate);
        verify(dietRepository, times(1)).findById(diet.getId());
        verify(foodRepository, times(1)).findById(food.getId());
        verify(dietRepository, times(1)).save(diet);
    }

    @Test
    public void testAddDiet() {
        when(dietRepository.save(diet)).thenReturn(diet);

        Diet result = dietService.addDiet(diet);

        assertNotNull(result);
        assertEquals(diet.getId(), result.getId());
        assertEquals(diet.getGoal(), result.getGoal());
        verify(dietRepository, times(1)).save(diet);
    }

    @Test
    public void testGetAllDiets() {
        when(dietRepository.findAll()).thenReturn(dietList);

        List<Diet> fetchedDiets = dietService.getAllDiets();

        assertNotNull(fetchedDiets);
        assertEquals(dietList.size(), fetchedDiets.size());
        assertEquals(dietList.get(0).getId(), fetchedDiets.get(0).getId());
        verify(dietRepository, times(1)).findAll();
    }
}

