package com.diploma.ergi.controller;

import com.diploma.ergi.entity.BMI;
import com.diploma.ergi.entity.User;
import com.diploma.ergi.service.BmiService;
import com.diploma.ergi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/bmi")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class BmiController {

    @Autowired
    private BmiService bmiService;

    @Autowired
    private UserService userService;

    public BmiController(BmiService bmiService) {
        this.bmiService = bmiService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBmi(@RequestBody BMI bmi) {
        bmiService.saveBmi(bmi);
        return ResponseEntity.ok("BMI data saved successfully");
    }

    @GetMapping("/latestBmi/{uid}")
    public ResponseEntity<?> latestBmi(@PathVariable("uid") String uid) {
        User user = userService.findByFirebaseId(uid);
        List<BMI> bmiList = bmiService.findLatest(user);

        if (bmiList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No BMI records found for the user.");
        }

        return ResponseEntity.ok(bmiList.get(0));
    }

    @GetMapping("/all/{uid}")
    public ResponseEntity<?> findAll(@PathVariable("uid") String uid){
        User user = userService.findByFirebaseId(uid);
        return ResponseEntity.ok(bmiService.findAll(user));
    }


    @GetMapping("/{uid}")
    public ResponseEntity<?> getBmi(@PathVariable("uid") String uid) {
        User user = userService.findByFirebaseId(uid);

        return ResponseEntity.ok(user.getBmi());

    }

}
