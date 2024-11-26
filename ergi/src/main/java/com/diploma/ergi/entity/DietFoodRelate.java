package com.diploma.ergi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "diet_food_relate")
@NoArgsConstructor
@AllArgsConstructor
public class DietFoodRelate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    @JsonIgnoreProperties(value = {"diets"})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Foods food;

    @ManyToOne
    @JoinColumn(name = "diet_id")
    @JsonIgnoreProperties(value = {"foods"})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Diet diet;

    @Column(name = "food_amount")
    private int food_amount;

    public int getFood_amount() {
        return food_amount;
    }

    public void setFood_amount(int food_amount) {
        this.food_amount = food_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Foods getFood() {
        return food;
    }

    public void setFood(Foods food) {
        this.food = food;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    @Override
    public String toString(){
        return "";
    }
}

