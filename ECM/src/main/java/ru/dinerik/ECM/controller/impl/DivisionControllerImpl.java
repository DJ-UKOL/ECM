package ru.dinerik.ECM.controller.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.controller.DivisionController;
import ru.dinerik.ECM.dto.division.DivisionForRequest;
import ru.dinerik.ECM.dto.division.DivisionForResponse;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;
import ru.dinerik.ECM.mapper.DivisionMapper;
import ru.dinerik.ECM.service.DivisionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/division")
public class DivisionControllerImpl implements DivisionController {

    private final DivisionService service;
    private final DivisionMapper mapper;

    @Autowired
    public DivisionControllerImpl(DivisionService service, DivisionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // Получить список всех подразделений в формате DTO
    @Override
    @GetMapping("")
    public List<DivisionForResponse> divisionTable(@RequestParam("page") Optional<Integer> page,
                                                   @RequestParam("per_page") Optional<Integer> divisionPerPage,
                                                   @RequestParam("sort_by") Optional<String> sortBy){
        return service.findAll(page, divisionPerPage, sortBy).stream()
                .map(mapper::responseToDivisionDto).collect(Collectors.toList());

    }

    // Получить подразделение по id
    @Override
    @GetMapping("/{id}")
    public DivisionForResponse findById(@PathVariable("id") Long id){
        return mapper.responseToDivisionDto(service.findById(id));
    }

    // Поиск подразделения по аттрибутам
    @Override
    @GetMapping("/search")
    public List<DivisionForResponse> search(@RequestParam(value = "attr", required = false) Optional<String> attribute,
                                            @RequestParam(value = "value", required = false) Optional<String> searchText,
                                            @RequestParam(value = "page", required = false) Optional<Integer> page,
                                            @RequestParam(value = "per_page", required = false) Optional<Integer> divisionPerPage,
                                            @RequestParam(value = "sort_by", required = false) Optional<String> sortBy) {
        return service.search(attribute, searchText, page, divisionPerPage, sortBy).stream()
                .map(mapper::responseToDivisionDto).collect(Collectors.toList());
    }

    // Добавить новое подразделение
    @Override
    @PostMapping
    public List<DivisionForResponse> createDivision(@RequestBody DivisionForRequest request) {
        return service.createDivision(mapper.requestToDivision(request)).stream()
                .map(mapper::responseToDivisionDto).collect(Collectors.toList());
    }

    // Назначение управляющего в подразделение
    @Override
    @PatchMapping("/{id}/manager")
    public DivisionForResponse assignManagerInDivision(@PathVariable Long id, @RequestBody EmployeeForResponse manager) {
        return mapper.responseToDivisionDto(service.assignManager(id, manager.getId()));
    }

    // Прикрепить подразделение к организации
    @Override
    @PatchMapping("/{id}/organization")
    public DivisionForResponse assignOrganizationInDivision(@PathVariable Long id, @RequestBody OrganizationForResponse organization) {
        return mapper.responseToDivisionDto(service.assignOrganization(id, organization.getId()));
    }

    // Редактировать подразделение
    @Override
    @PutMapping("/{id}")
    public List<DivisionForResponse> updateDivision(@PathVariable Long id,
                                                    @Valid @RequestBody DivisionForRequest request) {
        return service.updateDivision(id, mapper.requestToDivision(request)).stream()
                .map(mapper::responseToDivisionDto).collect(Collectors.toList());
    }

    // Удалить подразделение по id
    @Override
    @DeleteMapping("/{id}")
    public List<DivisionForResponse> deleteDivision(@PathVariable Long id) {
        return service.deleteDivision(id).stream()
                .map(mapper::responseToDivisionDto).collect(Collectors.toList());
    }
}