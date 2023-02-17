package ru.dinerik.ECM.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;
import ru.dinerik.ECM.dto.organization.OrganizationForRequest;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;
import java.util.List;
import java.util.Optional;

// Интерфейс для работы с контроллером Организация
@RequestMapping("/organization")
public interface OrganizationController {

    // Получить список всех организаций в формате DTO
    // http://localhost:8080/organization?page=1&per_page=3
    // http://localhost:8080/organization?sort_by=fullName
    // http://localhost:8080/organization?page=1&per_page=3&sort_by=fullName
    @GetMapping("")
    List<OrganizationForResponse> organizationTable(@RequestParam("page") Optional<Integer> page,
                                                    @RequestParam("per_page") Optional<Integer> organizationPerPage,
                                                    @RequestParam("sort_by") Optional<String> sortBy);

    // Получить организацию по id
    @GetMapping("/{id}")
    OrganizationForResponse findById(@PathVariable("id") Long id);

    // Добавить новую организацию
    @PostMapping
    List<OrganizationForResponse> createOrganization(@RequestBody OrganizationForRequest request);

    // Назначение директора в организацию
    @PatchMapping("/{id}/director")
    OrganizationForResponse assignDirectorInOrganization(@PathVariable Long id,
                                                         @RequestBody EmployeeForResponse employee);

    // Редактировать организацию
    @PutMapping("/{id}")
    List<OrganizationForResponse> updateOrganization(@PathVariable Long id,
                                        @Valid @RequestBody OrganizationForRequest request);

    // Удалить организацию по id
    @DeleteMapping("/{id}")
    List<OrganizationForResponse> deleteOrganization(@PathVariable Long id);
}