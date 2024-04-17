package com.example.employeedemo.service;

import com.example.employeedemo.dto.EmployeeDTO;
import com.example.employeedemo.exception.EmployeeNotFoundException;
import com.example.employeedemo.mapper.EmployeeMapper;
import com.example.employeedemo.model.Employee;
import com.example.employeedemo.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = this.employeeMapper.toEntity(employeeDTO);
        employee = this.employeeRepository.save(employee);
        return this.employeeMapper.toDto(employee);
    }

    public Page<EmployeeDTO> getAllEmployees(int page, int size) {
        Page<Employee> employeeList = this.employeeRepository.findAll(PageRequest.of(page, size));
        return employeeList.map(this.employeeMapper::toDto);
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        return employee.map(this.employeeMapper::toDto).orElseThrow(() -> new EmployeeNotFoundException("Not found"));
    }
}
