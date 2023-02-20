package ru.dinerik.ECM.controller.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.ECM.controller.OrderController;
import ru.dinerik.ECM.dto.order.OrderForRequest;
import ru.dinerik.ECM.dto.order.OrderForResponse;
import ru.dinerik.ECM.mapper.OrderMapper;
import ru.dinerik.ECM.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    @Autowired
    public OrderControllerImpl(OrderService service, OrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // Получить список всех поручений в формате DTO
    @Override
    @GetMapping("")
    public List<OrderForResponse> orderTable(@RequestParam("page") Optional<Integer> page,
                                             @RequestParam("per_page") Optional<Integer> orderPerPage,
                                             @RequestParam("sort_by") Optional<String> sortBy){
        return service.findAll(page, orderPerPage, sortBy).stream()
                .map(mapper::responseToOrderDto).collect(Collectors.toList());
    }

    // Получить поручение по id
    @Override
    @GetMapping("/{id}")
    public OrderForResponse findById(@PathVariable("id") Long id){
        return mapper.responseToOrderDto(service.findById(id));
    }

    // Поиск поручений по аттрибутам
    @Override
    @GetMapping("/search")
    public List<OrderForResponse> search(@RequestParam(value = "attr", required = false) Optional<String> attribute,
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
    public OrderForResponse setPerformanceSign(@PathVariable Long id,
                                        @RequestBody OrderForRequest request) {
        return mapper.responseToOrderDto(service.assignPerformanceSign(id, request.getPerformanceSign()));
    }

    // Установить статус контроль
    @Override
    @PatchMapping("/{id}/control")
    public OrderForResponse setControlSign(@PathVariable Long id,
                                        @RequestBody OrderForRequest request) {
        return mapper.responseToOrderDto(service.assignControlSign(id, request.getControlSign()));
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