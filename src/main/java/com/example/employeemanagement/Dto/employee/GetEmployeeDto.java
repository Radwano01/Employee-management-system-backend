package com.example.employeemanagement.Dto.employee;

import com.example.employeemanagement.Entity.FieldEntity;
import com.example.employeemanagement.Entity.LevelEntity;
import com.example.employeemanagement.Entity.RoleEntity;

import java.sql.Blob;
import java.util.List;

public class GetEmployeeDto {
    private String name;
    private String surname;
    private String email;
    private int salary;
    private List<RoleEntity> role;
    private List<FieldEntity> field;
    private List<LevelEntity> level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleEntity> getRoles() {
        return role;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.role = roles;
    }

    public List<FieldEntity> getFields() {
        return field;
    }

    public void setFields(List<FieldEntity> fields) {
        this.field = fields;
    }

    public List<LevelEntity> getLevels() {
        return level;
    }

    public void setLevels(List<LevelEntity> levels) {
        this.level = levels;
    }

    public GetEmployeeDto(String name, String surname, String email,
                          int salary, List<FieldEntity> field, List<LevelEntity> level, List<RoleEntity> role){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.salary = salary;
        this.field = field;
        this.level = level;
        this.role = role;

    }

    public GetEmployeeDto(){}
}
