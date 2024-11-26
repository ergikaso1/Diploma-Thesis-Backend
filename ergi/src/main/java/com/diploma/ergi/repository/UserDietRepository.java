package com.diploma.ergi.repository;

import com.diploma.ergi.entity.User;
import com.diploma.ergi.entity.UserDietRelate;
import com.diploma.ergi.entity.UserExerciseRelate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDietRepository extends JpaRepository<UserDietRelate,Integer>{

    List<UserDietRelate> findByUser(User user);
}
