package ru.dinerik.ECM.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.dto.organization.OrganizationForRequest;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с контроллером Организация
@RequestMapping("/organization")
@Tag(name="Организации", description="Управление организациями")
public interface OrganizationController {

    @Operation(
            summary = "Получить организацию",
            description = "Позволяет получить организацию по id"
    )
    @GetMapping("/{id}")
    OrganizationForResponse findById(
            @PathVariable("id")
            @Parameter(description = "Идентификатор организации")
            Long id);

    @Operation(
            summary = "Получить список организаций",
            description = "Позволяет получить список организаций с поиском по аттрибутам, с пагинацией и сортировкой"
    )
    @GetMapping( "")
    List<OrganizationForResponse> organizationTable(
            @RequestParam(value = "attr", required = false)
            @Parameter(description = "Аттрибут для поиска")
            Optional<String> attribute,
            @RequestParam(value = "value", required = false)
            @Parameter(description = "Фраза для поиска")
            Optional<String> searchText,
            @RequestParam(value = "page", required = false)
            @Parameter(description = "Номер страницы для отображения")
            Optional<Integer> page,
            @RequestParam(value = "per_page", required = false)
            @Parameter(description = "Количество объектов на странице")
            Optional<Integer> organizationPerPage,
            @RequestParam(value = "sort_by", required = false)
            @Parameter(description = "Аттрибут для сортировки")
            Optional<String> sortBy);

    @Operation(
            summary = "Добавить новую организацию",
            description = "Позволяет добавить новую организацию"
    )
    @PostMapping
    List<OrganizationForResponse> createOrganization(@RequestBody OrganizationForRequest request);

    @Operation(
            summary = "Назначить директора в организацию",
            description = "Позволяет назначить директора в организацию по id"
    )
    @PatchMapping("/{id}/assignDirector")
    OrganizationForResponse assignDirectorInOrganization(
            @PathVariable
            @Parameter(description = "Идентификатор организации") Long id,
            @RequestParam("directorId")
            @Parameter(description = "Идентификатор сотрудника") Long directorId);

    @Operation(
            summary = "Редактировать организацию",
            description = "Позволяет отредактировать организацию"
    )
    @PutMapping("/{id}")
    List<OrganizationForResponse> updateOrganization(
            @PathVariable
            @Parameter(description = "Идентификатор организации")
            Long id,
            @Valid @RequestBody OrganizationForRequest request);

    @Operation(
            summary = "Удалить организацию",
            description = "Позволяет удалить организацию"
    )
    @DeleteMapping("/{id}")
    List<OrganizationForResponse> deleteOrganization(
            @PathVariable
            @Parameter(description = "Идентификатор организации")
            Long id);
}