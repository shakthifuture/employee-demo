package com.example.employeedemo.controller;

import com.example.employeedemo.dto.EmployeeDTO;
import com.example.employeedemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Received request to save employee: {}", employeeDTO);
        EmployeeDTO savedEmployeeDTO = employeeService.saveEmployee(employeeDTO);
        log.info("Employee saved successfully: {}", employeeDTO);
        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        log.info("Received request for get all employees. Page {}, Size {} ", page, size);
        Page<EmployeeDTO> employeeDTOPage = this.employeeService.getAllEmployees(page, size);
        log.info("Returning {} employees for page {}", employeeDTOPage.getNumberOfElements(), page);
        return ResponseEntity.ok(employeeDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        log.info("Received request for get employee by id: {}", id);
        EmployeeDTO employeeDTO = this.employeeService.getEmployeeById(id);
        if(employeeDTO == null) {
            log.info("No content found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        log.info("Returning employee: {}", employeeDTO);
        return ResponseEntity.ok(employeeDTO);
    }

}
