package com.example.demo.service.ServiceImpl;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class DepartmentServiceImpl implements DepartmentService {
     @Override
    public DepartmentDTO getDepartmentDTO(@Valid DepartmentDTO departmentDTO)
    {
        return departmentDTO;
    }
}

