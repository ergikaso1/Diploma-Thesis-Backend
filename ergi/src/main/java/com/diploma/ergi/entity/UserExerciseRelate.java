package com.diploma.ergi.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_exercise_relate")
@NoArgsConstructor
@AllArgsConstructor
public class UserExerciseRelate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column
    private int total_reps;

    @Column
    private int total_duration;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getTotal_reps() {
        return total_reps;
    }

    public void setTotal_reps(int total_reps) {
        this.total_reps = total_reps;
    }

    public int getTotal_duration() {
        return total_duration;
    }

    public void setTotal_duration(int total_duration) {
        this.total_duration = total_duration;
    }

    @Override
    public String toString(){
        return "";
    }
}
