package com.example.employeedemo.mapper;

import com.example.employeedemo.dto.EmployeeDTO;
import com.example.employeedemo.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BeanMapperUtil<Employee, EmployeeDTO> {

}
