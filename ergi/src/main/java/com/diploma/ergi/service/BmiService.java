package com.diploma.ergi.service;

import com.diploma.ergi.entity.BMI;
import com.diploma.ergi.entity.User;
import com.diploma.ergi.repository.BmiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BmiService{

    private final BmiRepository bmiRepository;

    public BmiService(BmiRepository bmiRepository){
        this.bmiRepository = bmiRepository;
    }


    public Optional<BMI> getBmiById(int id) {
        return bmiRepository.findById(id);
    }

    public BMI saveBmi(BMI bmi) {return bmiRepository.save(bmi);
    }
    public List<BMI> findLatest(User user){
        return bmiRepository.findByUserOrderByIdDesc(user);
    }

    public List<BMI> findAll(User user){
        return bmiRepository.findBMIByUser(user);
    }

}
