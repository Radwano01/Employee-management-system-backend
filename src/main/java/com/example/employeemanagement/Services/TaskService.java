package com.example.employeemanagement.Services;

import com.example.employeemanagement.Dto.task.TaskDto;
import com.example.employeemanagement.Entity.EmployeeEntity;
import com.example.employeemanagement.Entity.TaskEntity;
import com.example.employeemanagement.Repository.EmployeeRepository;
import com.example.employeemanagement.Repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository, EmployeeRepository employeeRepository){
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<?> addNewTask(TaskDto taskDto){
        try{
            Optional<EmployeeEntity> employeeEmail = employeeRepository.findByEmail(taskDto.getEmployeeEmail());

            if(taskDto.getTask() != null){
                TaskEntity taskEntity = new TaskEntity();
                taskEntity.setData(taskDto.getTitle() ,taskDto.getTask(), taskDto.getStartDate(), taskDto.getEndDate(), List.of(employeeEmail.get()), taskDto.getCompleted());
                taskRepository.save(taskEntity);
                return new ResponseEntity<>("Task added Successfully: "+ taskDto.getTask(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Inputs Missing!",HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            return new ResponseEntity<>("An error occurred while processing to add Task: "
                    +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllTask() {
        try {
            List<TaskEntity> tasks = taskRepository.findAll();
            List<TaskDto> tasksDto = tasks.stream().map(task-> {
                TaskDto dto = new TaskDto();
                dto.setTask(task.getTask());
                dto.setStartDate(task.getStartDate());
                dto.setEndDate(task.getEndDate());
                dto.setCompleted(task.getCompleted());
                String employeeEmail = task.getEmployee().stream()
                        .map(EmployeeEntity::getEmail).collect(Collectors.joining(", "));
                dto.setEmployeeEmail(employeeEmail);
                return dto;
            }).toList();
            return new ResponseEntity<>(tasksDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred while processing the Fetching Tasks: "
                    +e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> editTask(int id,TaskDto taskDto) {
        try{
            EmployeeEntity employeeEmail = employeeRepository.findByEmail(taskDto.getEmployeeEmail())
                    .orElseThrow(()-> new EntityNotFoundException("Email Not Found!"+taskDto.getEmployeeEmail()));
            TaskEntity existTask = taskRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Task id Not found: " + id));
            if (!taskDto.getTask().isEmpty()) {
                existTask.setData(taskDto.getTitle() ,taskDto.getTask(), taskDto.getStartDate(),
                        taskDto.getEndDate(), List.of(employeeEmail), taskDto.getCompleted()
                );
                return new ResponseEntity<>("Task updated Successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Inputs Missing!", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred while processing to update a task: "
                    +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteTask(int id) {
        try{
            taskRepository.deleteById(id);
            return new ResponseEntity<>("Task id deleted Successfully: "+id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred while processing to delete a Task: "
                    +e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> getEmployeeTasks(String employeeEmail) {
        try {
            List<TaskEntity> tasks = taskRepository.findAll();
            List<TaskDto> tasksDto = tasks.stream().map(task-> {
                TaskDto dto = new TaskDto();
                dto.setId(task.getId());
                dto.setTitle(task.getTitle());
                dto.setTask(task.getTask());
                dto.setStartDate(task.getStartDate());
                dto.setEndDate(task.getEndDate());
                dto.setCompleted(task.getCompleted());
                String validEmployeeEmail = task.getEmployee().stream()
                        .map(employee -> employee.getEmail()).collect(Collectors.joining(", "));
                dto.setEmployeeEmail(validEmployeeEmail);
                if(validEmployeeEmail.equals(employeeEmail)){
                    return dto;
                }
                return null;
            }).toList();

            return new ResponseEntity<>(tasksDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred while processing the Fetching Tasks: "
                    +e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getEmployeeSingleTask(int id, String employeeEmail) {
        try {
            TaskEntity singleTask = taskRepository.findById(id)
                    .orElseThrow(()-> new EntityNotFoundException("Task id Not Found. "+id));
            TaskDto dto = new TaskDto();
            dto.setId(singleTask.getId());
            dto.setTitle(singleTask.getTitle());
            dto.setTask(singleTask.getTask());
            dto.setStartDate(singleTask.getStartDate());
            dto.setEndDate(singleTask.getEndDate());
            dto.setCompleted(singleTask.getCompleted());
            dto.setEmployeeEmail(null);
            if(singleTask.getEmployee().stream().anyMatch(employee -> employee.getEmail().equals(employeeEmail))){
                return new ResponseEntity<>(List.of(dto), HttpStatus.OK);
            }

            return null;
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the Fetching Tasks: "
                    +e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
