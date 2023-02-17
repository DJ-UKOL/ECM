package ru.dinerik.ECM.controller.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.controller.OrganizationController;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;
import ru.dinerik.ECM.dto.organization.OrganizationForRequest;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;
import ru.dinerik.ECM.mapper.OrganizationMapper;
import ru.dinerik.ECM.service.OrganizationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController     // @Controller + @ResponseBody над каждым методом
@RequestMapping("/organization")
public class OrganizationControllerImpl implements OrganizationController {

    private final OrganizationService service;
    private final OrganizationMapper mapper;

    @Autowired
    public OrganizationControllerImpl(OrganizationService service, OrganizationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // Получить список всех организаций в формате DTO
    @Override
    @GetMapping("")
    public List<OrganizationForResponse> organizationTable(@RequestParam("page") Optional<Integer> page,
                                                           @RequestParam("per_page") Optional<Integer> organizationPerPage,
                                                           @RequestParam("sort_by") Optional<String> sortBy) {
        return service.findAll(page, organizationPerPage, sortBy).stream()
                .map(mapper::responseToOrganizationDto).collect(Collectors.toList());
    }

    // Получить организацию по id
    @Override
    @GetMapping("/{id}")
    public OrganizationForResponse findById(@PathVariable("id") Long id) {
        return mapper.responseToOrganizationDto(service.findById(id));
    }

    // Добавить новую организацию
    @Override
    @PostMapping
    public List<OrganizationForResponse> createOrganization(@RequestBody OrganizationForRequest request) {
        return service.createOrganization(mapper.requestToOrganization(request)).stream()
                .map(mapper::responseToOrganizationDto).collect(Collectors.toList());
    }

    // Назначение директора в организацию
    @Override
    @PatchMapping("/{id}/director")
    public OrganizationForResponse assignDirectorInOrganization(@PathVariable Long id, @RequestBody EmployeeForResponse employee) {
        return mapper.responseToOrganizationDto(service.assignDirector(id, employee.getId()));
    }

    // Редактировать организацию
    @Override
    @PutMapping("/{id}")
    public List<OrganizationForResponse> updateOrganization(@PathVariable Long id,
                                                            @Valid @RequestBody OrganizationForRequest request) {
        return service.updateOrganization(id, mapper.requestToOrganization(request)).stream()
                .map(mapper::responseToOrganizationDto).collect(Collectors.toList());
    }

    // Удалить организацию по id
    @Override
    @DeleteMapping("/{id}")
    public List<OrganizationForResponse> deleteOrganization(@PathVariable Long id) {
        return service.deleteOrganization(id).stream()
                .map(mapper::responseToOrganizationDto).collect(Collectors.toList());
    }
}