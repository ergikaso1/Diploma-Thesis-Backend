package com.diploma.ergi.repository;

import com.diploma.ergi.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface UserExerciseRepository extends JpaRepository<UserExerciseRelate,Integer> {

    void deleteByExerciseId(int exerciseId);

    List<UserExerciseRelate> findByUser(User user);

    @Query("SELECT e.exercise.name, e.total_reps, e.total_duration " +
            "FROM UserExerciseRelate e " +
            "WHERE e.exercise.id IN (" +
            "   SELECT u.exercise.id FROM UserExerciseRelate u " +
            "   WHERE u.user.id = :userId " +
            "   GROUP BY u.exercise.id " +
            "   ORDER BY COUNT(u.exercise) DESC)")
    List<Object[]> findTopExercisesByUser(@Param("userId") int userId);



}
