package com.diploma.ergi.repository;

import com.diploma.ergi.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {

}
