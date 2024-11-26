package com.diploma.ergi.repository;

import com.diploma.ergi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    boolean existsByEmail(String email);

    User findByFirebaseId(String fId);

    List<User> findAllByOrderByIdAsc();



}