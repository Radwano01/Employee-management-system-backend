package com.example.employeemanagement.Controller;

import com.example.employeemanagement.Dto.task.TaskDto;
import com.example.employeemanagement.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("${TASK_CONTROLLER_PATH}")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("${TASK_CREATE_PATH}")
    public ResponseEntity<?> addNewTask(@RequestBody TaskDto taskDto) {
        return taskService.addNewTask(taskDto);
    }

    @GetMapping("${TASK_GET_PATH}")
    public ResponseEntity<?> getAllTask() {
        return taskService.getAllTask();
    }

    @PutMapping("${TASK_UPDATE_PATH}")
    public ResponseEntity<?> editTask(@PathVariable("id") int id, @RequestBody TaskDto taskDto) {
        return taskService.editTask(id,taskDto);
    }

    @DeleteMapping("${TASK_DELETE_PATH}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") int id){
        return taskService.deleteTask(id);
    }

    @GetMapping("${TASK_GET_BY_EMAIL_PATH}")
    public ResponseEntity<?> getEmployeeTasks(@PathVariable("email") String employeeEmail){
        return taskService.getEmployeeTasks(employeeEmail);
    }

    @GetMapping("${TASK_GET_BY_ID_AND_EMAIL_PATH}")
    public ResponseEntity<?> getEmployeeSingleTask(@PathVariable("id") int id,@PathVariable("email") String employeeEmail){
        return taskService.getEmployeeSingleTask(id,employeeEmail);
    }
}
