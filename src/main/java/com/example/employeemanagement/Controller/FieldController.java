package com.example.employeemanagement.Controller;

import com.example.employeemanagement.Dto.field.FieldDto;
import com.example.employeemanagement.Services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${FIELD_CONTROLLER_PATH}")
public class FieldController {

    private final FieldService fieldService;
    @Autowired
    private FieldController(FieldService fieldService){
        this.fieldService = fieldService;
    }

    @PostMapping("${FIELD_CREATE_PATH}")
    public ResponseEntity<?> addNewField(@RequestBody FieldDto fieldDto){
        return fieldService.createNewField(fieldDto);
    }

    @GetMapping("${FIELD_GET_PATH}")
    public ResponseEntity<?> getAllField(){
        return fieldService.getAllField();
    }

    @PutMapping("${FIELD_UPDATE_PATH}")
    public ResponseEntity<?> editField(@PathVariable int id,@RequestBody FieldDto fieldDto){
        return fieldService.editField(id, fieldDto);
    }

    @DeleteMapping("${FIELD_DELETE_PATH}")
    public ResponseEntity<?> deleteField(@PathVariable int id){
        return fieldService.deleteField(id);
    }

    @GetMapping("${FIELD_GET_BY_ID_PATH}")
    public ResponseEntity<?> getSingleField(@PathVariable int id){
        return fieldService.getSingleField(id);
    }

}
