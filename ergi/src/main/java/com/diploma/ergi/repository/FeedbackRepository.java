package com.diploma.ergi.repository;

import com.diploma.ergi.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

    @Query("SELECT AVG(f.rating) FROM Feedback f")
    BigDecimal findAverageRating();
}
