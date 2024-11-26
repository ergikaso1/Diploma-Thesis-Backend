package com.diploma.ergi.repository;

import com.diploma.ergi.entity.Foods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Foods, Integer> {

}
