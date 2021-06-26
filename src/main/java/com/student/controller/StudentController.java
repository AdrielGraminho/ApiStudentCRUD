package com.student.controller;

import com.student.entities.Student;
import com.student.repository.StudentRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class StudentController {

    StudentRepository repository;

    @ApiOperation(value = "return all students")
    @GetMapping("/student")
    public ResponseEntity getAllStudents(){
        try {
            return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @ApiOperation(value = "return student by id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "")
    )
    @GetMapping("/student/{id}")
    public ResponseEntity getStudentById(@PathVariable Long id){
        try {
            return new ResponseEntity<>( repository.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( "Aluno não encontrado", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @ApiOperation(value = "save new student")
    @ApiResponses(
            @ApiResponse(code = 200, message = "sucesso na criação")
    )
    @PostMapping("/student")
    public ResponseEntity saveStudent(@RequestBody Student product){
        try {
            repository.save(product);
            return new ResponseEntity<>( "sucesso na criação", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( "erro na validação", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @ApiOperation(value = "delete student by id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "sucesso na remoção")
    )
    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        try {
            repository.deleteById(id);
            return new ResponseEntity<>( "sucesso na remoção", HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity<>( "erro na remoção", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @ApiOperation(value = "edit student by id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "sucesso na atualização")
    )
    @PutMapping(value = "/student/{id}")
    public ResponseEntity updateStudent(@PathVariable(value = "id") long id, @RequestBody Student newStudent) {
        Optional<Student> oldStudent = repository.findById(id);
        try {
            if (oldStudent.isPresent()) {
                Student student = oldStudent.get();
                student.setId(id);
                student.setName(newStudent.getName());
                student.setEmail(newStudent.getEmail());
                student.setRegistration(newStudent.getRegistration());
                repository.save(student);
                return new ResponseEntity<>("sucesso na atualização", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("erro na validação", HttpStatus.METHOD_NOT_ALLOWED);
            }
        }catch (Exception e){
            return new ResponseEntity<>("erro na validação", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
