package com.example.employeemanagement.Services;

import com.example.employeemanagement.Dto.employee.EmployeeDto;
import com.example.employeemanagement.Dto.employee.GetEmployeeDto;
import com.example.employeemanagement.Entity.EmployeeEntity;
import com.example.employeemanagement.Entity.FieldEntity;
import com.example.employeemanagement.Entity.LevelEntity;
import com.example.employeemanagement.Entity.RoleEntity;
import com.example.employeemanagement.Repository.EmployeeRepository;
import com.example.employeemanagement.Repository.FieldRepository;
import com.example.employeemanagement.Repository.LevelRepository;
import com.example.employeemanagement.Repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final FieldRepository fieldRepository;
    private final LevelRepository levelRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           RoleRepository roleRepository,
                           FieldRepository fieldRepository,
                           LevelRepository levelRepository,
                           PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.fieldRepository = fieldRepository;
        this.levelRepository = levelRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> addNewEmployee(EmployeeDto employeeDto) {
        try {
            Optional<EmployeeEntity> findEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
            if(findEmployee.isPresent()){
                return new ResponseEntity<>("Email already valid: "+employeeDto.getEmail(), HttpStatus.CONFLICT);
            } else {
                if(checkInputs(employeeDto)) {
                    return new ResponseEntity<>("Inputs Missing", HttpStatus.BAD_REQUEST);
                } else {
                    EmployeeEntity employeeEntity = new EmployeeEntity();
                    EmployeeEntity processedEmployee = setData(employeeDto, employeeEntity);
                    employeeRepository.save(processedEmployee);
                    return new ResponseEntity<>("Employee details added Successfully!", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the POST: "
                    +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<EmployeeEntity> getAllEmployee() {
        try{
            return employeeRepository.findAll();
        }catch (Exception e){
            throw new IllegalStateException("An error occurred while processing the GET: "+e.getMessage());
        }
    }

    public ResponseEntity<?> deleteEmployee(int id) {
        try{
            employeeRepository.deleteById(id);
            return new ResponseEntity<>("Employee details deleted successfully", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("An error occurred while processing the DELETE: "
                    +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    public ResponseEntity<?> editEmployee(int id, EmployeeDto employeeDto){
        try {
            EmployeeEntity employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Employee id Not Found: "+id));
            if (checkInputs(employeeDto)) {
                return new ResponseEntity<>("Inputs Missing", HttpStatus.BAD_REQUEST);
            } else {
                setData(employeeDto, employee);
                return new ResponseEntity<>("Employee Details updated!", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred while processing the PUT: "+
                    e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getSingleEmployee(int id) {
        try {
            EmployeeEntity employeeData = employeeRepository
                    .findById(id).orElseThrow(()-> new UsernameNotFoundException("Employee id Not Found: "+id));
            GetEmployeeDto employeeDto = createEmployeeDto(employeeData);
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the GET BY ID: "
                    +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private GetEmployeeDto createEmployeeDto(EmployeeEntity employee) {
        List<FieldEntity> field = employee.getField();
        List<LevelEntity> level = employee.getLevel();
        List<RoleEntity> role = employee.getRole();

        return new GetEmployeeDto(
                employee.getName(), employee.getSurname(), employee.getEmail(),
                employee.getSalary(), field, level, role
        );
    }

    private boolean checkInputs(EmployeeDto employeeDto){
        return  employeeDto.getName().isEmpty() &&
                employeeDto.getSurname().isEmpty() &&
                employeeDto.getEmail().isEmpty() &&
                employeeDto.getSalary() < 0 &&
                employeeDto.getLevel().isEmpty() &&
                employeeDto.getField().isEmpty() &&
                employeeDto.getRole().isEmpty();
    }

    private LevelEntity Level(EmployeeDto employeeDto){
        try {
            return levelRepository.findByLevel(employeeDto.getLevel())
                    .orElseThrow(() -> new EntityNotFoundException("Level Not Found!"));
        }catch (Exception e){
            throw new IllegalArgumentException("An error occurred while processing the request");
        }

    }

    private FieldEntity Field(EmployeeDto employeeDto){
        try {
            return fieldRepository.findByField(employeeDto.getField())
                    .orElseThrow(() -> new EntityNotFoundException("Field Not Found!"));
        }catch (Exception e){
            throw new IllegalArgumentException("An error occurred while processing the request");
        }
    }

    private RoleEntity Role(EmployeeDto employeeDto){
        try {
            return roleRepository.findByRole(employeeDto.getRole())
                    .orElseThrow(() -> new EntityNotFoundException("Role Not Found!"));
        }catch (Exception e){
            throw new IllegalArgumentException("An error occurred while processing the request");
        }

    }

    private EmployeeEntity setData(EmployeeDto employeeDto, EmployeeEntity employeeEntity) {

        try {
            LevelEntity level = Level(employeeDto);
            FieldEntity field = Field(employeeDto);
            RoleEntity role = Role(employeeDto);

            employeeEntity.setData(
                    employeeDto.getName(),
                    employeeDto.getSurname(),
                    employeeDto.getEmail(),
                    employeeDto.getSalary(),
                    List.of(level),
                    List.of(field),
                    List.of(role),
                    passwordEncoder.encode(employeeDto.getPassword())
            );
            return employeeEntity;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error setting employee data: " + e.getMessage());
        }
    }
}
