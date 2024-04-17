package com.example.employeedemo;

import com.example.employeedemo.dto.EmployeeDTO;
import com.example.employeedemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@RequiredArgsConstructor
public class EmployeeControllerTest {

    public static final Integer ID = 1;
    public static final String NAME = "John";
    public static final Integer SALARY = 200;
    public static final Integer AGE = 20;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDTO getEmployeeDTO() {
        return new EmployeeDTO(ID, NAME, SALARY, AGE);
    }

    @Test
    void testSaveEmployee() throws Exception {
        this.mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(getEmployeeDTO())))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveEmployeeServiceSuccess() {
        EmployeeDTO employeeDTO = getEmployeeDTO();
        EmployeeDTO savedEmployee = this.employeeService.saveEmployee(employeeDTO);
        assertEquals(employeeDTO, savedEmployee);
    }

    @Test
    void testGetEmployeeById() {
        EmployeeDTO savedEmployee = this.employeeService.getEmployeeById(ID);
        assertEquals(ID, savedEmployee.id());
    }

}
