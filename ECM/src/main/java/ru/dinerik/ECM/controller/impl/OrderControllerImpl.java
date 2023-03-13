package ru.dinerik.ECM.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.controller.OrderController;
import ru.dinerik.ECM.domain.Order;
import ru.dinerik.ECM.dto.order.OrderForRequest;
import ru.dinerik.ECM.dto.order.OrderForResponse;
import ru.dinerik.ECM.mapper.OrderMapper;
import ru.dinerik.ECM.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    // Получить поручение по id
    @Override
    @GetMapping("/{id}")
    public OrderForResponse findById(@PathVariable("id") Long id){
        Order order = service.findById(id);
        return mapper.responseToOrderDto(order);
    }

    // Получить список поручений с поиском по аттрибутам с пагинацией и сортировкой в формате DTO
    @Override
    @GetMapping("")
    public List<OrderForResponse> orderTable(@RequestParam(value = "attr", required = false) Optional<String> attribute,
                                         @RequestParam(value = "value", required = false) Optional<String> searchText,
                                         @RequestParam(value = "page", required = false) Optional<Integer> page,
                                         @RequestParam(value = "per_page", required = false) Optional<Integer> orderPerPage,
                                         @RequestParam(value = "sort_by", required = false) Optional<String> sortBy) {
        return service.search(attribute, searchText, page, orderPerPage, sortBy).stream()
                .map(mapper::responseToOrderDto).collect(Collectors.toList());
    }

    // Добавить новое поручение
    @Override
    @PostMapping
    public List<OrderForResponse> createOrder(@Valid @RequestBody OrderForRequest request){
        return service.createOder(mapper.requestToOrder(request)).stream()
                .map(mapper::responseToOrderDto).collect(Collectors.toList());
    }

    // Установить статус исполнено
    @Override
    @PatchMapping("/{id}/performance")
    public OrderForResponse setPerformanceSign(
            @PathVariable Long id,
            @RequestParam("bool") Boolean bool) {
        return mapper.responseToOrderDto(service.assignPerformanceSign(id, bool));
    }

    // Назначить автора поручения
    @Override
    @PatchMapping("/{id}/assignAuthor")
    public OrderForResponse assignAuthorInOrder(
            @PathVariable Long id,
            @RequestParam("authorId") Long authorId) {
        return mapper.responseToOrderDto(service.assignAuthor(id, authorId));
    }

    // Назначить исполнителей поручения
    @Override
    @PatchMapping("/{id}/assignExecutor")
    public OrderForResponse assignExecutorInOrder(
            @PathVariable Long id,
            @RequestParam("executorIds") Set<Long> executorIds) {
        return mapper.responseToOrderDto(service.assignExecutors(id, executorIds));
    }

    // Редактировать поручение
    @Override
    @PutMapping("/{id}")
    public List<OrderForResponse> updateOrder(@PathVariable Long id,
                                              @Valid @RequestBody OrderForRequest request){
        return service.updateOrder(id, mapper.requestToOrder(request)).stream()
                .map(mapper::responseToOrderDto).collect(Collectors.toList());
    }

    // Удалить поручение по id
    @Override
    @DeleteMapping("/{id}")
    public List<OrderForResponse> deleteOrder(@PathVariable Long id){
        return service.deleteOrder(id).stream()
                .map(mapper::responseToOrderDto).collect(Collectors.toList());
    }
}