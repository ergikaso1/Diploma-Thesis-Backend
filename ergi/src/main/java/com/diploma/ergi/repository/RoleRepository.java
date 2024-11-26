package com.diploma.ergi.repository;

import com.diploma.ergi.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer>{
    Roles findByName(String name);
}
