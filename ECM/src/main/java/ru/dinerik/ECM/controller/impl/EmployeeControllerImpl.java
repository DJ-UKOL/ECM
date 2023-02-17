package ru.dinerik.ECM.controller.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.controller.EmployeeController;
import ru.dinerik.ECM.dto.employee.EmployeeForRequest;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;
import ru.dinerik.ECM.mapper.EmployeeMapper;
import ru.dinerik.ECM.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService service;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeControllerImpl(EmployeeService service, EmployeeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    // Получить список всех сотрудников в формате DTO
    @Override
    @GetMapping("")
    public List<EmployeeForResponse> employeeTable(
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("per_page") Optional<Integer> employeePerPage,
                              @RequestParam("sort_by") Optional<String> sortBy){

        return service.findAll(page, employeePerPage, sortBy).stream()
                .map(mapper::responseToEmployeeDto).collect(Collectors.toList());
    }

    // Получить сотрудника по id
    @Override
    @GetMapping("/{id}")
    public EmployeeForResponse findById(@PathVariable("id") Long id){
        return mapper.responseToEmployeeDto(service.findById(id));
    }

    // Добавить нового сотрудника
    @Override
    @PostMapping
    public List<EmployeeForResponse> createEmployee(@RequestBody EmployeeForRequest request) {
        return service.createEmployee(mapper.requestToEmployee(request)).stream()
                .map(mapper::responseToEmployeeDto).collect(Collectors.toList());
    }

    // Редактировать сотрудника
    @Override
    @PutMapping("/{id}")
    public List<EmployeeForResponse> updateEmployee(@PathVariable Long id,
                                                    @Valid @RequestBody EmployeeForRequest request){
        return service.updateEmployee(id, mapper.requestToEmployee(request)).stream()
                .map(mapper::responseToEmployeeDto).collect(Collectors.toList());
    }

    // Удалить сотрудника по id
    @Override
    @DeleteMapping("/{id}")
    public List<EmployeeForResponse> deleteEmployee(@PathVariable Long id){
        return service.deleteEmployee(id).stream()
                .map(mapper::responseToEmployeeDto).collect(Collectors.toList());
    }
}