package ru.dinerik.ECM.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.dto.employee.EmployeeForRequest;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с контроллером Сотрудник
@RequestMapping("/employee")
@Tag(name="Сотрудники", description="Управление сотрудниками")
public interface EmployeeController {

    @Operation(
            summary = "Получить сотрудника",
            description = "Позволяет получить сотрудника по id"
    )
    @GetMapping("/{id}")
    EmployeeForResponse findById(
            @PathVariable("id")
            @Parameter(description = "Идентификатор сотрудника")
            Long id);

    @Operation(
            summary = "Получить список сотрудников",
            description = "Позволяет получить список сотрудников с поиском по аттрибутам, с пагинацией и сортировкой"
    )
    @GetMapping( "")
    List<EmployeeForResponse> employeeTable(
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
            Optional<Integer> employeePerPage,
            @RequestParam(value = "sort_by", required = false)
            @Parameter(description = "Аттрибут для сортировки")
            Optional<String> sortBy);

    @Operation(
            summary = "Добавить нового сотрудника",
            description = "Позволяет добавить нового сотрудника"
    )
    @PostMapping
    List<EmployeeForResponse> createEmployee(@RequestBody EmployeeForRequest request);

    @Operation(
            summary = "Редактировать сотрудника",
            description = "Позволяет отредактировать сотрудника"
    )
    @PutMapping("/{id}")
    List<EmployeeForResponse> updateEmployee(
            @PathVariable
            @Parameter(description = "Идентификатор сотрудника")
            Long id,
            @Valid @RequestBody EmployeeForRequest request);

    @Operation(
            summary = "Удалить сотрудника",
            description = "Позволяет удалить сотрудника"
    )
    @DeleteMapping("/{id}")
    List<EmployeeForResponse> deleteEmployee(
            @PathVariable
            @Parameter(description = "Идентификатор сотрудника")
            Long id);
}