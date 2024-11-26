package com.diploma.ergi.controller;

import com.diploma.ergi.entity.Foods;
import com.diploma.ergi.service.FoodService;
import com.diploma.ergi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/foods")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class FoodController {

    @Autowired
    private FoodService foodService;

    private ImageService imageService;

    public FoodController(FoodService foodService, ImageService imageService){
        this.foodService = foodService;
        this.imageService = imageService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFoodWithImage(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("proteinCount") int proteinCount,
            @RequestParam("fatCount") int fatCount,
            @RequestParam("fiberCount") int fiberCount,
            @RequestParam("caloriesPer100G") int caloriesPer100G,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        Foods food = new Foods();
        food.setName(name);
        food.setDescription(description);
        food.setProteinCount(proteinCount);
        food.setFatCount(fatCount);
        food.setFiberCount(fiberCount);
        food.setCaloriesPer100G(caloriesPer100G);

        if (image != null && !image.isEmpty()) {
            try {
                String imageName = imageService.saveImage(new File("src/main/resources/static"), image, name);
                food.setImage(imageName);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
            }
        }

        foodService.addFood(food);
        return ResponseEntity.ok("Food added successfully");
    }

    @GetMapping("/all")
    public List<Foods> getAll(){
        return foodService.getAllFoods();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable int id) {
        try {
            foodService.delete(id);
            return ResponseEntity.ok("Food deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not found");
        }
    }

}
