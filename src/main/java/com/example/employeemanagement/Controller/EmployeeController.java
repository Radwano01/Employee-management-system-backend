package com.example.employeemanagement.Controller;

import com.example.employeemanagement.Dto.AdminLoginDto;
import com.example.employeemanagement.Dto.employee.EmployeeDto;
import com.example.employeemanagement.Entity.EmployeeEntity;
import com.example.employeemanagement.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path="${EMPLOYEE_CONTROLLER_PATH}")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("${EMPLOYEE_CREATE_PATH}")
    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.addNewEmployee(employeeDto);
    }

    @GetMapping("${EMPLOYEE_GET_PATH}")
    public List<EmployeeEntity> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @DeleteMapping("${EMPLOYEE_DELETE_PATH}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("${EMPLOYEE_UPDATE_PATH}")
    public ResponseEntity<?> editEmployee(@PathVariable("id") int id,@RequestBody EmployeeDto employeeDto){
        return employeeService.editEmployee(id, employeeDto);
    }

    @GetMapping("${EMPLOYEE_GET_BY_ID_PATH}")
    public ResponseEntity<?> getSingleEmployee(@PathVariable("id") int id){
        return employeeService.getSingleEmployee(id);
    }
}
