package com.diploma.ergi.repository;

import com.diploma.ergi.entity.DietFoodRelate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietFoodRepository extends JpaRepository<DietFoodRelate,Integer> {

}