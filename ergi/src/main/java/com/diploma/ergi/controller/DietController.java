package com.diploma.ergi.controller;

import com.diploma.ergi.entity.Diet;
import com.diploma.ergi.entity.DietFoodRelate;
import com.diploma.ergi.repository.DietFoodRepository;
import com.diploma.ergi.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diet")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class DietController {

    @Autowired
    private DietService dietService;


    @PostMapping("/add")
    public ResponseEntity<String> addDiet(@RequestBody Diet diet){
        dietService.addDiet(diet);
        return  ResponseEntity.ok("Diet added successfully");
    }

    @GetMapping("/all")
    public List<Diet> getAll(){
        return dietService.getAllDiets();
    }

    @PostMapping("/addFood")
    public ResponseEntity<DietFoodRelate> addFoodToDiet(@RequestBody DietFoodRelate dietFoodRelate) {
        try {
            DietFoodRelate updatedDiet = dietService.addFoodToDiet(dietFoodRelate);
            return ResponseEntity.ok(updatedDiet);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}