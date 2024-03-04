package com.example.employeemanagement.Dto.task;

import com.example.employeemanagement.Entity.EmployeeEntity;

import java.util.List;

public class TaskDto {

    private int id;
    private String title;
    private String task;
    private String employeeEmail;
    private String endDate;
    private String completed;
    private String startDate;

    public TaskDto(String task, String endDate, String completed) {
        this.task = task;
        this.endDate = endDate;
        this.completed = completed;
    }

    public TaskDto() {

    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
