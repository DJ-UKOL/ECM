package ru.dinerik.ECM.controller;

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
public interface DivisionController {

    // Получить список всех подразделений в формате DTO
    // http://localhost:8080/division?page=1&per_page=3
    // http://localhost:8080/division?sort_by=fullName
    // http://localhost:8080/division?page=1&per_page=3&sort_by=fullName
    @GetMapping("")
    List<DivisionForResponse> divisionTable(@RequestParam("page") Optional<Integer> page,
                                            @RequestParam("per_page") Optional<Integer> divisionPerPage,
                                            @RequestParam("sort_by") Optional<String> sortBy);

    // Получить подразделение по id
    @GetMapping("/{id}")
    DivisionForResponse findById(@PathVariable("id") Long id);

    // Поиск подразделения по аттрибутам
    // http://localhost:8080/division/search?attr=fullName&value=Отдел
    @GetMapping( "/search")
    List<DivisionForResponse> search(@RequestParam(value = "attr", required = false) Optional<String> attribute,
                                         @RequestParam(value = "value", required = false) Optional<String> searchText);

    // Добавить новое подразделение
    @PostMapping
    List<DivisionForResponse> createDivision(@RequestBody DivisionForRequest request);

    // Назначение управляющего в подразделение
    @PatchMapping("/{id}/manager")
    DivisionForResponse assignManagerInDivision(@PathVariable Long id, @RequestBody EmployeeForResponse manager);

    // Прикрепить подразделение к организации
    @PatchMapping("/{id}/organization")
    DivisionForResponse assignOrganizationInDivision(@PathVariable Long id, @RequestBody OrganizationForResponse organization);

    // Редактировать подразделение
    @PutMapping("/{id}")
    List<DivisionForResponse> updateDivision(@PathVariable Long id,
                                                            @Valid @RequestBody DivisionForRequest request);

    // Удалить подразделение по id
    @DeleteMapping("/{id}")
    List<DivisionForResponse> deleteDivision(@PathVariable Long id);

}