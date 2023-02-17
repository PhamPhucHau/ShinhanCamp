package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@ControllerAdvice
@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("")
    public ResponseEntity<EmployeeDTO> getEmployee( @Valid @RequestBody  EmployeeDTO employeeDTO)
    {
        return new ResponseEntity("Adding employee success", HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<EmployeeDTO> getEmployeeService( @Valid @RequestBody  EmployeeDTO employeeDTO)
    {

        return new ResponseEntity(employeeService.getEmployeeDTO(employeeDTO), HttpStatus.OK);
    }
}
