package ru.dinerik.ECM.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dinerik.ECM.domain.Employee;
import ru.dinerik.ECM.repository.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;

@SpringBootTest
public class EmployeeServiceDBTest {

/*    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void beforeEach() {
        employeeRepository.deleteAll();
    }

    @Test
    void shouldCreateSuccessfully() {
        List<Employee> employeeList = employeeService.createEmployee(new Employee("Steve", "Jobs", "Ht", "Director"));
        Employee asEmployee = employeeList.get(0);
        assertNotNull(asEmployee.getId());
        assertEquals("Steve", asEmployee.getLastname());
        assertEquals("Jobs", asEmployee.getFirstname());
        assertEquals("Ht", asEmployee.getPatronymic());
        assertEquals("Director", asEmployee.getJobTitle());
        assertEquals(1, employeeRepository.count());
    }*/
}
