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

    // Получить организацию по id
    @GetMapping("/{id}")
    OrganizationForResponse findById(@PathVariable("id") Long id);

    // Получить список организаций с поиском по аттрибутам с пагинацией и сортировкой в формате DTO
    // attr - это аттрибут по которому нужно искать.
    // value - это текст того что будем искать.
    // page - это какую страницу показать.
    // perpage - это сколько объектов отобразить на странице.
    // sort_by - сортировка по аттрибуту.
    // http://localhost:8080/organization?attr=fullName&value=Эвер
    @GetMapping( "")
    List<OrganizationForResponse> organizationTable(
            @RequestParam(value = "attr", required = false) Optional<String> attribute,
            @RequestParam(value = "value", required = false) Optional<String> searchText,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "per_page", required = false) Optional<Integer> organizationPerPage,
            @RequestParam(value = "sort_by", required = false) Optional<String> sortBy);

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