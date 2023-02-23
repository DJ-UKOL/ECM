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

    // Получить подразделение по id
    @GetMapping("/{id}")
    DivisionForResponse findById(@PathVariable("id") Long id);

    // Получить список подразделений с поиском по аттрибутам с пагинацией и сортировкой в формате DTO
    // attr - это аттрибут по которому нужно искать.
    // value - это текст того что будем искать.
    // page - это какую страницу показать.
    // perpage - это сколько объектов отобразить на странице.
    // sort_by - сортировка по аттрибуту.
    // http://localhost:8080/division?attr=fullName&value=Отдел
    @GetMapping( "")
    List<DivisionForResponse> divisionTable(@RequestParam(value = "attr", required = false) Optional<String> attribute,
                                     @RequestParam(value = "value", required = false) Optional<String> searchText,
                                     @RequestParam(value = "page", required = false) Optional<Integer> page,
                                     @RequestParam(value = "per_page", required = false) Optional<Integer> divisionPerPage,
                                     @RequestParam(value = "sort_by", required = false) Optional<String> sortBy);

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