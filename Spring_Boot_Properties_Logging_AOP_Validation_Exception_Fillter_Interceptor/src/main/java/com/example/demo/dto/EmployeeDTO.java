package com.example.demo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class EmployeeDTO {
    Long employeeId;
    @NotNull
    @Length(min=10,max=50,message="Độ dài phải từ 10 đến 50 ký tự")
    String name;
    @Valid
    Date birthDate;
    @Valid
    String gender;
    @Email(message="Email không hợp lệ")
    @NotNull(message="email không được rỗng")
    String email;
}
