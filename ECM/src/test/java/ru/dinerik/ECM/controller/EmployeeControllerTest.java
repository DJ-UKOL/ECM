package ru.dinerik.ECM.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dinerik.ECM.dto.employee.EmployeeForRequest;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeControllerTest {

/*    @Autowired
    private EmployeeController employeeController;

    // Создать список сотрудников из 100 человек
    @Test
    void shouldCreateSuccessfully() {
        for (int i = 0; i < 100; i++) {
            EmployeeForRequest employeeForRequest  = new EmployeeForRequest();
            employeeForRequest.setFirstname("FirstName_" + i);
            employeeForRequest.setLastname("MyLastName_" + i);
            employeeForRequest.setPatronymic("Patr_" + i);
            employeeForRequest.setJobTitle("WorkIn_"+ i);
            employeeController.createEmployee(employeeForRequest);
        }
       // assertEquals(100, (long) employeeController.employeeTable().size());
    }

    // Обновить должность у сотрудника
    @Test
    void shouldUpdateSuccessfully() {
        EmployeeForResponse employeeForResponse = employeeController.findById(5L);
        EmployeeForRequest employeeForRequest = new EmployeeForRequest();
        employeeForRequest.setFirstname(employeeForResponse.getFirstname());
        employeeForRequest.setLastname(employeeForResponse.getLastname());
        employeeForRequest.setPatronymic(employeeForResponse.getPatronymic());
        employeeForRequest.setJobTitle("Director");

        employeeController.updateEmployee(5L, employeeForRequest);
        assertEquals("Director", employeeController.findById(5L).getJobTitle());
    }*/
}