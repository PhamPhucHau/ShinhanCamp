package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("department")
public class DepartmentController {
    @PostMapping("")
    public ResponseEntity<DepartmentDTO> getDepartmentDTO(@RequestBody @Valid DepartmentDTO  departmentDTO)
    {
        return new ResponseEntity("Adding department success", HttpStatus.OK);
    }

}
