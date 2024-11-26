package com.diploma.ergi.controller;
import com.diploma.ergi.entity.User;
import com.diploma.ergi.entity.UserRoleRelate;
import com.diploma.ergi.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.diploma.ergi.service.UserService;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})

public class UserController {


    private final UserService userService;
    private final UserRoleService userRoleService;

    @Autowired
    public UserController(UserService userService, UserRoleService userRoleService){
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @PostMapping("/addUser")
    public void addUser(@AuthenticationPrincipal com.diploma.ergi.auth.models.User authUser, @RequestBody User user){
        user.setFirebaseId(authUser.getUid());
        System.err.println(authUser.getUid());
        userService.saveUser(user);
    }

    @PostMapping("/addRole/{uid}")
    public UserRoleRelate addRole(@PathVariable String uid, @AuthenticationPrincipal com.diploma.ergi.auth.models.User authUser, @RequestBody UserRoleRelate userRole){
        return userRoleService.saveUserRole(userRole);
    }

    @DeleteMapping("/removeRole/{userRoleId}")
    public void removeRole(@PathVariable Integer userRoleId, @AuthenticationPrincipal com.diploma.ergi.auth.models.User authUser){
        userRoleService.removeUserRole(userRoleId);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<User> getUserByUid(@PathVariable String uid, @AuthenticationPrincipal User user) {

        User fetchedUser = userService.findByFirebaseId(uid);
        if (fetchedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        fetchedUser.setLogTime(new Date());
        userService.updateUser(fetchedUser);
        return ResponseEntity.ok(fetchedUser);
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/allAsc")
    public List<User> getAllAsc(){
        return userService.getAllAsc();
    }


    @DeleteMapping("/remove/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable String uid, @AuthenticationPrincipal User user) {
        User fetchedUser = userService.findByFirebaseId(uid);
        if (fetchedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Instant from = fetchedUser.getLogTime().toInstant();
        Instant to = new Date().toInstant();
        Duration duration = Duration.between(from, to);
        if (duration.compareTo(Duration.ofDays(30)) < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nuk mund te fshish user sepse ka qen aktiv ne 30 ditet e fundit!");
        }
        userService.deleteUser(fetchedUser.getId());
        return ResponseEntity.ok("Useri u  fshi me sukses!");
    }
}
