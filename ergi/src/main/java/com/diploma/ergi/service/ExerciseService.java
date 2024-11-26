package com.diploma.ergi.service;

import com.diploma.ergi.entity.Exercise;
import com.diploma.ergi.entity.UserExerciseRelate;
import com.diploma.ergi.repository.ExerciseRepository;
import com.diploma.ergi.repository.UserExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class ExerciseService {


    @Autowired
    private ExerciseRepository exerciseRepository;


    @Autowired
    private UserExerciseRepository userExerciseRelateRepository;


    public ExerciseService(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise add(Exercise exercise){
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getAll(){
        return  exerciseRepository.findAll();
    }

    public Exercise findById(int id){
        return exerciseRepository.findExerciseById(id);
    }

    public void delete(int exerciseId) {
        // Delete related records first
        userExerciseRelateRepository.deleteByExerciseId(exerciseId);

        // Then delete the exercise
        exerciseRepository.deleteById(exerciseId);
    }

    public List<Object[]> findTopExercises(int id){
        return userExerciseRelateRepository.findTopExercisesByUser(id);
    }
}
