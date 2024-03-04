package com.example.employeemanagement.Repository;

import com.example.employeemanagement.Entity.FieldEntity;
import com.example.employeemanagement.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Integer> {
    Optional<FieldEntity> findByField(String field);
}