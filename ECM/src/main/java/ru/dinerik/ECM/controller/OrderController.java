package ru.dinerik.ECM.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.dto.order.OrderForRequest;
import ru.dinerik.ECM.dto.order.OrderForResponse;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с контроллером Поручение
@RequestMapping("/order")
public interface OrderController {

    // Получить поручение по id
    @GetMapping("/{id}")
    OrderForResponse findById(@PathVariable("id") Long id);

    // Получить список поручений с поиском по аттрибутам с пагинацией и сортировкой в формате DTO
    // attr - это аттрибут по которому нужно искать.
    // value - это текст того что будем искать.
    // page - это какую страницу показать.
    // perpage - это сколько объектов отобразить на странице.
    // sort_by - сортировка по аттрибуту.
    // http://localhost:8080/order?attr=subject&value=налог
    @GetMapping( "")
    List<OrderForResponse> orderTable(@RequestParam(value = "attr", required = false) Optional<String> attribute,
                                  @RequestParam(value = "value", required = false) Optional<String> searchText,
                                  @RequestParam(value = "page", required = false) Optional<Integer> page,
                                  @RequestParam(value = "per_page", required = false) Optional<Integer> orderPerPage,
                                  @RequestParam(value = "sort_by", required = false) Optional<String> sortBy);

    // Добавить новое поручение
    @PostMapping
    List<OrderForResponse> createOrder(@RequestBody OrderForRequest request);

    // Установить статус исполнено
    @PatchMapping("/{id}/performance")
    OrderForResponse setPerformanceSign(@PathVariable Long id,
                                        @RequestBody OrderForRequest request);

    // Установить статус контроль
    @PatchMapping("/{id}/control")
    OrderForResponse setControlSign(@PathVariable Long id,
                                        @RequestBody OrderForRequest request);

    // Редактировать поручение
    @PutMapping("/{id}")
    List<OrderForResponse> updateOrder(@PathVariable Long id,
                                                    @Valid @RequestBody OrderForRequest request);

    // Удалить поручение по id
    @DeleteMapping("/{id}")
    List<OrderForResponse> deleteOrder(@PathVariable Long id);

}