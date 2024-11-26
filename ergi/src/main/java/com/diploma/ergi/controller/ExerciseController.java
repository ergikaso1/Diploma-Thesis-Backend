package com.diploma.ergi.controller;

import com.diploma.ergi.entity.Exercise;

import com.diploma.ergi.service.ExerciseService;
import com.diploma.ergi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/exercises")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ImageService imageService;

    public ExerciseController(ExerciseService exerciseService, ImageService imageService) {
        this.exerciseService = exerciseService;
        this.imageService = imageService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addExerciseWithImage(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("route") String route,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setRoute(route);

        if (image != null && !image.isEmpty()) {
            try {
                String imageName = imageService.saveImage(new File("src/main/resources/static"), image, name);
                exercise.setImage(imageName);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
            }
        }
        exerciseService.add(exercise);
        return ResponseEntity.ok("Exercise added successfully");
    }

    @GetMapping("/all")
    public List<Exercise> getAll(){
        return exerciseService.getAll();
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Exercise> getExerciseByUid(@PathVariable Integer uid) {
        Exercise fetchedExercise = exerciseService.findById(uid);
        if (fetchedExercise == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(fetchedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable int id) {
        try {
            exerciseService.delete(id);
            return ResponseEntity.ok("Exercise deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found");
        }
    }




}