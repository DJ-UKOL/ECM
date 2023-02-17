package ru.dinerik.ECM.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.dto.employee.EmployeeForRequest;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с контроллером Сотрудник
@RequestMapping("/employee")
public interface EmployeeController {

    // Получить список всех сотрудников в формате DTO
    // http://localhost:8080/employee?page=1&per_page=3
    // http://localhost:8080/employee?sort_by=lastname
    // http://localhost:8080/employee?page=1&per_page=3&sort_by=lastname
    @GetMapping("")
    List<EmployeeForResponse> employeeTable(@RequestParam("page") Optional<Integer> page,
                                            @RequestParam("per_page") Optional<Integer> employeePerPage,
                                            @RequestParam("sort_by") Optional<String> sortBy);

    // Получить сотрудника по id
    @GetMapping("/{id}")
    EmployeeForResponse findById(@PathVariable("id") Long id);

    // Добавить нового сотрудника
    @PostMapping
    List<EmployeeForResponse> createEmployee(@RequestBody EmployeeForRequest request);

    // Редактировать сотрудника
    @PutMapping("/{id}")
    List<EmployeeForResponse> updateEmployee(@PathVariable Long id,
                                                    @Valid @RequestBody EmployeeForRequest request);

    // Удалить сотрудника по id
    @DeleteMapping("/{id}")
    List<EmployeeForResponse> deleteEmployee(@PathVariable Long id);
}
