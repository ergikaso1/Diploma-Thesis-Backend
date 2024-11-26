package com.diploma.ergi.repository;

import com.diploma.ergi.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer>{

    Exercise findExerciseById(int id);

}
