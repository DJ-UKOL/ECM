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

    // Получить сотрудника по id
    @Override
    @GetMapping("/{id}")
    public EmployeeForResponse findById(@PathVariable("id") Long id){
        return mapper.responseToEmployeeDto(service.findById(id));
    }

    // Получить список сотрудников с поиском по аттрибутам с пагинацией и сортировкой в формате DTO
    @Override
    @GetMapping("")
    public List<EmployeeForResponse> employeeTable(@RequestParam(value = "attr", required = false) Optional<String> attribute,
                                            @RequestParam(value = "value", required = false) Optional<String> searchText,
                                            @RequestParam(value = "page", required = false) Optional<Integer> page,
                                            @RequestParam(value = "per_page", required = false) Optional<Integer> employeePerPage,
                                            @RequestParam(value = "sort_by", required = false) Optional<String> sortBy) {
        return service.search(attribute, searchText, page, employeePerPage, sortBy).stream()
                .map(mapper::responseToEmployeeDto).collect(Collectors.toList());
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