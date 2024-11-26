package com.diploma.ergi.repository;

import com.diploma.ergi.entity.Feedback;
import com.diploma.ergi.entity.UserRoleRelate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleRelate,Integer> {

}
