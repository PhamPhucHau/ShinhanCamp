package com.example.demo.service.ServiceImpl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
   public  EmployeeDTO getEmployeeDTO(@Valid EmployeeDTO employeeDTO)
    {

        return employeeDTO;
    }
}
