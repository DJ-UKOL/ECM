package ru.dinerik.ECM.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.dto.order.OrderForRequest;
import ru.dinerik.ECM.dto.order.OrderForResponse;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с контроллером Поручение
@RequestMapping("/order")
@Tag(name="Поручения", description="Управление поручениями")
public interface OrderController {

    @Operation(
            summary = "Получить поручение",
            description = "Позволяет получить поручение по id"
    )
    @GetMapping("/{id}")
    OrderForResponse findById(
            @PathVariable("id")
            @Parameter(description = "Идентификатор поручения")
            Long id);

    @Operation(
            summary = "Получить список поручений",
            description = "Позволяет получить список поручений с поиском по аттрибутам, с пагинацией и сортировкой"
    )
    @GetMapping( "")
    List<OrderForResponse> orderTable(
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
            Optional<Integer> orderPerPage,
            @RequestParam(value = "sort_by", required = false)
            @Parameter(description = "Аттрибут для сортировки")
            Optional<String> sortBy);

    @Operation(
            summary = "Добавить новое поручение",
            description = "Позволяет добавить новое поручение"
    )
    @PostMapping
    List<OrderForResponse> createOrder(@RequestBody OrderForRequest request);

    @Operation(
            summary = "Установить статус исполнено",
            description = "Позволяет установить статус исполнено"
    )
    @PatchMapping("/{id}/performance")
    OrderForResponse setPerformanceSign(
            @PathVariable
            @Parameter(description = "Идентификатор поручения")
            Long id,
            @RequestBody OrderForRequest request);

    @Operation(
            summary = "Установить статус контроль",
            description = "Позволяет установить статус контроль"
    )
    @PatchMapping("/{id}/control")
    OrderForResponse setControlSign(
            @PathVariable
            @Parameter(description = "Идентификатор поручения")
            Long id,
            @RequestBody OrderForRequest request);

    @Operation(
            summary = "Редактировать поручение",
            description = "Позволяет отредактировать поручение"
    )
    @PutMapping("/{id}")
    List<OrderForResponse> updateOrder(
            @PathVariable
            @Parameter(description = "Идентификатор поручения")
            Long id,
            @Valid @RequestBody OrderForRequest request);

    @Operation(
            summary = "Удалить поручение",
            description = "Позволяет удалить поручение"
    )
    @DeleteMapping("/{id}")
    List<OrderForResponse> deleteOrder(
            @PathVariable
            @Parameter(description = "Идентификатор поручения")
            Long id);
}