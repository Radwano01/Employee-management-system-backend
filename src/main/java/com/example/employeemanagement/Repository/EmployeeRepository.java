package com.example.employeemanagement.Repository;

import com.example.employeemanagement.Entity.EmployeeEntity;
import com.example.employeemanagement.Entity.FieldEntity;
import com.example.employeemanagement.Entity.LevelEntity;
import com.example.employeemanagement.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
    Optional<EmployeeEntity> findByEmail(String email);

}
