package com.diploma.ergi.controller;

import com.diploma.ergi.entity.Exercise;

import com.diploma.ergi.entity.User;
import com.diploma.ergi.entity.UserExerciseRelate;
import com.diploma.ergi.repository.UserExerciseRepository;
import com.diploma.ergi.service.ExerciseService;
import com.diploma.ergi.service.ImageService;
import com.diploma.ergi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userExercise")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class UserExerciseController {

    @Autowired
    private UserExerciseRepository userExerciseRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private ExerciseService exerciseService;

    public UserExerciseController(UserExerciseRepository userExerciseRepository, UserService userService, ExerciseService exerciseService) {
        this.userExerciseRepository = userExerciseRepository;
        this.userService = userService;
        this.exerciseService = exerciseService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addExerciseToUser(@AuthenticationPrincipal User user ,@RequestBody UserExerciseRelate userExerciseRelate) {

        userExerciseRepository.save(userExerciseRelate);
        return ResponseEntity.ok("Exercise added successfully to user");
    }

    @GetMapping("/get/{uid}")
    public ResponseEntity<?> getExerciseToUser(@PathVariable String uid, @AuthenticationPrincipal User user) {
        User fetchedUser = userService.findByFirebaseId(uid);
        if (fetchedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<UserExerciseRelate> exercises = userExerciseRepository.findByUser(fetchedUser);
        List<Exercise> exerciseList = new ArrayList<>();
        for(UserExerciseRelate e : exercises){
            exerciseList.add(e.getExercise());
        }
        for(int i = 0; i < exercises.size(); i++){
            exerciseList.get(i).setReps(exercises.get(i).getTotal_reps());
            exerciseList.get(i).setDuration(exercises.get(i).getTotal_duration());
        }

        return ResponseEntity.ok(exerciseList);
    }

    @GetMapping("/get/{uid}/top")
    public ResponseEntity<?> getExerciseToUserTop(@PathVariable String uid, @AuthenticationPrincipal User user) {
        User fetchedUser = userService.findByFirebaseId(uid);
        if (fetchedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Object[]> exerciseList = exerciseService.findTopExercises(fetchedUser.getId());

        return ResponseEntity.ok(exerciseList);
    }




}