package com.diploma.ergi.service;

import com.diploma.ergi.entity.Foods;
import com.diploma.ergi.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private FoodRepository foodRepository;


    public FoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    public void addFood(Foods food){
        foodRepository.save(food);
    }

    public List<Foods> getAllFoods(){
        return foodRepository.findAll();
    }

    public void delete(int id){
        foodRepository.deleteById(id);
    }

}
