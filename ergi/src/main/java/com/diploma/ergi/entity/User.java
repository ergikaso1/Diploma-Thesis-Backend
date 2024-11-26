package com.diploma.ergi.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "firebase_id")
    private String firebaseId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "log_time")
    private Date logTime;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Feedback> feedbacks;



    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<UserRoleRelate> userRoleRelates;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<UserDietRelate> userDietRelate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<BMI> bmis;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<UserExerciseRelate> exercises;

    public User(int id, String name, String surname, int age, Date birthday, String phone, String email, String gender, String firebaseId,Date logTime) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.firebaseId = firebaseId;
        this.logTime = logTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    public List<Feedback> getFeedbacks(){
        return this.feedbacks;
    }

    public void setRoles(List<UserRoleRelate> userRoleRelates) {
        this.userRoleRelates = userRoleRelates;
    }
    public List<UserRoleRelate> getRoles(){
        return this.userRoleRelates;
    }

    public void setDiets(List<UserDietRelate> UserDietRelate) {
        this.userDietRelate = userDietRelate;
    }
    public List<UserDietRelate> getDiets(){
        return this.userDietRelate;
    }

    public void setBmis(List<BMI> bmis) {
        this.bmis = bmis;
    }
    public List<BMI> getBmi(){
        return this.bmis;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public List<UserExerciseRelate> getExercises() {
        return exercises;
    }

    public void setExercises(List<UserExerciseRelate> exercises) {
        this.exercises = exercises;
    }
}
