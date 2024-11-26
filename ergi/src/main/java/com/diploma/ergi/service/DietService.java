package com.diploma.ergi.service;

import com.diploma.ergi.entity.Diet;
import com.diploma.ergi.entity.DietFoodRelate;
import com.diploma.ergi.entity.Foods;
import com.diploma.ergi.repository.DietFoodRepository;
import com.diploma.ergi.repository.DietRepository;
import com.diploma.ergi.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DietService {


    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private FoodRepository foodRepository;


    @Autowired
    private DietFoodRepository dietFoodRepository;

    public DietService(DietRepository dietRepository, FoodRepository foodRepository, DietFoodRepository dietFoodRepository){
        this.dietRepository = dietRepository;
        this.foodRepository = foodRepository;
        this.dietFoodRepository = dietFoodRepository;
    }

    public DietFoodRelate addFoodToDiet(DietFoodRelate dietFoodRelate) throws Exception {
        DietFoodRelate added = dietFoodRepository.save(dietFoodRelate);

        Diet cDiet = dietRepository.findById(added.getDiet().getId()).get();
        int noOfCal = cDiet.getNoCalories();

        Foods cFood = foodRepository.findById(added.getFood().getId()).get();

        int newCal = (cFood.getCaloriesPer100G() * added.getFood_amount())/100;
        noOfCal = noOfCal + newCal;
        cDiet.setNoCalories(noOfCal);

        dietRepository.save(cDiet);

        return added;
    }

    public Diet addDiet(@RequestBody Diet diet){
        return dietRepository.save(diet);
    }

    public List<Diet> getAllDiets(){
        return dietRepository.findAll();
    }
}
