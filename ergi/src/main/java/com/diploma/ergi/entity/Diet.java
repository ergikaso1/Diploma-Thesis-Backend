package com.diploma.ergi.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diet")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "goal")
    private String goal;

    @Column(name = "benefits")
    private String benefits;

    @Column(name = "no_calories")
    private int noCalories;

    @Column(name = "min_age")
    private int minAge;

    @Column(name = "max_age")
    private int maxAge;



    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "diet", cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    private List<UserDietRelate> userDietRelate;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "diet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("diet")
    private List<DietFoodRelate> dietFoodRelate;



    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public int getNoCalories() {
        return noCalories;
    }

    public void setNoCalories(int noCalories) {
        this.noCalories = noCalories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public List<UserDietRelate> getUserDietRelate() {
        return userDietRelate;
    }

    public void setUserDietRelate(List<UserDietRelate> userDietRelate) {
        this.userDietRelate = userDietRelate;
    }

    public List<DietFoodRelate> getDietFoodRelate() {
        return dietFoodRelate;
    }

    public void setDietFoodRelate(List<DietFoodRelate> dietFoodRelate) {
        this.dietFoodRelate = dietFoodRelate;
    }

    public void setUsers(List<UserDietRelate> userDietRelate) {
        this.userDietRelate = userDietRelate;
    }

    public List<UserDietRelate> getUsers(){
        return this.userDietRelate;
    }

    public void setFoods(List<DietFoodRelate> foods) {
        this.dietFoodRelate = dietFoodRelate;
    }
    public List<DietFoodRelate> getFoods(){
        return this.dietFoodRelate;
    }



    @Override
    public String toString(){
        return "";
    }


}
