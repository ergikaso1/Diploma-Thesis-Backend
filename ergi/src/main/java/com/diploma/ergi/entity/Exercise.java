package com.diploma.ergi.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "reps")
    private int reps;

    @Column(name = "duration")
    private int duration;

    @Column(name = "route")
    private String route;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<UserExerciseRelate> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }


    public List<UserExerciseRelate> getUsers() {
        return users;
    }

    public void setUsers(List<UserExerciseRelate> users) {
        this.users = users;
    }

    @Override
    public String toString(){
        return "";
    }
}
