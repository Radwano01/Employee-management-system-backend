package com.example.employeemanagement.Services;

import com.example.employeemanagement.Dto.field.FieldDto;
import com.example.employeemanagement.Entity.FieldEntity;
import com.example.employeemanagement.Repository.FieldRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;
    @Autowired
    public FieldService(FieldRepository fieldRepository){
        this.fieldRepository = fieldRepository;
    }

    public ResponseEntity<?> createNewField(FieldDto createFieldDto) {
        if(fieldRepository.findByField(createFieldDto.getField()).isPresent()){
            return new ResponseEntity<>("Field already valid", HttpStatus.CONFLICT);
        }else{
            if(createFieldDto.getField().isEmpty()){
                return new ResponseEntity<>("Inputs Missing!", HttpStatus.BAD_REQUEST);
            }else{
                try {
                    FieldEntity field = new FieldEntity();
                    field.setField(createFieldDto.getField());
                    fieldRepository.save(field);
                }catch (Exception e){
                    return new ResponseEntity<>("Something went Wrong: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return null;
    }

    public ResponseEntity<?> getAllField() {
        try {
            List<FieldEntity> fieldData = fieldRepository.findAll();
            return new ResponseEntity<>(fieldData, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong: "+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> editField(int id, FieldDto fieldDto) {
        try {
            FieldEntity field = fieldRepository.findById(id).orElseThrow(()-> new InputMismatchException("Field Not Found!"));
            field.setField(fieldDto.getField());
            fieldRepository.save(field);
            return new ResponseEntity<>("Field updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteField(int id) {
        try{
            fieldRepository.deleteById(id);
            return new ResponseEntity<>("Field Deleted Successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong: "+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> getSingleField(int id) {
        try {
            FieldEntity field = fieldRepository.findById(id).orElseThrow(() -> new IllegalStateException("Field Not Found!!!"));
            return new ResponseEntity<>(field, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}