package com.example.employeemanagement.Entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String task;
    private String startDate;
    private String endDate;
    private String completed;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="employee_task",
            joinColumns = @JoinColumn(name="task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="employee_id", referencedColumnName = "id")
    )
    private List<EmployeeEntity> employeeEmail = new ArrayList<>();

    public TaskEntity(){
    }

    public void setData(String title,String task, String startDate, String endDate, List<EmployeeEntity> employeeEmail, String completed){
        this.title = title;
        this.task = task;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeEmail = employeeEmail;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<EmployeeEntity> getEmployee() {
        return employeeEmail;
    }

    public void setEmployee(List<EmployeeEntity> employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
