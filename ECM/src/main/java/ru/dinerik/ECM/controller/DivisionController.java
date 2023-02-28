package ru.dinerik.ECM.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.dto.division.DivisionForRequest;
import ru.dinerik.ECM.dto.division.DivisionForResponse;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с контроллером Подразделение
@RequestMapping("/division")
@Tag(name="Подразделения", description="Управление подразделениями")
public interface DivisionController {

    @Operation(
            summary = "Получить подразделение",
            description = "Позволяет получить подразделение по id"
    )
    @GetMapping("/{id}")
    DivisionForResponse findById(@PathVariable("id")
                                 @Parameter(description = "Идентификатор подразделения")
                                 Long id);

    @Operation(
            summary = "Получить список подразделений",
            description = "Позволяет получить список подразделений с поиском по аттрибутам, с пагинацией и сортировкой"
    )
    @GetMapping( "")
    List<DivisionForResponse> divisionTable(
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
            Optional<Integer> divisionPerPage,
            @RequestParam(value = "sort_by", required = false)
            @Parameter(description = "Аттрибут для сортировки")
            Optional<String> sortBy);

    @Operation(
            summary = "Добавить новое подразделение",
            description = "Позволяет добавить новое подразделение"
    )
    @PostMapping
    List<DivisionForResponse> createDivision(
            @RequestBody DivisionForRequest request);

    @Operation(
            summary = "Назначить управляющего в подразделение",
            description = "Позволяет назначить управляющего в подразделение"
    )
    @PatchMapping("/{id}/manager")
    DivisionForResponse assignManagerInDivision(
            @PathVariable
            @Parameter(description = "Идентификатор подразделения") Long id,
            @RequestBody EmployeeForResponse manager);

    @Operation(
            summary = "Прикрепить подразделение к организации",
            description = "Позволяет прикрепить подразделение к организации"
    )
    @PatchMapping("/{id}/organization")
    DivisionForResponse assignOrganizationInDivision(
            @PathVariable
            @Parameter(description = "Идентификатор подразделения")
            Long id,
            @RequestBody OrganizationForResponse organization);

    @Operation(
            summary = "Редактировать подразделение",
            description = "Позволяет отредактировать подразделение"
    )
    @PutMapping("/{id}")
    List<DivisionForResponse> updateDivision(
            @PathVariable
            @Parameter(description = "Идентификатор подразделения")
            Long id,
            @Valid @RequestBody DivisionForRequest request);

    @Operation(
            summary = "Удалить подразделение",
            description = "Позволяет удалить подразделение"
    )
    @DeleteMapping("/{id}")
    List<DivisionForResponse> deleteDivision(
            @PathVariable
            @Parameter(description = "Идентификатор подразделения")
            Long id);
}