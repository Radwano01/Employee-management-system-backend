package com.example.employeemanagement.Entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String email;
    private int salary;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="employee_role",
            joinColumns = @JoinColumn(name="employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="employee_field",
            joinColumns = @JoinColumn(name="employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="field_id", referencedColumnName = "id")
    )
    private List<FieldEntity> fields = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="employee_level",
            joinColumns = @JoinColumn(name="employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "level_id", referencedColumnName = "id")
    )
    private List<LevelEntity> levels = new ArrayList<>();


    public void setData(String name, String surname, String email, int salary,
                        List<LevelEntity> level, List<FieldEntity> field, List<RoleEntity> role, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.salary = salary;
        this.levels = level;
        this.fields = field;
        this.roles = role;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<FieldEntity> getField() {
        return fields;
    }

    public void setField(List<FieldEntity> field) {
        this.fields = field;
    }

    public List<RoleEntity> getRole() {
        return roles;
    }

    public void setRole(List<RoleEntity> role) {
        this.roles = role;
    }

    public List<LevelEntity> getLevel() {
        return levels;
    }

    public void setLevel(List<LevelEntity> level) {
        this.levels = level;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
