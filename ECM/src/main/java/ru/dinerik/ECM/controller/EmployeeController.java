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

    // Получить сотрудника по id
    @GetMapping("/{id}")
    EmployeeForResponse findById(@PathVariable("id") Long id);

    // Получить список сотрудников с поиском по аттрибутам с пагинацией и сортировкой в формате DTO
    // attr - это аттрибут по которому нужно искать.
    // value - это текст того что будем искать.
    // page - это какую страницу показать.
    // perpage - это сколько объектов отобразить на странице.
    // sort_by - сортировка по аттрибуту.
    // http://localhost:8080/employee?attr=firstname&value=75&page=0&per_page=3&sort_by=lastname
    // http://localhost:8080/employee?page=0&per_page=3&sort_by=lastname
    // http://localhost:8080/employee?attr=firstname&value=75
    // http://localhost:8080/employee?attr=firstname&value=75&sort_by=firsName
    @GetMapping( "")
    List<EmployeeForResponse> employeeTable(@RequestParam(value = "attr", required = false) Optional<String> attribute,
                                     @RequestParam(value = "value", required = false) Optional<String> searchText,
                                     @RequestParam(value = "page", required = false) Optional<Integer> page,
                                     @RequestParam(value = "per_page", required = false) Optional<Integer> employeePerPage,
                                     @RequestParam(value = "sort_by", required = false) Optional<String> sortBy);

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