package ru.dinerik.ECM.service;

import ru.dinerik.ECM.domain.Division;
import ru.dinerik.ECM.domain.Order;
import java.util.List;
import java.util.Optional;

// Интерфейс для работы с сервисом Поручение
public interface OrderService {

    // Получить список всех поручений
    List<Order> findAll(Optional<Integer> page, Optional<Integer> orderPerPage, Optional<String> sortBy);

    // Получить поручение по id
    Order findById(Long id);

    // Поиск поручений по аттрибутам
    List<Order> search(Optional<String> attribute, Optional<String> searchText);

    // Добавить новое поручение
    List<Order> createOder(Order order);

    // Установить статус исполнение
    Order assignPerformanceSign(Long id, Boolean performanceSign);

    // Установить статус контроль
    Order assignControlSign(Long id, Boolean controlSign);

    // Редактировать поручение
    List<Order> updateOrder(Long id, Order order);

    // Удалить поручение
    List<Order> deleteOrder(Long id);
}