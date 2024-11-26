package com.diploma.ergi.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
@Table(name = "foods")
public class Foods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "protein_count")
    private int proteinCount;

    @Column(name = "fat_count")
    private int fatCount;

    @Column(name = "fiber_count")
    private int fiberCount;

    @Column(name = "calories_100g")
    private int caloriesPer100G;

    @Column(name = "image_path")
    private String image;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "food", cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    private List<DietFoodRelate> dietFoodRelate;



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

    public int getProteinCount() {
        return proteinCount;
    }

    public void setProteinCount(int proteinCount) {
        this.proteinCount = proteinCount;
    }

    public int getFatCount() {
        return fatCount;
    }

    public void setFatCount(int fatCount) {
        this.fatCount = fatCount;
    }

    public int getFiberCount() {
        return fiberCount;
    }

    public void setFiberCount(int fiberCount) {
        this.fiberCount = fiberCount;
    }

    public int getCaloriesPer100G() {
        return caloriesPer100G;
    }

    public void setCaloriesPer100G(int caloriesPer100G) {
        this.caloriesPer100G = caloriesPer100G;
    }

    public void setDiets(List<DietFoodRelate> DietFoodRelate) {
        this.dietFoodRelate = dietFoodRelate;
    }

    public List<DietFoodRelate> getDiets(){
        return this.dietFoodRelate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
