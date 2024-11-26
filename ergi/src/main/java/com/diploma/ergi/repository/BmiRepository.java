package com.diploma.ergi.repository;

import com.diploma.ergi.entity.BMI;
import com.diploma.ergi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BmiRepository extends JpaRepository<BMI, Integer> {

    List<BMI> findByUserOrderByIdDesc(User user);

    List<BMI> findBMIByUser(User user);
}
