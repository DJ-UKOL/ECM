package ru.dinerik.ECM.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class OrganizationControllerImpl implements OrganizationController {

    private final OrganizationService service;
    private final OrganizationMapper mapper;

    // Получить организацию по id
    @Override
    @GetMapping("/{id}")
    public OrganizationForResponse findById(@PathVariable("id") Long id) {
        return mapper.responseToOrganizationDto(service.findById(id));
    }

    // Получить список организаций с поиском по аттрибутам с пагинацией и сортировкой в формате DTO
    @Override
    @GetMapping("")
    public List<OrganizationForResponse> organizationTable(
            @RequestParam(value = "attr", required = false) Optional<String> attribute,
            @RequestParam(value = "value", required = false) Optional<String> searchText,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "per_page", required = false) Optional<Integer> organizationPerPage,
            @RequestParam(value = "sort_by", required = false) Optional<String> sortBy) {

        return service.search(attribute, searchText, page, organizationPerPage, sortBy).stream()
                .map(mapper::responseToOrganizationDto).collect(Collectors.toList());
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