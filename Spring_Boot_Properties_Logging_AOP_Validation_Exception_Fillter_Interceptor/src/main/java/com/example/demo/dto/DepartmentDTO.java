package com.example.demo.dto;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class DepartmentDTO {
    @Valid
    Long departmentId;
    @NotNull(message="Thiếu title")
    @Length(min=10,max=50,message="Độ dài phải từ 10 đến 50 ")
    String deptName;
    @NotNull(message="Thiếu description")
    String description;
    @Valid
    List<EmployeeDTO> employeeDTOList;
}
