package com.diploma.ergi.controller;

import com.diploma.ergi.entity.*;
import com.diploma.ergi.repository.UserDietRepository;
import com.diploma.ergi.repository.UserExerciseRepository;
import com.diploma.ergi.service.DietService;
import com.diploma.ergi.service.ExerciseService;
import com.diploma.ergi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userDiet")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class UserDietController {

    @Autowired
    private UserDietRepository userDietRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private DietService dietService;

    public UserDietController(UserDietRepository userDietRepository, UserService userService, DietService dietService) {
        this.userDietRepository = userDietRepository;
        this.userService = userService;
        this.dietService = dietService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDietToUser(@AuthenticationPrincipal User user , @RequestBody UserDietRelate userDietRelate) {

        userDietRepository.save(userDietRelate);
        return ResponseEntity.ok("Diet added successfully to user");
    }

    @GetMapping("/get/{uid}")
    public ResponseEntity<?> getDietToUser(@PathVariable String uid, @AuthenticationPrincipal User user) {
        User fetchedUser = userService.findByFirebaseId(uid);
        if (fetchedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<UserDietRelate> dietRelates = userDietRepository.findByUser(fetchedUser);
        List<Diet> dietList = new ArrayList<>();
        for (UserDietRelate d : dietRelates) {
            dietList.add(d.getDiet());
        }
        return ResponseEntity.ok(dietList);
    }
}
